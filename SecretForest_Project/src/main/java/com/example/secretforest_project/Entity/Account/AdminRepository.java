package com.example.secretforest_project.Entity.Account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Long> {

    Optional<Admin> findByAminId(String adminId);

}
