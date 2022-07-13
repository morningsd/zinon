package edu.demian.zinon.service;

import edu.demian.zinon.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

}
