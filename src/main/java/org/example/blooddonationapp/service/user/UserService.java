package org.example.blooddonationapp.service.user;

import org.example.blooddonationapp.controller.user.dto.GetUserDto;
import org.example.blooddonationapp.controller.user.dto.UpdateUserDto;
import org.example.blooddonationapp.infrastructure.entity.AuthEntity;
import org.example.blooddonationapp.infrastructure.entity.UserEntity;
import org.example.blooddonationapp.infrastructure.repository.AuthRepository;
import org.example.blooddonationapp.infrastructure.repository.DonorProfileRepository;
import org.example.blooddonationapp.infrastructure.repository.UserRepository;
import org.example.blooddonationapp.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final DonorProfileRepository donorProfileRepository;

    @Autowired
    public UserService(AuthRepository authRepository, UserRepository userRepository, DonorProfileRepository donorProfileRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.donorProfileRepository = donorProfileRepository;
    }

    public GetUserDto getUserByUsername(String username) {
        AuthEntity auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFound.createWithUsername(username));
        UserEntity user = auth.getUser();

        return new GetUserDto(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }

    public GetUserDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFound.createWithId(id));
        return new GetUserDto(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }

    @Transactional
    public void updateUserByUsername(String username, UpdateUserDto dto) {
        AuthEntity auth = authRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFound.createWithUsername(username));
        UserEntity user = auth.getUser();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
    }

    @Transactional
    public void updateUserById(Long userId, UpdateUserDto dto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFound.createWithId(userId));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
    }


    @Transactional
    public void deleteUserAndProfileById(Long userId) {
        // First delete donor profile if exists
        donorProfileRepository.deleteDonorProfileById(userId);

        // Then delete user
        userRepository.deleteById(userId);
    }

}
