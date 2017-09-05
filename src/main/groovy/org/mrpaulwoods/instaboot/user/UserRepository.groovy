package org.mrpaulwoods.instaboot.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, Long> {
}
