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
        @Column(name = "name")
        val clientUUID: String,
        @Column(name = "password")
        val clientSecret: String
) : BaseEntity(baseEntityId)