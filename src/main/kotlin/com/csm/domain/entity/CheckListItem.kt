package com.csm.domain.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne


/*
* Created by I503342 - 21/03/2019
*/
@Entity
class CheckListItem(
        baseEntityId: Long,
        val name: String,
        val checked: Boolean,
        @ManyToOne(cascade = [CascadeType.DETACH]) val checkList: CheckList
) : BaseEntity(baseEntityId)