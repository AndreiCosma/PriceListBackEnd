package com.csm.domain.entity

import java.util.*
import javax.persistence.*


/*
* Created by I503342 - 19/03/2019
*/
@Entity
@Table(name = "registration")
class Registration(
        @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH, CascadeType.PERSIST], mappedBy = "registration", optional = false)
        val user: User,
        @Column(name = "registration_date")
        var registrationDate: Date,
        @Column(name = "activation_date")
        var activationDate: Date,
        @Column(name = "active")
        var active: Boolean
) : BaseEntity()