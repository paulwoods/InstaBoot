package org.mrpaulwoods.instaboot.post

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional
class PostService {

    private PostRepository postRepository

    PostService(
            PostRepository postRepository
    ) {
        this.postRepository = postRepository
    }

    Post create(Post post) {
        postRepository.save post
    }

    Post read(Long id) {
        postRepository.findOne(id)
    }

    Post fetch(Long id) {
        Post post = read(id)
        if (!post) {
            throw new PostNotFoundException()
        }
        post
    }

    void update(Post post) {
        postRepository.save post
    }

    void delete(Long id) {
        postRepository.delete id
    }

    List<Post> list() {
        postRepository.findAll()
    }

}
