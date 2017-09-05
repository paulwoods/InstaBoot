package org.mrpaulwoods.instaboot.user

import groovy.util.logging.Slf4j

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User {

    @GeneratedValue
    @Id
    Long id

    @Column(length=300, nullable=false)
    String username
}
