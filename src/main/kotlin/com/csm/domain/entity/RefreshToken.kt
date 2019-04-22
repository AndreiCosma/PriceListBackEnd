package com.csm.domain.entity

import java.util.*
import javax.persistence.*


/*
* Created by I503342 - 20/03/2019
*/
@Entity
@Table(name = "refresh_token")
class RefreshToken(
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
        @JoinColumn(name = "app_user_id")
        var user: User,
        @Column(name = "token", length = 36)
        var refreshToken: String,
        @Column(name = "device_uuid", length = 36)
        var deviceUUID: String,
        @Column(name = "creation_date")
        var creationDate: Date
) : BaseEntity()