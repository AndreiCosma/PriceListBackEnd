package com.csm.domain.entity

import javax.persistence.*


/*
* Created by I503342 - 20/03/2019
*/
@Entity
@Table(name = "email")
class Email(
        baseEntityId: Long,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
        @JoinColumn(name = "app_user_id")
        val user: User,
        @Column(name = "name")
        val emailName: String,
        @Column(name = "primary_email")
        val primaryEmail: Boolean
) : BaseEntity(baseEntityId)