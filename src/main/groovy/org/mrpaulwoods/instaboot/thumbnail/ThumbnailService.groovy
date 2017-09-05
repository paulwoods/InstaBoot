package org.mrpaulwoods.instaboot.thumbnail

import groovy.util.logging.Slf4j
import net.coobird.thumbnailator.Thumbnails
import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.image.ImageService
import org.mrpaulwoods.instaboot.image.ImageType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional
class ThumbnailService {

    private ImageService imageService

    ThumbnailService(
            ImageService imageService
    ) {
        this.imageService = imageService
    }

    Image execute(Image image) {

        ByteArrayInputStream bais = new ByteArrayInputStream(image.content)
        ByteArrayOutputStream baos = new ByteArrayOutputStream()

        Thumbnails
                .of(bais)
                .size(120,120)
                .toOutputStream(baos)

        Image image2 = imageService.create(new Image(
                post: image.post,
                name: image.name,
                contentType: image.contentType,
                content: baos.toByteArray(),
                imageType: ImageType.THUMBNAIL
        ))

        image2
    }

}
