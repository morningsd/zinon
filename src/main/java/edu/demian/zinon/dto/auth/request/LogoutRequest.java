package edu.demian.zinon.dto.auth.request;

import lombok.Getter;

@Getter
public class LogoutRequest {

    private final Long userId;

    public LogoutRequest(Long userId) {
        this.userId = userId;
    }
}
