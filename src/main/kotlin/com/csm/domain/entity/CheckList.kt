package com.csm.domain.entity

import javax.persistence.*


/*
* Created by I503342 - 21/03/2019
*/
@Entity
@Table(name = "check_list")
class CheckList(
        baseEntityId: Long,
        @Column(name = "name")
        val name: String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "checkList")
        val items: MutableList<CheckListItem>,
        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH], mappedBy = "lists")
        val users: MutableList<User>
) : BaseEntity(baseEntityId)