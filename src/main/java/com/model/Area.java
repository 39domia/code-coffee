package com.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class Area {
    private Long id;

    private String name;
}
