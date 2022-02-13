package com.example.secretforest_project.Entity.Comments;

import com.example.secretforest_project.Entity.Post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comments, Long> {

    Page<Comments> findAllByCensorshipOrderByIdDesc(Integer Censorship, Pageable page);
    List<Comments> findAllByPost(Post id);

}
