package org.mrpaulwoods.instaboot.security.user

import org.mrpaulwoods.instaboot.security.authority.Authority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.*

@Entity
class User implements UserDetails {

    @Id
    @GeneratedValue
    Long id

    @Column(length=300, unique = true)
    String username

    @Column(length=500)
    String password

    boolean enabled = true

    boolean accountNonExpired = true

    boolean accountNonLocked = true

    boolean credentialsNonExpired = true

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Authority> authorities = []

}
