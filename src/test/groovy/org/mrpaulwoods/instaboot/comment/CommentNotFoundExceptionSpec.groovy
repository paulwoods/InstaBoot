package org.mrpaulwoods.instaboot.comment

import spock.lang.Specification

class CommentNotFoundExceptionSpec extends Specification {

    def "message is correct"() {
        expect:
        new CommentNotFoundException().message == "The comment was not found."
    }

}
