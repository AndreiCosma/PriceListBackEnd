package com.csm.service.def

import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.User


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListItemService {
    fun requestNewItemForParentList(parentId: String, user: User): CheckListItemDTO
    fun persistItem(checkListItemDTO: CheckListItemDTO, user: User)
    fun getItem(itemId: String, parentId: String, user: User): CheckListItemDTO
    fun updateItem(checkListItemDTO: CheckListItemDTO, user: User)
    fun deleteItem(itemId: String, parentId: String, user: User)
}