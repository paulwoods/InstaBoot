package org.mrpaulwoods.instaboot.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository extends JpaRepository<Post, Long> {

}