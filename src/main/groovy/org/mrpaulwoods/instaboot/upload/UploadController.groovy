package org.mrpaulwoods.instaboot.upload

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.action.UploadAction
import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.post.Post
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

import javax.validation.Valid

@Slf4j
@Controller
@RequestMapping("/upload")
class UploadController {

    private ImageService imageService
    private UploadAction uploadAction

    UploadController(
            ImageService imageService,
            UploadAction uploadAction
    ) {
        this.imageService = imageService
        this.uploadAction = uploadAction
    }

    @GetMapping
    String index(final Model model) {
        model.addAttribute "uploadForm", new UploadForm()
        "upload/index"
    }

    @PostMapping("/create")
    String create(@Valid UploadForm uploadForm) {
        Post post = uploadAction.execute(uploadForm)
        "redirect:/"
    }

}
