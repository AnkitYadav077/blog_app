package com.backend.blog_app.Repo;

import com.backend.blog_app.Entity.Category;
import com.backend.blog_app.Entity.Post;
import com.backend.blog_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
