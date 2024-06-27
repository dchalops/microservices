package com.microservice.jobservice.request.advert;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import com.microservice.jobservice.enums.AdvertStatus;

@Data
public class AdvertUpdateRequest {
    @NotBlank(message = "Advert id is required")
    private String id;
    private String name;
    private String description;
    private int deliveryTime;
    private int price;
    private AdvertStatus status;
}
