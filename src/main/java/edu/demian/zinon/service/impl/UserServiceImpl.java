package edu.demian.zinon.service.impl;

import edu.demian.zinon.entity.User;
import edu.demian.zinon.repository.UserRepository;
import edu.demian.zinon.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
