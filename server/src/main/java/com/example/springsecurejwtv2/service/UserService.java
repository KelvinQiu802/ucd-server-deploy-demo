package com.example.springsecurejwtv2.service;

import com.example.springsecurejwtv2.exception.UserNameAlreadyExistsException;
import com.example.springsecurejwtv2.model.RegisterRequest;
import com.example.springsecurejwtv2.model.UserDTO;
import com.example.springsecurejwtv2.model.UserEntity;
import com.example.springsecurejwtv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public void createNewUser(RegisterRequest registerRequest) throws UserNameAlreadyExistsException {
        Optional<UserEntity> result = userRepository.findById(registerRequest.getUserName());
        result.ifPresentOrElse(
                (u) -> {
                    throw new UserNameAlreadyExistsException(
                            String.format("User Name [%s] Already Exists", u.getName())
                    );
                },
                () -> userRepository.save(UserEntity.builder()
                        .name(registerRequest.getUserName())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .email(registerRequest.getEmail())
                        .build())
        );
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userEntity -> modelMapper.map(userEntity, UserDTO.class)).toList();
    }

}
