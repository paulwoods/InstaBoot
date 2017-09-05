package org.mrpaulwoods.instaboot.security.user

class UserNotFoundException extends RuntimeException {
    UserNotFoundException() {
        super("The user was not found.")
    }
}
