package com.example.codefellowship.repos;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    public ApplicationUser findByUsername(String username);
    List<ApplicationUser> findAllByOrderByUsernameAsc();
}
