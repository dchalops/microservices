package com.microservice.roomservice.dto;

import lombok.Data;

@Data
public class HabitacionDto {
    private String id;
    private String nombre;
    private String descripcion;
    private Double precioBase;
    private Integer numeroCamas;
    private Integer numeroPersonas;
}
