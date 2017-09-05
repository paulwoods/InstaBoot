package org.mrpaulwoods.instaboot.post

class PostNotFoundException extends RuntimeException {
    PostNotFoundException() {
        super("The post was not found.")
    }
}
