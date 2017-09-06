package org.mrpaulwoods.instaboot.upload

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.action.UploadAction
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.security.SecurityService
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
@RequestMapping("/upload")
class UploadController {

    private ImageService imageService
    private UploadAction uploadAction
    private SecurityService securityService

    UploadController(
            ImageService imageService,
            UploadAction uploadAction,
            SecurityService securityService
    ) {
        this.imageService = imageService
        this.uploadAction = uploadAction
        this.securityService = securityService
    }

    @GetMapping
    @PreAuthorize("hasRole(['ROLE_USER])")
    String index(final Model model) {
        model.addAttribute "uploadForm", new UploadForm()
        "upload/index"
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole(['ROLE_USER])")
    String create(@Valid UploadForm uploadForm, BindingResult result) {
        if (result.hasErrors()) {
            "upload/index"
        } else if (uploadForm.hasContent()) {
            "upload/index"
        } else {
            uploadAction.execute uploadForm, securityService.currentUser
            "redirect:/"
        }
    }

}
