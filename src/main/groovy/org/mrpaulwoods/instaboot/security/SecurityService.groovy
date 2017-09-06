package org.mrpaulwoods.instaboot.security

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.security.authority.Authority
import org.mrpaulwoods.instaboot.security.authority.AuthorityService
import org.mrpaulwoods.instaboot.security.user.User
import org.mrpaulwoods.instaboot.security.user.UserService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional
class SecurityService {

    private UserService userService
    private AuthorityService authorityService

    SecurityService(
            UserService userService,
            AuthorityService authorityService
    ) {
        this.userService = userService
        this.authorityService = authorityService
    }

    User getCurrentUser() {
        def user = userService.findByUsername(SecurityContextHolder.context.authentication.name)
        if(user instanceof User) {
            user
        } else {
            null
        }
    }

    User register(RegisterUserForm registerUserForm) {
        register registerUserForm.username, registerUserForm.password1
    }

    User register(String username, String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder()

        User user = userService.findByUsername(username)
        if (!user) {
            user = userService.create(new User(username: username, password: encoder.encode(password)))
            user.authorities << authorityService.create(new Authority(user: user, authority: "ROLE_USER"))
            userService.update user
        }

        user
    }

    void login(User user) {
        SecurityContextHolder.context.authentication = new UsernamePasswordAuthenticationToken(
                user.username,
                user.password,
                user.authorities)
    }

}
