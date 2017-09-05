package org.mrpaulwoods.instaboot.comment

import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.post.PostService
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import spock.lang.Specification

class CommentControllerSpec extends Specification {

    PostService postService = Mock(PostService)
    CommentService commentService = Mock(CommentService)

    CommentController controller = new CommentController(postService, commentService)

    BindingResult result = Mock(BindingResult)
    Model model = Mock(Model)

    Post post1 = new Post(id: 100)
    CommentForm commentForm1 = new CommentForm(text: "the comment text")

    Comment comment1 = new Comment(id: 200)

    def setup() {
        0 * _
    }

    def "save (binding error) sets the model and returns the post/show view"() {

        when:
        def ret = controller.save(post1.id, commentForm1, result, model)

        then:
        1 * postService.fetch(post1.id) >> post1
        1 * result.hasErrors() >> true
        1 * model.addAttribute("post", post1)
        1 * commentService.findAllByPost(post1) >> [comment1]
        1 * model.addAttribute("comments", [comment1])
        1 * model.addAttribute("commentForm", _) >> { String k, CommentForm cf ->
            assert cf.postId == post1.id
        }

        and:
        ret == "post/show"
    }

    def "save (success) creates the comment and redirects to the post/show view"() {

        when:
        def ret = controller.save(post1.id, commentForm1, result, model)

        then:
        1 * postService.fetch(post1.id) >> post1
        1 * result.hasErrors() >> false
        1 * commentService.create(_) >> { Comment c ->
            assert c.post == post1
            assert c.text == "the comment text"
            c.id == 300
            c
        }

        and:
        ret == "redirect:/post/show/100"
    }

}
