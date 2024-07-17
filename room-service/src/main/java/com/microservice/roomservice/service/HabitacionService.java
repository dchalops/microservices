package com.microservice.roomservice.service;

import com.microservice.roomservice.model.Habitacion;
import com.microservice.roomservice.repository.HabitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitacionService {
    private final HabitacionRepository habitacionRepository;

    public Habitacion saveHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public List<Habitacion> getAll() {
        return habitacionRepository.findAll();
    }

    public Habitacion getHabitacionById(String id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitacion not found"));
    }

    public Habitacion updateHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void deleteHabitacion(String id) {
        habitacionRepository.deleteById(id);
    }
}
