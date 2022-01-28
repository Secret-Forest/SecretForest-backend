package com.example.secretforest_project.Jwt.Details;

import com.example.secretforest_project.Entity.Admin.AdminRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public Details loadUserByUsername(String adminId) throws UsernameNotFoundException {

        return adminRepository.findByAminId(adminId)
                .map(Details::new)
                .orElseThrow(NotFoundException::new);
    }

}
