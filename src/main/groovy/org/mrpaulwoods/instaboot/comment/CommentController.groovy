package org.mrpaulwoods.instaboot.comment

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.post.PostService
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
    private CommentService commentService

    CommentController(
            PostService postService,
            CommentService commentService
    ) {
        this.postService = postService
        this.commentService = commentService
    }

    @RequestMapping("/save/{postId}")
    String save(@PathVariable Long postId, @Valid CommentForm commentForm, BindingResult result, final Model model) {
        Post post = postService.fetch(postId)
        if(result.hasErrors()) {
            model.addAttribute "post", post
            model.addAttribute "comments", commentService.findAllByPost(post)
            model.addAttribute "commentForm", new CommentForm(postId: post.id)
            "post/show"
        } else {
            commentService.create new Comment(post: post, text: commentForm.text)
            "redirect:/post/show/$postId"
        }
    }
}
