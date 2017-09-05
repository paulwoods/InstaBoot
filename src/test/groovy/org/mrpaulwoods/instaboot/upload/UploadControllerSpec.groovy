package org.mrpaulwoods.instaboot.upload

import org.mrpaulwoods.instaboot.action.UploadAction
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.post.Post
import org.springframework.ui.Model
import spock.lang.Specification

class UploadControllerSpec extends Specification {

    ImageService imageService = Mock(ImageService)
    UploadAction uploadAction = Mock(UploadAction)

    UploadController controller = new UploadController(imageService, uploadAction)

    Model model = Mock(Model)

    UploadForm uploadForm1 = new UploadForm()
    Post post1 = new Post()

    def "index puts a uploadForm in the model and returns the upload/index view"() {

        when:
        def ret = controller.index(model)

        then:
        1 * model.addAttribute("uploadForm", _)

        and:
        ret == "upload/index"
    }

    def "create delegates to the upload action and redirects to the home page"() {

        when:
        def ret = controller.create(uploadForm1)

        then:
        1 * uploadAction.execute(uploadForm1) >> post1

        and:
        ret == "redirect:/"
    }

}
