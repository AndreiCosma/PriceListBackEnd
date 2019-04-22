package com.csm.domain.entity

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import springfox.documentation.schema.property.BeanPropertyDefinitions.name


@Entity(name = "UserEntity")
@Table(name = "app_user")
class User(
        @Column(name = "username", length = 128)
        var usernameU: String,
        @Column(name = "password", length = 256)
        var passwordP: String,
        @Column(name = "enabled")
        var enabled: Boolean,
        @Column(name = "credentials_non_expired")
        var credentialsNonExpired: Boolean,
        @Column(name = "account_non_expired")
        var accountNonExpired: Boolean,
        @Column(name = "account_non_locked")
        var accountNonLocked: Boolean,
        @Column(name = "requires_two_factor")
        var requiresTwoFactor: Boolean,
        @ManyToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.EAGER)
        @JoinTable(
                name = "app_user_link_authority",
                joinColumns = [JoinColumn(name = "app_user_id")],
                inverseJoinColumns = [JoinColumn(name = "authority_id")]
        )
        var userAuthorities: MutableList<Authority>,
        @ManyToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.LAZY)
        @JoinTable(
                name = "check_list_link_app_user",
                joinColumns = [JoinColumn(name = "app_user_id")],
                inverseJoinColumns = [JoinColumn(name = "check_list_id")]
        )
        var lists: MutableList<CheckList>,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "owner")
        var ownedLists: MutableList<CheckList>,
        @OneToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
        var userRefreshTokens: MutableList<RefreshToken>,
        @OneToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
        var userEmails: MutableList<Email>,
        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "registration")
        var registration: Registration?,
        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "main_email")
        var mainEmail: Email?
) : BaseEntity(), UserDetails {

    override fun getAuthorities() = userAuthorities

    override fun isEnabled() = enabled

    override fun getUsername() = usernameU

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun getPassword() = passwordP

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked
}