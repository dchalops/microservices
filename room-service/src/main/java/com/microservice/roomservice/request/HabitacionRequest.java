package com.microservice.roomservice.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class HabitacionRequest {
    private String id; // Campo id agregado

    @NotBlank(message = "Nombre es requerido")
    private String nombre;

    @NotBlank(message = "Descripción es requerida")
    private String descripcion;

    @NotNull(message = "Precio base es requerido")
    private Double precioBase;

    @NotNull(message = "Número de camas es requerido")
    private Integer numeroCamas;

    @NotNull(message = "Número de personas es requerido")
    private Integer numeroPersonas;

    private String imagenUrl; // Nuevo campo
    private String tipo; // Nuevo campo
}
