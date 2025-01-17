package com.microservice.jobservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.jobservice.client.FileStorageClient;
import com.microservice.jobservice.exc.NotFoundException;
import com.microservice.jobservice.model.Category;
import com.microservice.jobservice.repository.CategoryRepository;
import com.microservice.jobservice.request.category.CategoryCreateRequest;
import com.microservice.jobservice.request.category.CategoryUpdateRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileStorageClient fileStorageClient;
    private final ModelMapper modelMapper;

    public Category createCategory(CategoryCreateRequest request, MultipartFile file) {
        String imageId = null;

        if (file != null)
            imageId = fileStorageClient.uploadImageToFIleSystem(file).getBody();

        return categoryRepository.save(
                Category.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .imageId(imageId)
                        .build());
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String id) {
        return findCategoryById(id);
    }

    public Category updateCategoryById(CategoryUpdateRequest request, MultipartFile file) {
        Category toUpdate = findCategoryById(request.getId());
        modelMapper.map(request, toUpdate);

        if (file != null) {
            String imageId = fileStorageClient.uploadImageToFIleSystem(file).getBody();
            if (imageId != null) {
                fileStorageClient.deleteImageFromFileSystem(toUpdate.getImageId());
                toUpdate.setImageId(imageId);
            }
        }

        return categoryRepository.save(toUpdate);
    }

    public void deleteCategoryById(String id) {
        categoryRepository.deleteById(id);
    }

    protected Category findCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }
}
