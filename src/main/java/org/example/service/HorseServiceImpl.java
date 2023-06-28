package org.example.service;

import org.example.TrainerNotFoundException;
import org.example.dto.HorseRequest;
import org.example.dto.HorseResponse;
import org.example.entities.Horse;
import org.example.repository.HorseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HorseServiceImpl implements HorseService {
    @Autowired
    HorseRepository horseRepository;

    @Autowired
    EntityManager em;

    public HorseServiceImpl(HorseRepository horseRepository, EntityManager em) {
        this.horseRepository = horseRepository;
        this.em = em;
    }

    @Override
    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }

//    @Override
//    public List<Horse> findByIdAndFoaled(Integer trainerId, Integer foaled) {
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
//        StringBuilder queryBuilder = new StringBuilder();
//        queryBuilder.append("select h.id, h.name, h.foaled " +
//                "from Trainer t inner join HorseAccount ha on t.account_id=ha.account_id " +
//                "inner join Horse h on h.id = ha.horse_id where 1 = 1 ");
//        if (trainerId != null) {
//            queryBuilder.append("and t.id = :trainerId ");
//            parameterMap.put("trainerId", trainerId);
//        }
//        if (foaled != null) {
//            queryBuilder.append("and YEAR(h.foaled) = :foaled ");
//            parameterMap.put("foaled", foaled);
//        }
//        Query jpaQuery = em.createQuery(queryBuilder.toString());
//        for (String key : parameterMap.keySet()) {
//            jpaQuery.setParameter(key, parameterMap.get(key));
//        }
//        return jpaQuery.getResultList();
//    }

//    @Override
//    public List<Horse> findByIdAndFoaled(Integer trainerId, Integer foaled) {
//        return horseRepository.findAll(new Specification<Horse>() {
//            @Override
//            public Predicate toPredicate(Root<Horse> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<>();
//                if (trainerId != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), trainerId)));
//                }
//                if (foaled != null) {
//                    System.out.println(root.get("foaled"));
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("foaled"), foaled)));
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        });
//    }

    @Override
    public List<Horse> findByIdAndFoaled(Integer trainerId, Integer foaled) {
        return (List<Horse>) horseRepository.findByTrainerAndYear(trainerId, foaled);
    }

    @Override
    public HorseResponse createHorse(HorseRequest request) {
        Horse horse = new Horse();
        BeanUtils.copyProperties(request, horse);
        horseRepository.save(horse);
        return new HorseResponse(horse);
    }

    @Override
    public HorseResponse updateHorse(Integer Id, Horse horseDetails) {
        Horse t = horseRepository.findById(Id)
                .orElseThrow(() -> new TrainerNotFoundException("Horse", "id", Id));
        t.setName(horseDetails.getName());
        t.setFoaled(horseDetails.getFoaled());
        Horse updatedHorse = horseRepository.save(t);
        return new HorseResponse(updatedHorse);
    }

    @Override
    public ResponseEntity<?> deleteHorse(Integer Id) {
        Horse t = horseRepository.findById(Id)
                .orElseThrow(() -> new TrainerNotFoundException("Horse", "id", Id));
        horseRepository.delete(t);
        return ResponseEntity.ok().build();
    }
}
