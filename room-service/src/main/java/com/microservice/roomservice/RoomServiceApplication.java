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
        Habitacion habitacion = new Habitacion("Habitación 101", "Descripción de la Habitación 101", 100.0, 2, 4);
        if (habitacionRepository.findByNombre("Habitación 101").isEmpty()) {
            habitacionRepository.save(habitacion);
        }
    }
}
