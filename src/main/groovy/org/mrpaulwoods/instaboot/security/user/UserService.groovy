package org.mrpaulwoods.instaboot.security.user

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional
class UserService {

    private UserRepository userRepository

    UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository
    }

    User create(User user) {
        userRepository.save user
    }

    User read(Long id) {
        userRepository.findOne(id)
    }

    User fetch(Long id) {
        User user = read(id)
        if (!user) {
            throw new UserNotFoundException()
        }
        user
    }

    void update(User user) {
        userRepository.save user
    }

    void delete(Long id) {
        userRepository.delete id
    }

    List<User> list() {
        userRepository.findAll()
    }

    User findByUsername(String userName) {
        userRepository.findByUsername(userName)
    }

}
