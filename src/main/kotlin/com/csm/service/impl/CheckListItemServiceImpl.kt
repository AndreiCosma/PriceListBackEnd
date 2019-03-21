package com.csm.service.impl

import com.csm.domain.dto.CheckListItemDTO
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
    override fun createCheckList(): CheckListItemDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveRemoteCreatedCheckListItem(checkListItemDTO: CheckListItemDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCheckListItem(id: Long): CheckListItemDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCheckListItems(checkListId: Long): List<CheckListItemDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateCheckListItem(checkListDTO: CheckListItemDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCheckListItem(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}