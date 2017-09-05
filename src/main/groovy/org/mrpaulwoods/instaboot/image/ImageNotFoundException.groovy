package org.mrpaulwoods.instaboot.image

class ImageNotFoundException extends RuntimeException {
    ImageNotFoundException() {
        super("The image was not found.")
    }
}
