package com.example.secretforest_project.Entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<CommentsEntity, Long> {

    List<CommentsEntity> findAllBy();

}
