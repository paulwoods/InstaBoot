package org.mrpaulwoods.instaboot.upload

import groovy.transform.Canonical
import org.springframework.web.multipart.MultipartFile

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Canonical
class UploadForm implements Serializable {

    private static final long serialVersionUID = 0

    MultipartFile content

    @NotNull
    @Size(min=1, max=2000)
    String text

    boolean hasContent() {
        !content.empty
    }

}
