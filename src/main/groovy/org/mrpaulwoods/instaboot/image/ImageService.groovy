package org.mrpaulwoods.instaboot.image

import groovy.util.logging.Slf4j
import org.mrpaulwoods.instaboot.post.Post
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional
class ImageService {

    private ImageRepository imageRepository

    ImageService(
            ImageRepository imageRepository
    ) {
        this.imageRepository = imageRepository
    }

    Image create(Image image) {
        imageRepository.save image
    }

    Image read(Long id) {
        imageRepository.findOne(id)
    }

    Image fetch(Long id) {
        Image image = read(id)
        if(!image) {
            throw new ImageNotFoundException()
        }
        image
    }

    void update(Image image) {
        imageRepository.save image
    }

    void delete(Long id) {
        imageRepository.delete id
    }

    List<Image> list() {
        imageRepository.findAll()
    }

    Image fetchThumbnailImage(Post post) {
        imageRepository.findByPostAndImageType(post, ImageType.THUMBNAIL)
    }

    Image fetchOriginalImage(Post post) {
        imageRepository.findByPostAndImageType(post, ImageType.ORIGINAL)
    }

}
