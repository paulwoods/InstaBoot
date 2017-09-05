package org.mrpaulwoods.instaboot.thumbnail

import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.image.ImageType
import org.mrpaulwoods.instaboot.post.Post
import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

class ThumbnailServiceSpec extends Specification {

    ImageService imageService = Mock(ImageService)

    ThumbnailService service = new ThumbnailService(imageService)

    Post post1 = new Post(id:100)

    Image image1 = new Image(
            id:200,
            post: post1,
            content: new ClassPathResource("01.jpg").inputStream.bytes,
            name: "01.jpg",
            contentType: "application/jpg",
            imageType: ImageType.ORIGINAL
    )

    def setup() {
        0 * _
    }

    def "execute creates the thumbnail"() {

        when:
        def ret = service.execute(image1)

        then:

        1 * imageService.create(_) >> { Image i ->
            assert i.post.id == 100
            assert i.name == "01.jpg"
            assert i.contentType == "application/jpg"
            assert i.content != null
            assert i.imageType == ImageType.THUMBNAIL
            i.id = 300
            i
        }

        and:
        ret instanceof Image
    }

}
