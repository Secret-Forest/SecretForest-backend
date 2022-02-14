package com.example.secretforest_project.Entity.Comments;

import com.example.secretforest_project.Entity.Post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    Page<Comment> findAllByCensorshipOrderByIdDesc(Integer Censorship, Pageable page);
    List<Comment> findAllByPost(Post id);

}
