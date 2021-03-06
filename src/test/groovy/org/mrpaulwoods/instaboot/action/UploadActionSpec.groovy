package org.mrpaulwoods.instaboot.action

import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.image.ImageType
import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.post.PostService
import org.mrpaulwoods.instaboot.security.user.User
import org.mrpaulwoods.instaboot.thumbnail.ThumbnailService
import org.mrpaulwoods.instaboot.upload.UploadForm
import org.springframework.core.io.ClassPathResource
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

class UploadActionSpec extends Specification {

    ImageService imageService = Mock(ImageService)
    PostService postService = Mock(PostService)
    ThumbnailService thumbnailService = Mock(ThumbnailService)

    UploadAction action = new UploadAction(imageService, postService, thumbnailService)

    User user1 = new User(id:100)

    def setup() {
        0 * _
    }

    def "execute creates the post, image and thumbnail"() {

        MultipartFile content = Mock(MultipartFile)

        UploadForm uploadForm = new UploadForm(
                content: content,
                text: "the post"
        )

        when:
        def ret = action.execute(uploadForm, user1)

        then:
        1 * content.getOriginalFilename() >> "01.png"
        1 * content.getContentType() >> "application/png"
        1 * content.getBytes() >> new ClassPathResource("01.png").inputStream.bytes
        1 * postService.create(_) >> { Post p ->
            assert p.text == "the post"
            p.id = 100
            p
        }

        1 * imageService.create(_) >> { Image i ->
            assert i.post.id == 100
            assert i.name == "01.png"
            assert i.contentType == "application/png"
            assert i.content != null
            assert i.imageType == ImageType.ORIGINAL
            i.id = 200
            i
        }

        1 * thumbnailService.execute(_)

        and:
        ret.id == 100
    }

}
