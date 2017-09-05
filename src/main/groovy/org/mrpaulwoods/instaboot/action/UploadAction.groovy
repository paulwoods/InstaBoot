package org.mrpaulwoods.instaboot.action

import groovy.util.logging.Slf4j
import net.coobird.thumbnailator.Thumbnails
import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.image.ImageType
import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.post.PostService
import org.mrpaulwoods.instaboot.security.user.User
import org.mrpaulwoods.instaboot.thumbnail.ThumbnailService
import org.mrpaulwoods.instaboot.upload.UploadForm
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional
class UploadAction {

    private ImageService imageService
    private PostService postService
    private ThumbnailService thumbnailService

    UploadAction(
            ImageService imageService,
            PostService postService,
            ThumbnailService thumbnailService
    ) {
        this.imageService = imageService
        this.postService = postService
        this.thumbnailService = thumbnailService
    }

    Post execute(UploadForm uploadForm, User user) {

        execute uploadForm.content.originalFilename,
                uploadForm.content.contentType,
                uploadForm.content.bytes,
                uploadForm.text,
                user
    }

    Post execute(String name, String contentType, byte[] content, String text, User user) {

        Post post = postService.create(new Post(
                text: text,
                user: user
        ))

        Image image = imageService.create(new Image(
                post: post,
                name: name,
                contentType: contentType,
                content: content,
                imageType: ImageType.ORIGINAL,
                user: user
        ))

        thumbnailService.execute image

        post
    }

}
