package org.mrpaulwoods.instaboot.home

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.post.PostService
import org.mrpaulwoods.instaboot.security.RegisterUserForm
import org.mrpaulwoods.instaboot.security.SecurityService
import org.mrpaulwoods.instaboot.security.user.User
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

import javax.validation.Valid

@Slf4j
@Controller
@RequestMapping
@PreAuthorize("hasRole(['ROLE_USER])")
class HomeController {

    private PostService postService
    private SecurityService securityService

    HomeController(
            PostService postService,
            SecurityService securityService
    ) {
        this.postService = postService
        this.securityService = securityService
    }

    @GetMapping
    String index(final Model model) {
        model.addAttribute "posts", postService.list()
        "home/index"
    }

    @GetMapping("/login")
    String show(Model model) {
        model.addAttribute "registerUserForm", new RegisterUserForm()
        "login"
    }

    @PostMapping("/register")
    String register(@Valid RegisterUserForm registerUserForm, BindingResult result) {
        if(result.hasErrors()) {
            "/login"
        } else {
            User user = securityService.register(registerUserForm)
            securityService.login user
            "redirect:/"
        }
    }

}
