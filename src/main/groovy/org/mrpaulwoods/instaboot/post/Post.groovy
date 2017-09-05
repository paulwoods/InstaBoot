package org.mrpaulwoods.instaboot.post

import org.mrpaulwoods.instaboot.image.Image

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Post implements Serializable {

    private static final long serialVersionUID = 0

    @GeneratedValue
    @Id
    Long id

    @Column(length=2000)
    String text
}

