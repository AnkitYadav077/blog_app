package com.backend.blog_app.Repo;

import com.backend.blog_app.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
