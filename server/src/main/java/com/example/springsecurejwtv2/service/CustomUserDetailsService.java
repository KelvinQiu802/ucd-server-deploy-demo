package com.example.springsecurejwtv2.service;

import com.example.springsecurejwtv2.model.UserEntity;
import com.example.springsecurejwtv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> result = userRepository.findById(userName);
        UserEntity userEntity = result.orElseThrow(
                () -> new UsernameNotFoundException("Can not Find User with Name: " + userName)
        );
        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ADMIN"))
        );
    }

}
