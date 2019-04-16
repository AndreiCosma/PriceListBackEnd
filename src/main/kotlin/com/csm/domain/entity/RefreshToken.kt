package com.csm.domain.entity

import java.util.*
import javax.persistence.*


/*
* Created by I503342 - 20/03/2019
*/
@Entity
@Table(name = "refresh_token")
class RefreshToken(
        baseEntityId: String,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
        @JoinColumn(name = "app_user_id")
        val user: User,
        @Column(name = "token")
        val refreshToken: String,
        @Column(name = "device_uuid")
        val deviceUUID: String,
        @Column(name = "creation_date")
        val creationDate: Date
) : BaseEntity(baseEntityId)