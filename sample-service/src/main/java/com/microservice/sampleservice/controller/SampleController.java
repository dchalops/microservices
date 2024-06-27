package com.microservice.sampleservice.controller;

import com.microservice.sampleservice.dto.SampleDto;
import com.microservice.sampleservice.model.Sample;
import com.microservice.sampleservice.request.RegisterRequest;
import com.microservice.sampleservice.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<SampleDto> createSample(@RequestBody RegisterRequest request) {
        Sample sample = sampleService.saveSample(request);
        SampleDto sampleDto = modelMapper.map(sample, SampleDto.class);
        return new ResponseEntity<>(sampleDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SampleDto>> getAllSamples() {
        List<Sample> samples = sampleService.getAll();
        List<SampleDto> sampleDtos = samples.stream()
                .map(sample -> modelMapper.map(sample, SampleDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(sampleDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleDto> getSampleById(@PathVariable Long id) {
        Sample sample = sampleService.getSampleById(id);
        SampleDto sampleDto = modelMapper.map(sample, SampleDto.class);
        return ResponseEntity.ok(sampleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleDto> updateSample(@PathVariable Long id, @RequestBody RegisterRequest request) {
        Sample sample = sampleService.updateSampleById(id, request);
        SampleDto sampleDto = modelMapper.map(sample, SampleDto.class);
        return ResponseEntity.ok(sampleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSample(@PathVariable Long id) {
        sampleService.deleteSampleById(id);
        String response = "{\"message\": \"Sample deleted successfully\"}";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
