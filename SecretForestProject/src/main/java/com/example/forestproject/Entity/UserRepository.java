package com.example.forestproject.Entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserid(String userid);
    Optional<UserEntity> findByName(String beforename);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByUserid(String userid);
    boolean existsByName(String name);
    boolean existsByEmail(String email);

}
