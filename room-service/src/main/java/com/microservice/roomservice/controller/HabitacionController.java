package com.microservice.roomservice.controller;

import com.microservice.roomservice.model.Habitacion;
import com.microservice.roomservice.request.HabitacionRequest;
import com.microservice.roomservice.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/habitacion")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Habitacion> save(@RequestPart("habitacion") HabitacionRequest habitacionRequest,
                                           @RequestPart("imagen") MultipartFile imagen) throws IOException {
        return ResponseEntity.ok(habitacionService.saveHabitacion(habitacionRequest, imagen));
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
    public ResponseEntity<Habitacion> update(@RequestPart("habitacion") HabitacionRequest habitacionRequest,
                                             @RequestPart("imagen") MultipartFile imagen) throws IOException {
        return ResponseEntity.ok(habitacionService.updateHabitacion(habitacionRequest, imagen));
    }

    @DeleteMapping("/deleteHabitacion/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        habitacionService.deleteHabitacion(id);
        return ResponseEntity.ok().build();
    }
}

