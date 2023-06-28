package org.example.controller;

import org.example.ApiConst;
import org.example.dto.HorseRequest;
import org.example.dto.HorseResponse;
import org.example.entities.Horse;
import org.example.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HorseController {
    @Autowired
    HorseService horseService;

    // Get All Horses
    @GetMapping(ApiConst.GET_ALL)
    public List<Horse> getAllHorses() {
        return horseService.getAllHorses();
    }

    // Get : Filter by Trainer_ID and Year
    @GetMapping(ApiConst.HORSE)
    @ResponseBody
    public Object getHorseByIdAndYear(@RequestParam(value = "trainerId", required = false) Integer trainerId,
                                      @RequestParam(value = "foaled", required = false) Integer foaled) {
        return horseService.findByIdAndFoaled(trainerId, foaled);
    }

    // Create a new Horse
    @PostMapping(value = ApiConst.HORSE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HorseResponse createHorse(@RequestBody HorseRequest request) {
        return horseService.createHorse(request);
    }

    // Update a Trainer
    @PutMapping(ApiConst.HORSE)
    public HorseResponse updateHorse(@RequestParam(value = "id") Integer id,
                                     @RequestBody Horse horseDetails) {
        return horseService.updateHorse(id, horseDetails);
    }

    // Delete a Horse
    @DeleteMapping(ApiConst.HORSE)
    public ResponseEntity<?> deleteHorse(@RequestParam(value = "id") Integer Id) {
        return horseService.deleteHorse(Id);
    }
}
