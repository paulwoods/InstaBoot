package org.mrpaulwoods.instaboot.comment

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.post.PostService
import org.mrpaulwoods.instaboot.security.SecurityService
import org.mrpaulwoods.instaboot.security.user.User
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.validation.Valid

@Slf4j
@Controller
@RequestMapping("/comment")
class CommentController {

    private PostService postService
    private ImageService imageService
    private CommentService commentService
    private SecurityService securityService

    CommentController(
            PostService postService,
            ImageService imageService,
            CommentService commentService,
            SecurityService securityService
    ) {
        this.postService = postService
        this.imageService = imageService
        this.commentService = commentService
        this.securityService = securityService
    }

    @RequestMapping("/save/{postId}")
    @PreAuthorize("hasRole(['ROLE_USER])")
    String save(@PathVariable Long postId, @Valid CommentForm commentForm, BindingResult result, final Model model) {
        Post post = postService.fetch(postId)
        if(result.hasErrors()) {
            model.addAttribute "post", post
            model.addAttribute "image", imageService.fetchOriginalImage(post)
            model.addAttribute "comments", commentService.findAllByPost(post)
            model.addAttribute "commentForm", new CommentForm(postId: post.id)
            "post/show"
        } else {
            commentService.create new Comment(
                    post: post,
                    text: commentForm.text,
                    user: securityService.currentUser)
            "redirect:/post/show/$postId"
        }
    }

}
