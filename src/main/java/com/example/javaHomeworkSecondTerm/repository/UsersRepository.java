package com.example.javaHomeworkSecondTerm.repository;

import com.example.javaHomeworkSecondTerm.model.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, UUID> {

}
