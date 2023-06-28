package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class HorseRequest {
    private Integer id;
    private String name;
    private Date foaled;
}
