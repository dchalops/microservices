package com.microservice.roomservice.service;

import com.microservice.roomservice.model.Habitacion;
import com.microservice.roomservice.repository.HabitacionRepository;
import com.microservice.roomservice.exception.NotFoundException;
import com.microservice.roomservice.request.HabitacionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitacionService {
    private final HabitacionRepository habitacionRepository;

    private final String UPLOAD_DIR = "uploads/";

    public Habitacion saveHabitacion(HabitacionRequest habitacionRequest, MultipartFile imagen) throws IOException {
        String imagenUrl = saveImage(imagen);
        Habitacion habitacion = Habitacion.builder()
                .nombre(habitacionRequest.getNombre())
                .descripcion(habitacionRequest.getDescripcion())
                .precioBase(habitacionRequest.getPrecioBase())
                .numeroCamas(habitacionRequest.getNumeroCamas())
                .numeroPersonas(habitacionRequest.getNumeroPersonas())
                .imagenUrl(imagenUrl)
                .tipo(habitacionRequest.getTipo())
                .build();
        return habitacionRepository.save(habitacion);
    }

    public List<Habitacion> getAll() {
        return habitacionRepository.findAll();
    }

    public Habitacion getHabitacionById(String id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Habitacion not found"));
    }

    public Habitacion updateHabitacion(HabitacionRequest habitacionRequest, MultipartFile imagen) throws IOException {
        Habitacion habitacion = habitacionRepository.findById(habitacionRequest.getId())
                .orElseThrow(() -> new NotFoundException("Habitacion not found"));

        if (!imagen.isEmpty()) {
            String imagenUrl = saveImage(imagen);
            habitacion.setImagenUrl(imagenUrl);
        }

        habitacion.setNombre(habitacionRequest.getNombre());
        habitacion.setDescripcion(habitacionRequest.getDescripcion());
        habitacion.setPrecioBase(habitacionRequest.getPrecioBase());
        habitacion.setNumeroCamas(habitacionRequest.getNumeroCamas());
        habitacion.setNumeroPersonas(habitacionRequest.getNumeroPersonas());
        habitacion.setTipo(habitacionRequest.getTipo());

        return habitacionRepository.save(habitacion);
    }

    public void deleteHabitacion(String id) {
        habitacionRepository.deleteById(id);
    }

    private String saveImage(MultipartFile imagen) throws IOException {
        if (imagen.isEmpty()) {
            return null;
        }

        String imagenId = UUID.randomUUID().toString();
        Path imagenPath = Paths.get(UPLOAD_DIR + imagenId + "_" + imagen.getOriginalFilename());
        Files.createDirectories(imagenPath.getParent());
        Files.write(imagenPath, imagen.getBytes());

        return imagenPath.toString();
    }
}
