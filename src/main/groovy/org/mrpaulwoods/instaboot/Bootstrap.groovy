package org.mrpaulwoods.instaboot

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.action.UploadAction
import org.mrpaulwoods.instaboot.comment.Comment
import org.mrpaulwoods.instaboot.comment.CommentService
import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.security.SecurityService
import org.mrpaulwoods.instaboot.security.authority.Authority
import org.mrpaulwoods.instaboot.security.authority.AuthorityService
import org.mrpaulwoods.instaboot.security.user.User
import org.mrpaulwoods.instaboot.security.user.UserService
import org.springframework.core.io.ClassPathResource
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Slf4j
@Service
@Transactional
class Bootstrap {

    private UploadAction uploadAction
    private CommentService commentService
    private SecurityService securityService

    Bootstrap(
            UploadAction uploadAction,
            CommentService commentService,
            SecurityService securityService
    ) {
        this.uploadAction = uploadAction
        this.commentService = commentService
        this.securityService = securityService
    }

    @PostConstruct
    void init() {

//        User user = securityService.register("mr.paul.woods@gmail.com", "123456")
//
//        Post post1 = uploadAction.execute(
//                "all code is guilty until proven innocent",
//                "image/png",
//                new ClassPathResource("01.png").inputStream.bytes,
//                "anonymous quote",
//                user
//        )
//
//        commentService.create(new Comment(
//                post: post1,
//                text: "This is the first comment.",
//                user: user
//        ))
//
//        commentService.create(new Comment(
//                post: post1,
//                text: "This is the second comment.",
//                user: user
//        ))
//
//        Post post2 = uploadAction.execute(
//                "hello world",
//                "image/png",
//                new ClassPathResource("02.png").inputStream.bytes,
//                "when a geek is born",
//                user
//        )
//
//        commentService.create(new Comment(
//                post: post2,
//                text: "This is the third comment.",
//                user: user
//        ))
//
//        commentService.create(new Comment(
//                post: post2,
//                text: "This is the fourth comment.",
//                user: user
//        ))

    }

}
