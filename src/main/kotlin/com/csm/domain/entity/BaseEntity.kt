package com.csm.domain.entity

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity(
        @Id
        @Column(name = "id")
        val id: String
)