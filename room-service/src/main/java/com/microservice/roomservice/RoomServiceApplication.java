package com.microservice.roomservice;

import com.microservice.roomservice.model.Habitacion;
import com.microservice.roomservice.repository.HabitacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class RoomServiceApplication implements CommandLineRunner {
    private final HabitacionRepository habitacionRepository;

    public RoomServiceApplication(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RoomServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Habitacion habitacion = Habitacion.builder()
                .nombre("Habitación 101")
                .descripcion("Descripción de la Habitación 101")
                .precioBase(100.0)
                .numeroCamas(2)
                .numeroPersonas(4)
                .build();
        
        if (habitacionRepository.findByNombre("Habitación 101").isEmpty()) {
            habitacionRepository.save(habitacion);
        }
    }
}
