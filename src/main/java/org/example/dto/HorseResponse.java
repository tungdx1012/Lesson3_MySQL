package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Horse;

import java.util.Date;

@Data
@NoArgsConstructor
public class HorseResponse {
    private Integer id;
    private String name;
    private Date foaled;

    public HorseResponse(Horse horse) {
        this.id = horse.getId();
        this.name = horse.getName();
        this.foaled = horse.getFoaled();
    }
}
