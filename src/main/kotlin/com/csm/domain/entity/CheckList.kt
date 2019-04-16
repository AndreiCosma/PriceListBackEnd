package com.csm.domain.entity

import javax.persistence.*


/*
* Created by I503342 - 21/03/2019
*/
@Entity
@Table(name = "check_list")
class CheckList(
        baseEntityId: String,
        @Column(name = "name", length = 128)
        val name: String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "checkList")
        val items: MutableList<CheckListItem>,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
        @JoinColumn(name = "owner_id")
        val owner: User,
        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH], mappedBy = "lists")
        val users: MutableList<User>
) : BaseEntity(baseEntityId)