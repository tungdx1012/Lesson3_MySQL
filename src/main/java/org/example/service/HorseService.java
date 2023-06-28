package org.example.service;

import org.example.dto.HorseRequest;
import org.example.dto.HorseResponse;
import org.example.entities.Horse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface HorseService {
    public List<Horse> getAllHorses();

    public List<Horse> findByIdAndFoaled(Integer trainerId, Integer foaled);

    public HorseResponse createHorse(HorseRequest request);

    public HorseResponse updateHorse(@PathVariable(value = "id") Integer Id, @RequestBody Horse horseDetails);

    public ResponseEntity<?> deleteHorse(@PathVariable(value = "id") Integer Id);
}
