package com.example.forestproject.Entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    List<PostEntity> findAllByTitleLike(String title);
    List<PostEntity> findAllByWriter(String writer);
    List<PostEntity> findAllBy();

}
