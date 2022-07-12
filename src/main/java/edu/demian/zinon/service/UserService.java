package edu.demian.zinon.service;

import edu.demian.zinon.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByEmail(String email);
}
