package org.mrpaulwoods.instaboot.image

import spock.lang.Specification

import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletResponse

class ImageControllerSpec extends Specification {

    ImageService imageService = Mock(ImageService)

    ImageController controller = new ImageController(imageService)

    Image image1 = new Image(id:100, contentType: "application/jpg", content: "content".bytes)

    def setup() {
        0 * _
    }

    def "content sets the content type and returns the content"() {

        HttpServletResponse response = Mock(HttpServletResponse)
        ServletOutputStream sos = Mock(ServletOutputStream)

        when:
        controller.content image1.id, response

        then:
        1 * imageService.fetch(image1.id) >> image1
        1 * response.setContentType("application/jpg")
        1 * response.getOutputStream() >> sos
        1 * sos.write("content".bytes)
        1 * sos.flush()
    }

}
