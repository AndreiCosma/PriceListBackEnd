package com.csm.domain.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne


/*
* Created by I503342 - 20/03/2019
*/
@Entity
class Email(
        baseEntityId: Long,
        @ManyToOne(cascade = [CascadeType.DETACH])
        val user: User,
        val emailName: String,
        val primaryEmail: Boolean
) : BaseEntity(baseEntityId)