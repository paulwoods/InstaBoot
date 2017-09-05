package org.mrpaulwoods.instaboot.security

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class RegisterUserForm implements Serializable {

    private static final long serialVersionUID = 0

    @NotNull
    @Size(min = 1, max=300)
    String username

    @NotNull
    @Size(min = 1, max=500)
    String password1

    @NotNull
    @Size(min = 1, max=500)
    String password2

}
