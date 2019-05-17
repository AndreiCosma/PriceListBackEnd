package com.csm.service.def

import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.User
import org.springframework.stereotype.Service


/*
* Created by I503342 - 21/03/2019
*/
@Service
interface ListItemService {
    fun requestNewItemForParentList(parentId: String, user: User): CheckListItemDTO
    fun persistRemoteItem(checkListItemDTO: CheckListItemDTO, user: User)
    fun getItem(itemId: String, user: User): CheckListItemDTO
    fun updateItem(checkListItemDTO: CheckListItemDTO, user: User)
    fun deleteItem(itemId: String, user: User)
}