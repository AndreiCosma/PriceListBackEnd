package com.csm.domain.entity

import java.util.*
import javax.persistence.*


/*
* Created by I503342 - 21/03/2019
*/
@Entity
@Table(name = "check_list")
class CheckList(
        id: String,
        @Column(name = "name", length = 128)
        var name: String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "checkList")
        val items: MutableList<CheckListItem>,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH, CascadeType.PERSIST])
        @JoinColumn(name = "owner_id")
        val owner: User,
        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH, CascadeType.PERSIST], mappedBy = "lists")
        val users: MutableList<User>,
        @Column(name = "creation_date")
        val creationDate: Date,
        @Column(name = "edit_date")
        var editDate: Date,
        @GeneratedValue
        @Column(name = "position")
        var position: Int = 1
) : BaseEntity(id)