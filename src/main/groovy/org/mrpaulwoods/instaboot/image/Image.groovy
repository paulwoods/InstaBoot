package org.mrpaulwoods.instaboot.image

import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.security.user.User

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne

@Entity
class Image implements Serializable {

    private static final long serialVersionUID = 0

    @GeneratedValue
    @Id
    Long id

    @ManyToOne
    @JoinColumn(name="user_id")
    User user

    @ManyToOne
    @JoinColumn(name="post_id")
    Post post

    @Lob
    @Column(length = 4194304)
    // 4MB
    byte[] content

    @Column(length = 300)
    String name

    @Column(length = 200)
    String contentType

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    ImageType imageType
}
