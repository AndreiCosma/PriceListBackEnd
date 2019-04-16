package com.csm.domain.entity

import javax.persistence.*


/*
* Created by I503342 - 20/03/2019
*/
@Entity
@Table(name = "email")
class Email(
        baseEntityId: String,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
        @JoinColumn(name = "app_user_id")
        val user: User,
        @Column(name = "name", length = 128)
        val emailName: String,
        @OneToOne(mappedBy = "mainEmail", cascade = [CascadeType.DETACH], optional = false)
        val mainEmail: User
) : BaseEntity(baseEntityId)