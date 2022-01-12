package com.example.secretforest_project.Entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<CommentsEntity, Long> {

    Page<CommentsEntity> findAllByCnsrsOrderByIdDesc(Integer cnsrs, Pageable page);
    List<CommentsEntity> findAllByPost(PostEntity id);

}
