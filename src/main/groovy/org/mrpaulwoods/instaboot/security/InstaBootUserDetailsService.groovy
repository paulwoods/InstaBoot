package org.mrpaulwoods.instaboot.security

import org.mrpaulwoods.instaboot.security.authority.Authority
import org.mrpaulwoods.instaboot.security.authority.AuthorityRepository
import org.mrpaulwoods.instaboot.security.user.User
import org.mrpaulwoods.instaboot.security.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Service
class InstaBootUserDetailsService { // implements UserDetailsService {

    @Autowired
    UserRepository userRepository

    @Autowired
    AuthorityRepository authorityRepository

//    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
        if (user)
            user
        else
            throw new UsernameNotFoundException(username)
    }

}
