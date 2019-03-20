package com.csm.domain.entity

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne


/*
* Created by I503342 - 20/03/2019
*/
@Entity
class RefreshToken(
        baseEntityId: Long,
        @ManyToOne(cascade = [CascadeType.DETACH])
        val user: User,
        val refreshToken: String,
        val deviceUUID: String,
        val creationDate: Date
) : BaseEntity(baseEntityId)