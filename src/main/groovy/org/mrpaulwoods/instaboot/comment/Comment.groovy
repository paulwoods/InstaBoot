package org.mrpaulwoods.instaboot.comment

import org.mrpaulwoods.instaboot.post.Post
import org.mrpaulwoods.instaboot.security.user.User

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Comment implements Serializable {

    private static final long serialVersionUID = 0

    @GeneratedValue
    @Id
    Long id

    @ManyToOne
    @JoinColumn(name="user_id")
    User user

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post

    @Column(length = 2000)
    String text
}
