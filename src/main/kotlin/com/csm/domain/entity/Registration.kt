package com.csm.domain.entity

import java.util.*
import javax.persistence.*


/*
* Created by I503342 - 19/03/2019
*/
@Entity
@Table(name = "registration")
class Registration(
        @Column(name = "uuid") @Id val id: String,
        @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH, CascadeType.PERSIST])
        @JoinColumn(name = "app_user_id")
        val user: User,
        @Column(name = "registration_date")
        val registrationDate: Date,
        @Column(name = "activation_date")
        private val activationDate: Date,
        @Column(name = "active")
        val active: Boolean
)