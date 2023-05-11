package com.example.codefellowship.repos;

import com.example.codefellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

}