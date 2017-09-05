package org.mrpaulwoods.instaboot.image

import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageNotFoundException
import org.mrpaulwoods.instaboot.image.ImageRepository
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.post.Post
import spock.lang.Specification

class ImageServiceSpec extends Specification {

    ImageRepository imageRepository = Mock(ImageRepository)

    ImageService service = new ImageService(imageRepository)

    Image image1 = new Image(id:100)
    Post post1 = new Post(id:200)

    def setup() {
        0 * _
    }

    def "create delegates to the repository"() {
        when:
        def ret = service.create(image1)

        then:
        1 * imageRepository.save(image1) >> image1

        and:
        ret == image1
    }

    def "read delegates to the repository"() {
        when:
        def ret = service.read(100)

        then:
        1 * imageRepository.findOne(100) >> image1

        and:
        ret == image1
    }

    def "fetch (not found) throws exception"() {

        when:
        service.fetch(100)

        then:
        1 * imageRepository.findOne(100) >> null

        and:
        thrown ImageNotFoundException
    }

    def "fetch (success) returns the image"() {

        when:
        def ret = service.fetch(100)

        then:
        1 * imageRepository.findOne(100) >> image1

        and:
        ret == image1
    }

    def "update delegates to the repository"() {
        when:
        service.update(image1)

        then:
        1 * imageRepository.save(image1) >> image1
    }

    def "delete delegates to the repository"() {
        when:
        service.delete(100)

        then:
        1 * imageRepository.delete(100)
    }

    def "list delegates to the repository"() {
        when:
        def ret = service.list()

        then:
        1 * imageRepository.findAll() >> [image1]

        and:
        ret == [image1]
    }

    def "fetchThumbnailImage delegates to the repository"() {
        when:
        def ret = service.fetchThumbnailImage(post1)

        then:
        1 * imageRepository.findByPostAndImageType(post1, ImageType.THUMBNAIL) >> image1

        and:
        ret == image1
    }

    def "fetchOriginalImage delegates to the repository"() {
        when:
        def ret = service.fetchOriginalImage(post1)

        then:
        1 * imageRepository.findByPostAndImageType(post1, ImageType.ORIGINAL) >> image1

        and:
        ret == image1
    }

}
