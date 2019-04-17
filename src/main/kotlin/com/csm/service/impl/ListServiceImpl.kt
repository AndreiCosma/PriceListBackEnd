package com.csm.service.impl

import com.csm.domain.dto.CheckListDTO
import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.CheckList
import com.csm.domain.entity.CheckListItem
import com.csm.domain.entity.User
import com.csm.domain.repo.ListRepo
import com.csm.service.def.ListService
import org.springframework.stereotype.Service
import java.util.*


/*
* Created by I503342 - 21/03/2019
*/
@Service
class ListServiceImpl(
        val checkListRepo: ListRepo
) : ListService {

    override fun createCheckList(user: User): CheckListDTO {
        //Create List
        val checkList = CheckList(
                baseEntityId = UUID.randomUUID().toString(),
                name = "New Check List",
                items = mutableListOf(),
                owner = user,
                users = mutableListOf(user)
        )
        user.ownedLists.add(checkList)
        user.lists.add(checkList)
        //Return list
        return checkListRepo.save(checkList).toDTO()
    }

    override fun saveRemoteCreatedCheckList(checkListDTO: CheckListDTO, user: User) {
        val checkList = checkListDTO.toPersistable(user = user)
        checkList.items.addAll(checkListDTO.items.toPersistable(checkList))
        user.ownedLists.add(checkList)
        user.lists.add(checkList)
        checkListRepo.save(checkList)
    }

    override fun getCheckList(id: String, user: User): CheckListDTO = checkListRepo.findByIdAndUser(id = id, user = user).toDTO()

    override fun getCheckLists(user: User): List<CheckListDTO> = checkListRepo.findByUser(user = user).toDTO()

    override fun updateCheckList(checkListDTO: CheckListDTO, user: User) {
        // Get list from database.
        val checkList = checkListRepo.findByIdAndUser(id = checkListDTO.id, user = user)

        //ToDo: Update only fields that are not null in the DTO

        //Save list
        checkListRepo.save(checkList)
    }

    override fun deleteCheckList(id: String, user: User) = checkListRepo.deleteByIdAndUser(id = id, user = user)

    private fun CheckList.toDTO() = CheckListDTO(
            id = this.id,
            name = this.name,
            items = this.items.toDTO(this.id)
    )

    private fun MutableList<CheckListItem>.toDTO(parentId: String): MutableList<CheckListItemDTO> = this.map { element -> element.toDTO(parentId) }.toMutableList()

    private fun CheckListItem.toDTO(parentId: String) = CheckListItemDTO(
            listId = parentId,
            id = this.id,
            name = this.name,
            checked = this.checked
    )

    private fun List<CheckList>.toDTO() = this.map { element -> element.toDTO() }

    private fun CheckListDTO.toPersistable(user: User) = CheckList(
            baseEntityId = this.id,
            name = this.name,
            items = mutableListOf(),
            owner = user,
            users = mutableListOf(user)
    )

    private fun MutableList<CheckListItemDTO>.toPersistable(parent: CheckList) = this.map { item -> item.toPersistable(parent) }.toMutableList()

    private fun CheckListItemDTO.toPersistable(parent: CheckList) = CheckListItem(
            baseEntityId = UUID.randomUUID().toString(),
            name = this.name,
            checked = this.checked,
            checkList = parent
    )
}