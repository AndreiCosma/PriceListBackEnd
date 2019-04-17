package com.csm.service.def

import com.csm.domain.dto.CheckListDTO
import com.csm.domain.entity.User


/*
* Created by I503342 - 21/03/2019
*/
interface ListService {
    fun createCheckList(user: User): CheckListDTO
    fun saveRemoteCreatedCheckList(checkListDTO: CheckListDTO, user: User)
    fun getCheckList(id: String, user: User): CheckListDTO
    fun getCheckLists(user: User): List<CheckListDTO>
    fun updateCheckList(checkListDTO: CheckListDTO, user: User)
    fun deleteCheckList(id: String, user: User)
}