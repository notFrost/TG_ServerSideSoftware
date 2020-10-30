package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.model.User;
import com.opense.traininggain.domain.service.UserService;
import com.opense.traininggain.resource.SaveSpecialistResource;
import com.opense.traininggain.resource.SaveUserResource;
import com.opense.traininggain.resource.SpecialistResource;
import com.opense.traininggain.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @GetMapping("/users")

    @Operation(summary = "Get users", description = "Get All Users by Pages", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Users returned", content = @Content(mediaType = "application/json"))
    })
    public Page<UserResource> getAllUsers(Pageable pageable) {
        Page<User> userPage = userService.getAllUsers(pageable);
        List<UserResource> resources = userPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create user", description = "Create a new user", tags = {"Users"})
    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }

    @Operation(summary = "Update user", description = "Update user for given Id", tags = {"Users"})
    @PutMapping("/users/{usersId}")
    public UserResource updateUser(@PathVariable Long usersId, @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(usersId, user));
    }

    @Operation(summary = "Delete user", description = "Delete user with given Id", tags = {"Users"})
    @DeleteMapping("/users/{usersId}")
    public ResponseEntity<?> deleteSpecialist(@PathVariable Long usersId) {
        return userService.deleteUser(usersId);
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }


}
