package com.microservice.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.jobservice.enums.Advertiser;
import com.microservice.jobservice.model.Advert;

import java.util.List;

public interface AdvertRepository extends JpaRepository<Advert, String> {
    List<Advert> getAdvertsByUserIdAndAdvertiser(String id, Advertiser advertiser);
}
