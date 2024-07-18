package com.microservice.roomservice.repository;

import com.microservice.roomservice.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, String> {
    Optional<Habitacion> findByNombre(String nombre);
}
