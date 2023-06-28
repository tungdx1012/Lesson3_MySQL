package org.example.controller;

import org.example.TrainerNotFoundException;
import org.example.entities.Trainer;
import org.example.repository.TrainerRepository;
import org.springframework.web.bind.annotation.RestController;
import org.example.ApiConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainerController {
    @Autowired
    TrainerRepository trainerRepository;

    // Get All Trainers
    @GetMapping(ApiConst.GET_ALL_TRAINER)
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    // Create a new Trainer
    @PostMapping(ApiConst.CREATE_TRAINER)
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    // Get a Single Trainer
    @GetMapping(ApiConst.GET_SINGLE)
    public Trainer getTrainerById(@PathVariable(value = "id") Integer id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer", "id", id));
    }

    // Update a Trainer
    @PutMapping(ApiConst.UPDATE_TRAINER)
    public Trainer updateNote(@PathVariable(value = "id") Integer id,
                              @RequestBody Trainer trainerDetails) {

        Trainer t = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer", "id", id));

        t.setName(trainerDetails.getName());

        Trainer updatedTrainer = trainerRepository.save(t);
        return updatedTrainer;
    }

    // Delete a Trainer
    @DeleteMapping(ApiConst.DELETE_TRAINER)
    public ResponseEntity<?> deleteTrainer(@PathVariable(value = "id") Integer Id) {
        Trainer t = trainerRepository.findById(Id)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer", "id", Id));
        trainerRepository.delete(t);
        return ResponseEntity.ok().build();
    }
}
