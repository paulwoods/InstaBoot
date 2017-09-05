package org.mrpaulwoods.instaboot.home

import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.post.PostService
import org.mrpaulwoods.instaboot.security.SecurityService
import org.springframework.ui.Model
import spock.lang.Specification

class HomeControllerSpec extends Specification {

    PostService postService = Mock(PostService)
    SecurityService securityService = Mock(SecurityService)

    HomeController controller = new HomeController(postService, securityService)

    Model model = Mock(Model)

    Post post1 = new Post(id:100)

    def "index puts the posts into the model, and returns the home/index view"() {

        when:
        def ret = controller.index(model)

        then:
        1 * postService.list() >> [post1]
        1 * model.addAttribute("posts", [post1])

        and:
        ret == "home/index"
    }

}
