package org.example.service;

import org.example.dto.HorseRequest;
import org.example.dto.HorseResponse;
import org.example.entities.Horse;
import org.example.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HorseServiceImpl implements HorseService {
    @Autowired
    HorseRepository horseRepository;

    @Autowired
    EntityManager em;

    @Override
    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }

    @Override
    public List<Horse> findByIdAndFoaled(Integer trainerId, Integer foaled) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select h.id, h.name, h.foaled " +
                "from Trainer t inner join HorseAccount ha on t.account_id=ha.account_id " +
                "inner join Horse h on h.id = ha.horse_id where 1 = 1 ");
        if (trainerId != null) {
            queryBuilder.append("and t.id = :trainerId ");
            paramaterMap.put("trainerId", trainerId);
        }
        if (foaled != null) {
            queryBuilder.append("and YEAR(h.foaled) = :foaled ");
            paramaterMap.put("foaled", foaled);
        }
        Query jpaQuery = em.createQuery(queryBuilder.toString());
        for (String key : paramaterMap.keySet()) {
            jpaQuery.setParameter(key, paramaterMap.get(key));
        }
        return jpaQuery.getResultList();
    }

    @Override
    public HorseResponse createHorse(HorseRequest request) {
        return null;
    }

    @Override
    public HorseResponse updateHorse(Integer Id, Horse horseDetails) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteHorse(Integer Id) {
        return null;
    }
}
