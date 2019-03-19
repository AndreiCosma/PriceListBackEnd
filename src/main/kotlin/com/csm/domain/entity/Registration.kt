package com.csm.domain.entity

import java.util.*
import javax.persistence.Entity


/*
* Created by I503342 - 19/03/2019
*/
@Entity
class Registration(
        id: Long,
        val userId: Long,
        val registrationUUID: String,
        val registrationDate: Date,
        private val activationDate: Date,
        val active: Boolean
) : BaseEntity(id)