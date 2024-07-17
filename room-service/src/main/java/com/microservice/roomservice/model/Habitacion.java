package com.microservice.roomservice.model;

import lombok.*;

import javax.persistence.Entity;

@Entity(name = "habitaciones")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Habitacion extends BaseEntity {
    private String nombre;
    private String descripcion;
    private Double precioBase;
    private Integer numeroCamas;
    private Integer numeroPersonas;
}
