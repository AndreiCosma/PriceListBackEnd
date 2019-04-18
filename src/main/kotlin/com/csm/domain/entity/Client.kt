package com.csm.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


/*
* Created by I503342 - 20/03/2019
*/
@Entity
@Table(name = "client")
class Client(
        baseEntityId: String,
        @Column(name = "name", length = 36)
        val clientUUID: String,
        @Column(name = "password", length = 256)
        val clientSecret: String
) : BaseEntity(baseEntityId)