package org.mrpaulwoods.instaboot.image

import spock.lang.Specification

class ImageNotFoundExceptionSpec extends Specification {

    def "message is correct"() {
        expect:
        new ImageNotFoundException().message == "The image was not found."
    }

}
