package org.mrpaulwoods.instaboot.security.authority

import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository extends JpaRepository<Authority, Long> {

}