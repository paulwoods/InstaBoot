package org.mrpaulwoods.instaboot.upload

import org.springframework.web.multipart.MultipartFile

class UploadForm implements Serializable {

    private static final long serialVersionUID = 0

    MultipartFile content

    String text
}
