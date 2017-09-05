package org.mrpaulwoods.instaboot.comment

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.post.Post
import org.springframework.stereotype.Service

import javax.transaction.Transactional

@Slf4j
@Service
@Transactional
class CommentService {

    private CommentRepository commentRepository

    CommentService(
            CommentRepository commentRepository
    ) {
        this.commentRepository = commentRepository
    }

    Comment create(Comment comment) {
        commentRepository.save comment
    }

    Comment read(Long id) {
        commentRepository.findOne(id)
    }

    Comment fetch(Long id) {
        Comment comment = read(id)
        if (!comment) {
            throw new CommentNotFoundException()
        }
        comment
    }

    void upload(Comment comment) {
        commentRepository.save comment
    }

    void delete(Long id) {
        commentRepository.delete id
    }

    List<Comment> list() {
        commentRepository.findAll()
    }

    List<Comment> findAllByPost(Post post) {
        commentRepository.findAllByPost(post)
    }
}
