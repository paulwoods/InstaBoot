package org.mrpaulwoods.instaboot.post

import org.mrpaulwoods.instaboot.image.Image
import org.mrpaulwoods.instaboot.security.user.User

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(indexes = [ @Index(columnList = "user_id") ] )
class Post implements Serializable {

    private static final long serialVersionUID = 0

    @GeneratedValue
    @Id
    Long id

    @ManyToOne
    @JoinColumn(name="user_id")
    User user

    @Column(length=2000)
    String text
}

