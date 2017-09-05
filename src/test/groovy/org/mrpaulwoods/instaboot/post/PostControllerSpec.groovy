package org.mrpaulwoods.instaboot.post

import org.mrpaulwoods.instaboot.comment.Comment
import org.mrpaulwoods.instaboot.comment.CommentForm
import org.mrpaulwoods.instaboot.comment.CommentService
import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageService
import org.springframework.ui.Model
import spock.lang.Specification

class PostControllerSpec extends Specification {

    PostService postService = Mock(PostService)
    ImageService imageService = Mock(ImageService)
    CommentService commentService = Mock(CommentService)

    PostController controller = new PostController(postService, imageService, commentService)

    Model model = Mock(Model)

    Post post1 = new Post(id:100)
    Image image1 = new Image(post: post1)
    Comment comment1 = new Comment(post: post1)

    def setup() {
        0 * _
    }

    def "show puts the post, image, comments and commentForm into the model, and shows the view"() {

        when:
        def ret = controller.show(post1.id, model)

        then:
        1 * postService.fetch(post1.id) >> post1
        1 * model.addAttribute("post", post1)
        1 * imageService.fetchOriginalImage(post1) >> image1
        1 * model.addAttribute("image", image1)
        1 * commentService.findAllByPost(post1) >> [comment1]
        1 * model.addAttribute("comments", [comment1])
        1 * model.addAttribute("commentForm", _) >> { String s, CommentForm cf ->
            assert cf.postId == post1.id
        }

        and:
        ret == "post/show"
    }

}
