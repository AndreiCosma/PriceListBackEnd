package com.csm.domain.entity

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import springfox.documentation.schema.property.BeanPropertyDefinitions.name


@Entity(name = "UserEntity")
@Table(name = "app_user")
class User(
        id: String,
        @Column(name = "username")
        val usernameU: String,
        @Column(name = "password")
        val passwordP: String,
        @Column(name = "enabled")
        val enabled: Boolean,
        @Column(name = "credentials_non_expired")
        val credentialsNonExpired: Boolean,
        @Column(name = "account_non_expired")
        val accountNonExpired: Boolean,
        @Column(name = "account_non_locked")
        val accountNonLocked: Boolean,
        @Column(name = "requires_two_factor")
        val requiresTwoFactor: Boolean,
        @ManyToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.EAGER)
        @JoinTable(
                name = "app_user_link_authority",
                joinColumns = [JoinColumn(name = "app_user_id")],
                inverseJoinColumns = [JoinColumn(name = "authority_id")]
        )
        val userAuthorities: MutableList<Authority>,
        @ManyToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.LAZY)
        @JoinTable(
                name = "check_list_link_app_user",
                joinColumns = [JoinColumn(name = "app_user_id")],
                inverseJoinColumns = [JoinColumn(name = "check_list_id")]
        )
        val lists: MutableList<CheckList>,
        @OneToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
        val userRefreshTokens: MutableList<RefreshToken>,
        @OneToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
        val userEmails: MutableList<Email>,
        @OneToOne(cascade = [CascadeType.DETACH, CascadeType.PERSIST], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
        val registration: Registration?,
        @OneToOne(optional = false)
        @JoinColumn(name = "main_email")
        val mainEmail: Email?
) : BaseEntity(id), UserDetails {

    override fun getAuthorities() = userAuthorities

    override fun isEnabled() = enabled

    override fun getUsername() = usernameU

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun getPassword() = passwordP

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked
}