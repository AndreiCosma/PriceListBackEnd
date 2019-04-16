package com.csm.service.impl

import com.csm.domain.dto.CheckListDTO
import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.CheckList
import com.csm.domain.entity.CheckListItem
import com.csm.domain.entity.User
import com.csm.domain.repo.CheckListRepo
import com.csm.domain.repo.UserRepo
import com.csm.exception.OdataException
import com.csm.service.def.CheckListService
import com.csm.service.def.UserService
import org.springframework.stereotype.Service


/*
* Created by I503342 - 21/03/2019
*/
@Service
class CheckListServiceImpl(
        val checkListRepo: CheckListRepo,
        val userRepo: UserRepo
) : CheckListService {

    override fun createCheckList(user: User): CheckListDTO {
        //Create List
        val checkList = CheckList(
                baseEntityId = 1L,
                name = "New Check List",
                items = mutableListOf(),
                users = mutableListOf(user)
        )
        user.lists.add(checkList)
        userRepo.save(user)
        //Return list
        return checkListRepo.save(checkList).toDTO()
    }

    override fun saveRemoteCreatedCheckList(checkListDTO: CheckListDTO, user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCheckList(id: Long, user: User): CheckListDTO = checkListRepo.findByIdAndUser(id = id, user = user).toDTO()

    override fun getCheckLists(user: User): List<CheckListDTO> = checkListRepo.findByUser(user = user).toDTO()

    override fun updateCheckList(checkListDTO: CheckListDTO, user: User) {
        // Get list from database.
        val checkList = checkListRepo.findByIdAndUser(id = checkListDTO.id, user = user)

        //ToDo: Update only fields that are not null in the DTO

        //Save list
        checkListRepo.save(checkList)
    }

    override fun deleteCheckList(id: Long, user: User) = checkListRepo.deleteByIdAndUser(id = id, user = user)

    private fun CheckList.toDTO() = CheckListDTO(
            id = this.id,
            name = this.name,
            items = this.items.toDTO(this.id)
    )

    private fun MutableList<CheckListItem>.toDTO(parentId: Long): MutableList<CheckListItemDTO> = this.map { element -> element.toDTO(parentId) }.toMutableList()

    private fun CheckListItem.toDTO(parentId: Long) = CheckListItemDTO(
            listId = parentId,
            id = this.id,
            name = this.name,
            checked = this.checked
    )

    fun List<CheckList>.toDTO() = this.map { element -> element.toDTO() }

}