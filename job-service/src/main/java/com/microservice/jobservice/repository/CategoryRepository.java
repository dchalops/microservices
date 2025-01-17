package com.microservice.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.jobservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
