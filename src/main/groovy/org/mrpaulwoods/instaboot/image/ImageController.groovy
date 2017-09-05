package org.mrpaulwoods.instaboot.image

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.post.PostService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.servlet.http.HttpServletResponse

@Slf4j
@Controller
@RequestMapping("/image")
class ImageController {

    private ImageService imageService

    ImageController(
            ImageService imageService
    ) {
        this.imageService = imageService
    }

    @GetMapping("/content/{id}")
    @PreAuthorize("permitAll")
    void content(@PathVariable Long id, HttpServletResponse response) {
        Image image = imageService.fetch(id)
        response.contentType = image.contentType
        response.outputStream << image.content
    }

}
