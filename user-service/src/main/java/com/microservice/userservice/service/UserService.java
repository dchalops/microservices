package com.microservice.userservice.service;

import com.microservice.userservice.client.FileStorageClient;
import com.microservice.userservice.dto.PaginatedResponse;
import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.enums.Active;
import com.microservice.userservice.enums.Role;
import com.microservice.userservice.exc.NotFoundException;
import com.microservice.userservice.model.User;
import com.microservice.userservice.model.UserDetails;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.request.RegisterRequest;
import com.microservice.userservice.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final FileStorageClient fileStorageClient;
    private final ModelMapper modelMapper;

    public User saveUser(RegisterRequest request) {
        User toSave = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .active(Active.ACTIVE).build();
        return userRepository.save(toSave);
    }

    public PaginatedResponse<UserDto> getAll(String keyword, int limit, int page, String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(direction, "username"));

        Page<User> usersPage = userRepository.findAllByActiveAndUsernameContaining(Active.ACTIVE, keyword, pageable);
        List<UserDto> users = usersPage.getContent().stream()
                .map(user -> {
                    UserDto.UserDtoBuilder userDtoBuilder = UserDto.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .status(user.getActive().name())
                            .verified(true);

                    if (user.getUserDetails() != null) {
                        userDtoBuilder
                                .phone(user.getUserDetails().getPhoneNumber())
                                .userDetails(user.getUserDetails());
                    }

                    return userDtoBuilder.build();
                })
                .collect(Collectors.toList());

        return PaginatedResponse.<UserDto>builder()
                .rows(users)
                .total_page(usersPage.getTotalPages())
                .current_page(usersPage.getNumber() + 1)
                .build();
    }

    public User getUserById(String id) {
        return findUserById(id);
    }

    public User getUserByEmail(String email) {
        return findUserByEmail(email);
    }

    public User getUserByUsername(String username) {
        return findUserByUsername(username);
    }

    public User updateUserById(UserUpdateRequest request) {
        User toUpdate = findUserById(request.getId());

        //request.setUserDetails(updateUserDetails(toUpdate.getUserDetails(), request.getUserDetails()));
        modelMapper.map(request, toUpdate);

        return userRepository.save(toUpdate);
    }

    public void deleteUserById(String id) {
        User toDelete = findUserById(id);
        toDelete.setActive(Active.INACTIVE);
        userRepository.save(toDelete);
    }

    protected User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    protected User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    protected User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private UserDetails updateUserDetails(UserDetails toUpdate, UserDetails request) {
        //toUpdate = toUpdate == null ? new UserDetails() : toUpdate;
        toUpdate =  null;

        /*if (file != null) {
            String profilePicture = fileStorageClient.uploadImageToFIleSystem(file).getBody();
            if (profilePicture != null) {
                fileStorageClient.deleteImageFromFileSystem(toUpdate.getProfilePicture());
                toUpdate.setProfilePicture(profilePicture);
            }
        }*/

        modelMapper.map(request, toUpdate);

        return toUpdate;
    }
}