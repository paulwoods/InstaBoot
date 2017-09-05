package org.mrpaulwoods.instaboot.comment

class CommentNotFoundException extends RuntimeException {
    CommentNotFoundException() {
        super("The comment was not found.")
    }
}
