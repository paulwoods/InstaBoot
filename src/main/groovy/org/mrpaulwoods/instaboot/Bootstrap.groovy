package org.mrpaulwoods.instaboot

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.action.UploadAction
import org.mrpaulwoods.instaboot.comment.Comment
import org.mrpaulwoods.instaboot.comment.CommentService
import org.mrpaulwoods.instaboot.post.Post
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Slf4j
@Service
@Transactional
class Bootstrap {

    private UploadAction uploadAction
    private CommentService commentService

    Bootstrap(
            UploadAction uploadAction,
            CommentService commentService
    ) {
        this.uploadAction = uploadAction
        this.commentService = commentService
    }

    @PostConstruct
    void init() {

        Post post = uploadAction.execute(
                "01 - all code is guilty until proven innocent.png",
                "image/png",
                new File("C:\\Users\\mrpau\\Downloads\\pictures\\01 - all code is guilty until proven innocent.png").bytes,
                "The first image for the demo."
        )

        commentService.create(new Comment(
                post: post,
                text: "This is the first comment."
        ))

        commentService.create(new Comment(
                post: post,
                text: "This is the second comment."
        ))

        commentService.create(new Comment(
                post: post,
                text: "This is the third comment."
        ))

        commentService.create(new Comment(
                post: post,
                text: "This is the fourth comment."
        ))

    }

}
