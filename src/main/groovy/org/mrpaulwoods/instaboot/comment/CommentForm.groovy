package org.mrpaulwoods.instaboot.comment

import groovy.transform.Canonical

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Canonical
class CommentForm implements Serializable {

    private static final long serialVersionUID = 0

    @NotNull
    Long postId

    @NotNull
    @Size(min = 1, max = 2000)
    String text
}
