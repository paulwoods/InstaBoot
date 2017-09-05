package org.mrpaulwoods.instaboot.security.authority

class AuthorityNotFoundException extends RuntimeException {
    AuthorityNotFoundException() {
        super("The authority was not found.")
    }
}
