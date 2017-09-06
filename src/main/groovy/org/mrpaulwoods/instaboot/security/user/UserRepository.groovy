package org.mrpaulwoods.instaboot.security.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username)
}