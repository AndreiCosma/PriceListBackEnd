package com.csm.domain.entity

import javax.persistence.*


/*
* Created by I503342 - 21/03/2019
*/
@Entity
class CheckList(
        baseEntityId: Long,
        val name: String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true) val items: MutableList<CheckListItem>,
        @ManyToOne(cascade = [CascadeType.DETACH], fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User
) : BaseEntity(baseEntityId)