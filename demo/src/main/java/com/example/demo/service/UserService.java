package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  private final UserRepository userRepository;
  public Object getUser;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<AppUser> getAllUsers() {
    logger.info("Fetching all users from the database");
    return userRepository.findAll();
  }

  public Optional<AppUser> getUserById(Long id) {
    logger.info("Fetching user with ID: {}", id);
    return userRepository.findById(id);
  }

  public AppUser createUser(AppUser user) {
    logger.info("Creating a new user: {}", user);
    return userRepository.save(user);
  }

  public AppUser updateUser(Long id, AppUser updatedUser) {
    logger.info("Updating user with ID: {}", id);
    updatedUser.setId(id);
    return userRepository.save(updatedUser);
  }

  public void deleteUser(Long id) {
    logger.info("Deleting user with ID: {}", id);
    userRepository.deleteById(id);
  }

  public void partiallyUpdateUser(Long id, AppUser updatedUser) {
    logger.info("Partially updating user with ID: {}", id);
    Optional<AppUser> existingUserOpt = userRepository.findById(id);
    if (existingUserOpt.isPresent()) {
      AppUser existingUser = existingUserOpt.get();
      if (updatedUser.getName() != null) {
        existingUser.setName(updatedUser.getName());
      }
      if (updatedUser.getEmail() != null) {
        existingUser.setEmail(updatedUser.getEmail());
      }
      userRepository.save(existingUser);
    }
  }
}