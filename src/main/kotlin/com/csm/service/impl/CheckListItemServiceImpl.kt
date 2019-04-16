package com.csm.service.impl

import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.User
import com.csm.domain.repo.CheckListItemRepo
import com.csm.service.def.CheckListItemService
import org.springframework.stereotype.Service


/*
* Created by I503342 - 21/03/2019
*/
@Service
class CheckListItemServiceImpl(
        val checkListItemRepo: CheckListItemRepo
) : CheckListItemService {
    override fun requestNewItemForParentList(parentId: String, user: User): CheckListItemDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun persistItem(checkListItemDTO: CheckListItemDTO, user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(itemId: String, parentId: String, user: User): CheckListItemDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateItem(checkListItemDTO: CheckListItemDTO, user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItem(itemId: String, parentId: String, user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}