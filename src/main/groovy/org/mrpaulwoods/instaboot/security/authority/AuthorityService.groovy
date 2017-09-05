package org.mrpaulwoods.instaboot.security.authority

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.security.authority.Authority
import org.mrpaulwoods.instaboot.security.authority.AuthorityNotFoundException
import org.mrpaulwoods.instaboot.security.authority.AuthorityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional
class AuthorityService {

    private AuthorityRepository authorityRepository

    AuthorityService(
            AuthorityRepository authorityRepository
    ) {
        this.authorityRepository = authorityRepository
    }

    Authority create(Authority authority) {
        authorityRepository.save authority
    }

    Authority read(Long id) {
        authorityRepository.findOne(id)
    }

    Authority fetch(Long id) {
        Authority authority = read(id)
        if (!authority) {
            throw new AuthorityNotFoundException()
        }
        authority
    }

    void update(Authority authority) {
        authorityRepository.save authority
    }

    void delete(Long id) {
        authorityRepository.delete id
    }

    List<Authority> list() {
        authorityRepository.findAll()
    }

}
