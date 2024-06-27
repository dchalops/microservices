package com.microservice.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.jobservice.model.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, String> {
    List<Offer> getOffersByUserId(String id);

    List<Offer> getOffersByAdvertId(String id);
}
