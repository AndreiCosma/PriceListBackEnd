package com.csm.domain.entity

import javax.persistence.Entity


/*
* Created by I503342 - 20/03/2019
*/
@Entity
class Client(
        baseEntityId: Long,
        val clientUUID: String,
        val clientSecret: String
) : BaseEntity(baseEntityId)