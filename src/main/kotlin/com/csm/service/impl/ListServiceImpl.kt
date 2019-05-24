package com.csm.service.impl

import com.csm.domain.dto.CheckListDTO
import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.CheckList
import com.csm.domain.entity.CheckListItem
import com.csm.domain.entity.User
import com.csm.domain.repo.ListRepo
import com.csm.domain.repo.UserRepo
import com.csm.service.def.ListService
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional


/*
* Created by I503342 - 21/03/2019
*/

@Service
class ListServiceImpl(
        val checkListRepo: ListRepo,
        val userRepo: UserRepo
) : ListService {

    @Transactional
    override fun createCheckList(user: User): CheckListDTO {
        //Create List
        val userWithHibernateSessionActive = userRepo.findById(user.id).get()
        val checkList = CheckList(
                id = UUID.randomUUID().toString(),
                name = "New Check List",
                items = mutableListOf(),
                owner = userWithHibernateSessionActive,
                users = mutableListOf(userWithHibernateSessionActive),
                creationDate = Date()
        )
        userWithHibernateSessionActive.apply { this.lists.add(checkList); this.ownedLists.add(checkList) }

        //Return list
        return checkList.toDTO()
    }

    @Transactional
    override fun persistRemoteCheckList(checkListDTO: CheckListDTO, user: User) {
        val userWithHibernateSessionActive = userRepo.findById(user.id)
        val checkList = checkListDTO.toPersistable(user = userWithHibernateSessionActive.get())
        checkList.items.addAll(checkListDTO.items.toPersistable(checkList))
        userWithHibernateSessionActive.get().apply { this.lists.add(checkList); this.ownedLists.add(checkList) }
    }

    @Transactional
    override fun getCheckList(id: String, user: User): CheckListDTO = checkListRepo.findByIdAndUser(id = id, user = user).toDTO()

    @Transactional
    override fun getCheckLists(user: User): List<CheckListDTO> = checkListRepo.findByUser(user = user).toDTO()

    @Transactional
    override fun updateCheckList(checkListDTO: CheckListDTO, user: User) {
        // Get list from database.
        checkListRepo.findByIdAndUser(id = checkListDTO.id, user = user).name = checkListDTO.name


        //zToDo: Update only fields that are not null in the DTO
    }

    @Transactional
    override fun deleteCheckList(id: String, user: User) = checkListRepo.deleteByIdAndUser(id = id, user = user)

    private fun CheckList.toDTO() = CheckListDTO(
            id = this.id,
            name = this.name,
            items = this.items.toDTO(this.id),
            creationDate = this.creationDate
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
            id = this.id,
            name = this.name,
            items = mutableListOf(),
            owner = user,
            users = mutableListOf(user),
            creationDate =  this.creationDate
    )

    private fun MutableList<CheckListItemDTO>.toPersistable(parent: CheckList) = this.map { item -> item.toPersistable(parent) }.toMutableList()

    private fun CheckListItemDTO.toPersistable(parent: CheckList) = CheckListItem(
            id = UUID.randomUUID().toString(),
            name = this.name,
            checked = this.checked,
            checkList = parent
    )
}