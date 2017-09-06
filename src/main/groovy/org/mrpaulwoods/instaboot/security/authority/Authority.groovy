package org.mrpaulwoods.instaboot.security.authority

import org.mrpaulwoods.instaboot.security.user.User
import org.springframework.security.core.GrantedAuthority

import javax.persistence.*

@Entity
class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    Long id

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user

    String authority

}
