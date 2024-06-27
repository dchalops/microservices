package com.microservice.filestorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.filestorage.model.File;

public interface FileRepository extends JpaRepository<File, String> {
}
