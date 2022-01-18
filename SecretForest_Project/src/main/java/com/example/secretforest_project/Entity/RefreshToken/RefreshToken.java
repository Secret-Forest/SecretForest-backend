package com.example.secretforest_project.Entity.RefreshToken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    private String id;

    private String refreshToken;

    public RefreshToken update(String token) {
        this.refreshToken = token;
        return this;
    }

}
