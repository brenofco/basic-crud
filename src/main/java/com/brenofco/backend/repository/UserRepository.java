package com.brenofco.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brenofco.backend.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

}
