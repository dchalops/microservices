package com.microservice.userservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedResponse<T> {
    private List<T> rows;
    private int total_page;
    private int current_page;
}
