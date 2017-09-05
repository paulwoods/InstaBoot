package org.mrpaulwoods.instaboot.home

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.post.PostService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Controller
@RequestMapping
class HomeController {

    private PostService postService

    HomeController(
            PostService postService
    ) {
        this.postService = postService
    }

    @GetMapping
    String index(final Model model) {
        model.addAttribute "posts", postService.list()
        "home/index"
    }

}
