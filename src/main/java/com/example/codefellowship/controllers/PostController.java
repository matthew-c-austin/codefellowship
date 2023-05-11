package com.example.codefellowship.controllers;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.models.Post;
import com.example.codefellowship.repos.ApplicationUserRepository;
import com.example.codefellowship.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostRepository postRepository;

    @PostMapping("/createPost")
    public RedirectView createPost(Principal p, String body, long id, RedirectAttributes redir) {
        ApplicationUser user = applicationUserRepository.findById(id).orElseThrow();
        if(p != null) { //not strictly needed if WebSecurityConfig is set up properly
            Date date = new Date();
            Post post = new Post(body, date);
            user.addPost(post);
            postRepository.save(post);
            applicationUserRepository.save(user);
        } else {
            redir.addFlashAttribute("errorMessage", "You are not permitted to add posts to this profile!");
        }
        return new RedirectView("/myprofile");
    }

    @GetMapping("/feed")
    public String showFeed(Model m, Principal p) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("username", currentUser.getUsername());

        // This little method is to order the posts in descending order chronologically. Thanks ChatGPT
        List<Post> feedPosts = currentUser.getFollowing().stream()
                .flatMap(user -> user.getPosts().stream())
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
        m.addAttribute("feedPosts", feedPosts);
        return "feed";
    }

}
