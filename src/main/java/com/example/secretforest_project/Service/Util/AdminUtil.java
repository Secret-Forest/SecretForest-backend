package com.example.secretforest_project.Service.Util;

import com.example.secretforest_project.Entity.Admin.Admin;
import com.example.secretforest_project.Entity.Admin.AdminRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminUtil {

    private final AdminRepository accountRepository;
    private final AuthenticationUtil authenticationUtil;

    public Admin getAdmin() {
        return accountRepository.findByAminId(authenticationUtil.getAccountId())
                .orElseThrow(NotFoundException::new);
    }

}
