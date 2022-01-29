package com.example.secretforest_project.Entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {

    Optional<Post> findById(Long post_id);
    Page<Post> findAllByWriterOrderByWriter(String writer, Pageable page);
    Page<Post> findAllByTitleContainingOrderByTitle(String title, Pageable page);
    Page<Post> findAllByOrderByIdDesc(Pageable page);
    Page<Post> findAllByCnsrsOrderByIdDesc(Integer cnsrs, Pageable page);
    Page<Post> findAllByCnsrsBetweenOrderByIdDesc(Integer start, Integer end, Pageable page);

}
