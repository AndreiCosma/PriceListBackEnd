package com.csm.domain.entity

import java.util.*
import javax.persistence.*


/*
* Created by I503342 - 21/03/2019
*/
@Entity
@Table(name = "check_list_item")
class CheckListItem(
        id: String,
        @Column(name = "name", length = 128)
        var name: String,
        @Column(name = "checked")
        var checked: Boolean,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "check_list_id")
        var checkList: CheckList,
        @Column(name = "edit_date")
        var editDate: Date,
        @Column(name = "position")
        @GeneratedValue
        var position: Int = 1
) : BaseEntity(id)