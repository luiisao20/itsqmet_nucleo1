package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itsqmet.entity.User;
import com.itsqmet.repository.UserRepository;
import com.itsqmet.roles.Role;

import lombok.Data;

@Service
@Data
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(UUID id) {
    return userRepository.findById(id);
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public User registerUser(User user) {
    String passwordEncoded = passwordEncoder.encode(user.getPassword());
    user.setPassword(passwordEncoded);
    user.setRole(Role.ROLE_USER);
    return userRepository.save(user);
  }

  public User updateUser(UUID id, User user) {
    User oldUser = getUserById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    oldUser.setEmail(user.getEmail());
    oldUser.setFirstName(user.getFirstName());
    oldUser.setLastName(user.getLastName());
    oldUser.setRole(user.getRole());

    if (user.getPassword() != null && !user.getPassword().trim().isEmpty()){
      oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    return userRepository.save(oldUser);
  }

  public void deleteUser(UUID id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    userRepository.delete(user);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    return org.springframework.security.core.userdetails.User
        .builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(user.getRole().name())
        .build();
  }
}
