package com.example.secretforest_project.Entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    Optional<PostEntity> findById(Long post_id);
    Page<PostEntity> findAllByWriterOrderByWriter(String writer, Pageable page);
    Page<PostEntity> findAllByTitleContainingOrderByTitle(String title, Pageable page);
    Page<PostEntity> findAllByOrderByIdDesc(Pageable page);

    Page<PostEntity> findAllByCnsrsOrderByIdDesc(Integer cnsrs, Pageable page);

}
