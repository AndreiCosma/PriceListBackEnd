package com.csm.domain.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne


/*
* Created by I503342 - 20/03/2019
*/
@Entity
class Email(
        baseEntityId: Long,
        @ManyToOne(cascade = [CascadeType.DETACH], fetch = FetchType.EAGER)
        val user: User,
        val email: String,
        val primary: Boolean
) : BaseEntity(baseEntityId)