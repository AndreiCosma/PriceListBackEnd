package com.csm.domain.entity

import javax.persistence.*


/*
* Created by I503342 - 21/03/2019
*/
@Entity
@Table(name = "check_list")
class CheckList(
        id: String,
        @Column(name = "name", length = 128)
        val name: String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "checkList")
        val items: MutableList<CheckListItem>,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH, CascadeType.PERSIST])
        @JoinColumn(name = "owner_id")
        val owner: User,
        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH, CascadeType.PERSIST], mappedBy = "lists")
        val users: MutableList<User>
) : BaseEntity(id)