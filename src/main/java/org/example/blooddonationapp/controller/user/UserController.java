package org.example.blooddonationapp.controller.user;

import jakarta.validation.Valid;
import org.example.blooddonationapp.controller.user.dto.GetUserDto;
import org.example.blooddonationapp.controller.user.dto.UpdateUserDto;
import org.example.blooddonationapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getMe(Principal principal){
        String username = principal.getName();
        GetUserDto userDto = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GetUserDto> getUserById(@PathVariable Long id) {
        GetUserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateMe(Principal principal,
                                         @RequestBody @Valid UpdateUserDto dto) {
        userService.updateUserByUsername(principal.getName(), dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateUserById(@PathVariable Long id,
                                               @RequestBody @Valid UpdateUserDto dto) {
        userService.updateUserById(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserAndProfileById(id);
        return ResponseEntity.noContent().build();
    }


}
