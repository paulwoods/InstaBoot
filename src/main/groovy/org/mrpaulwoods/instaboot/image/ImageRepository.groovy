package org.mrpaulwoods.instaboot.image

import org.mrpaulwoods.instaboot.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByPostAndImageType(Post post, ImageType imageType)
}
