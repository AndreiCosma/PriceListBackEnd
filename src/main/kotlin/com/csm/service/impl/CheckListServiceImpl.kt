package com.csm.service.impl

import com.csm.domain.dto.CheckListDTO
import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.CheckList
import com.csm.domain.entity.CheckListItem
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

    override fun createCheckList(name: String): CheckListDTO {
        //Get user
        val user = userRepo.findByUsernameU(usernameU = name).get()
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

    override fun saveRemoteCreatedCheckList(checkListDTO: CheckListDTO, name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCheckList(id: Long, name: String): CheckListDTO = checkListRepo.findByIdAndUser(id, userRepo.findByUsernameU(name).get()).toDTO()

    override fun getCheckLists(name: String): List<CheckListDTO> = checkListRepo.findByUser(userRepo.findByUsernameU(name).get()).toDTO()

    override fun updateCheckList(checkListDTO: CheckListDTO, name: String) {
        // Get list from database.
        val checkList = checkListRepo.findByIdAndUser(id = checkListDTO.id, user = userRepo.findByUsernameU(name).get())

        //ToDo: Update only fields that are not null in the DTO

        //Save list
        checkListRepo.save(checkList)
    }

    override fun deleteCheckList(id: Long, name: String) = checkListRepo.deleteByIdAndUser(id, userRepo.findByUsernameU(name).get())

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