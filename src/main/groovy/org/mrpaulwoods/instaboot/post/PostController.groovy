package org.mrpaulwoods.instaboot.post

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.comment.CommentForm
import org.mrpaulwoods.instaboot.comment.CommentService
import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Slf4j
@Controller
@RequestMapping("/post")
class PostController {

    private PostService postService
    private ImageService imageService
    private CommentService commentService

    PostController(
            PostService postService,
            ImageService imageService,
            CommentService commentService
    ) {
        this.postService = postService
        this.imageService = imageService
        this.commentService = commentService
    }

    @GetMapping("/show/{id}")
    String show(@PathVariable Long id, final Model model) {
        Post post = postService.fetch(id)
        model.addAttribute "post", post
        model.addAttribute "image", imageService.fetchOriginalImage(post)
        model.addAttribute "comments", commentService.findAllByPost(post)
        model.addAttribute "commentForm", new CommentForm(postId: post.id)
        "post/show"
    }

}
