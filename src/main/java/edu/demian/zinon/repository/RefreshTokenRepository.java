package edu.demian.zinon.repository;

import edu.demian.zinon.entity.RefreshToken;
import edu.demian.zinon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Integer deleteByUser(User user);

}