package org.mrpaulwoods.instaboot.comment

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class CommentForm implements Serializable {

    private static final long serialVersionUID = 0

    @NotNull
    Long postId

    @NotNull
    @Size(min = 1, max = 2000)
    String text
}
