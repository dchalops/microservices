package com.microservice.roomservice.controller;

import com.microservice.roomservice.model.Habitacion;
import com.microservice.roomservice.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/habitacion")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Habitacion> save(@RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(habitacionService.saveHabitacion(habitacion));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Habitacion>> getAll() {
        return ResponseEntity.ok(habitacionService.getAll());
    }

    @GetMapping("/getHabitacionById/{id}")
    public ResponseEntity<Habitacion> getHabitacionById(@PathVariable String id) {
        return ResponseEntity.ok(habitacionService.getHabitacionById(id));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERVISOR')")
    public ResponseEntity<Habitacion> update(@RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(habitacionService.updateHabitacion(habitacion));
    }

    @DeleteMapping("/deleteHabitacion/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        habitacionService.deleteHabitacion(id);
        return ResponseEntity.ok().build();
    }
}
