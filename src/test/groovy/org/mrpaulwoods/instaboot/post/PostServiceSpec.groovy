package org.mrpaulwoods.instaboot.post

import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.post.PostNotFoundException
import org.mrpaulwoods.instaboot.post.PostRepository
import org.mrpaulwoods.instaboot.post.PostService
import spock.lang.Specification

class PostServiceSpec extends Specification {

    PostRepository postRepository = Mock(PostRepository)

    PostService service = new PostService(postRepository)

    Post post1 = new Post(id:100)

    def setup() {
        0 * _
    }

    def "create delegates to the repository"() {
        when:
        def ret = service.create(post1)

        then:
        1 * postRepository.save(post1) >> post1

        and:
        ret == post1
    }

    def "read delegates to the repository"() {
        when:
        def ret = service.read(100)

        then:
        1 * postRepository.findOne(100) >> post1

        and:
        ret == post1
    }

    def "fetch (not found) throws exception"() {

        when:
        service.fetch(100)

        then:
        1 * postRepository.findOne(100) >> null

        and:
        thrown PostNotFoundException
    }

    def "fetch (success) returns the post"() {

        when:
        def ret = service.fetch(100)

        then:
        1 * postRepository.findOne(100) >> post1

        and:
        ret == post1
    }

    def "upload delegates to the repository"() {
        when:
        service.upload(post1)

        then:
        1 * postRepository.save(post1) >> post1
    }

    def "delete delegates to the repository"() {
        when:
        service.delete(100)

        then:
        1 * postRepository.delete(100)
    }

    def "list delegates to the repository"() {
        when:
        def ret = service.list()

        then:
        1 * postRepository.findAll() >> [post1]

        and:
        ret == [post1]
    }

}
