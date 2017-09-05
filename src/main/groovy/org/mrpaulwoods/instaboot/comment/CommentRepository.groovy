package org.mrpaulwoods.instaboot.comment

import org.mrpaulwoods.instaboot.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post)
}