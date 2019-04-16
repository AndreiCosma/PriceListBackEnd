package com.csm.domain.entity

import javax.persistence.*


/*
* Created by I503342 - 21/03/2019
*/
@Entity
@Table(name = "check_list_item")
class CheckListItem(
        baseEntityId: String,
        @Column(name = "name", length = 128)
        val name: String,
        @Column(name = "checked")
        val checked: Boolean,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "check_list_id")
        val checkList: CheckList
) : BaseEntity(baseEntityId)