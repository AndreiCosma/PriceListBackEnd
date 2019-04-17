package com.csm.service.impl

import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.CheckList
import com.csm.domain.entity.CheckListItem
import com.csm.domain.entity.User
import com.csm.domain.repo.ListItemRepo
import com.csm.domain.repo.ListRepo
import com.csm.exception.list.ListNotFoundException
import com.csm.service.def.ListItemService
import org.springframework.stereotype.Service
import java.util.*


/*
* Created by I503342 - 21/03/2019
*/
@Service
class ListItemServiceImpl(
        val checkListItemRepo: ListItemRepo,
        val checkListRepo: ListRepo
) : ListItemService {
    override fun requestNewItemForParentList(parentId: String, user: User) = checkListItemRepo.save(CheckListItem(baseEntityId = UUID.randomUUID().toString(), name = "New Item", checked = false, checkList = checkListRepo.findByIdAndUser(id = parentId, user = user)
            ?: throw ListNotFoundException("No List found."))).toDTO()

    override fun persistRemoteItem(checkListItemDTO: CheckListItemDTO, user: User) {
        checkListItemRepo.save(checkListItemDTO.toPersistable(checkListRepo.findByIdAndUser(id = checkListItemDTO.listId, user = user))
                ?: throw ListNotFoundException("List not found."))
    }

    override fun getItem(itemId: String, user: User) = checkListItemRepo.findByIdAndUser(id = itemId, user = user).toDTO()

    override fun updateItem(checkListItemDTO: CheckListItemDTO, user: User) {
        (checkListItemRepo.findByIdAndUser(id = checkListItemDTO.id, user = user)
                ?: throw ListNotFoundException("List not found.")).update(checkListItemDTO)

    }

    override fun deleteItem(itemId: String, parentId: String, user: User) {
        checkListItemRepo.deleteByIdAndUser(id = itemId, user = user)
    }

    private fun CheckListItem.toDTO() = CheckListItemDTO(
            listId = this.checkList.id,
            id = this.id,
            name = this.name,
            checked = this.checked
    )

    fun CheckListItemDTO.toPersistable(parent: CheckList) = CheckListItem(
            baseEntityId = UUID.randomUUID().toString(),
            name = this.name,
            checked = this.checked,
            checkList = parent
    )

    fun CheckListItem.update(dto: CheckListItemDTO) = CheckListItem(
            baseEntityId = this.id,
            name = dto.name,
            checked = dto.checked,
            checkList = this.checkList
    )
}