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
    private String imagenUrl; // Campo para la URL de la imagen
    private String tipo; // Campo para el tipo de habitación (básica, top, etc.)

    // Si prefieres no usar Lombok Builder y necesitas un constructor específico, defínelo aquí:
    public Habitacion(String nombre, String descripcion, Double precioBase, Integer numeroCamas, Integer numeroPersonas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.numeroCamas = numeroCamas;
        this.numeroPersonas = numeroPersonas;
    }
}
