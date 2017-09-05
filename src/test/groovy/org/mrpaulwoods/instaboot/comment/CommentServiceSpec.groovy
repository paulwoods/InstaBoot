package org.mrpaulwoods.instaboot.comment

import org.mrpaulwoods.instaboot.post.Post
import spock.lang.Specification

class CommentServiceSpec extends Specification {

    CommentRepository commentRepository = Mock(CommentRepository)

    CommentService service = new CommentService(commentRepository)

    Comment comment1 = new Comment(id:100)
    Post post1 = new Post(id:200)

    def setup() {
        0 * _
    }

    def "create delegates to the repository"() {
        when:
        def ret = service.create(comment1)

        then:
        1 * commentRepository.save(comment1) >> comment1

        and:
        ret == comment1
    }

    def "read delegates to the repository"() {
        when:
        def ret = service.read(100)

        then:
        1 * commentRepository.findOne(100) >> comment1

        and:
        ret == comment1
    }

    def "fetch (not found) throws exception"() {

        when:
        service.fetch(100)

        then:
        1 * commentRepository.findOne(100) >> null

        and:
        thrown CommentNotFoundException
    }

    def "fetch (success) returns the comment"() {

        when:
        def ret = service.fetch(100)

        then:
        1 * commentRepository.findOne(100) >> comment1

        and:
        ret == comment1
    }

    def "upload delegates to the repository"() {
        when:
        service.upload(comment1)

        then:
        1 * commentRepository.save(comment1) >> comment1
    }

    def "delete delegates to the repository"() {
        when:
        service.delete(100)

        then:
        1 * commentRepository.delete(100)
    }

    def "list delegates to the repository"() {
        when:
        def ret = service.list()

        then:
        1 * commentRepository.findAll() >> [comment1]

        and:
        ret == [comment1]
    }

    def "findAllByPost delegates to the repository"() {
        when:
        def ret = service.findAllByPost(post1)

        then:
        1 * commentRepository.findAllByPost(post1) >> [comment1]

        and:
        ret == [comment1]
    }

}
