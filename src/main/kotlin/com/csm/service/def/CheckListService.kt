package com.csm.service.def

import com.csm.domain.dto.CheckListDTO


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListService {
    fun createCheckList(name: String): CheckListDTO
    fun saveRemoteCreatedCheckList(checkListDTO: CheckListDTO, name: String)
    fun getCheckList(id: Long, name: String): CheckListDTO
    fun getCheckLists(name: String): List<CheckListDTO>
    fun updateCheckList(checkListDTO: CheckListDTO, name: String)
    fun deleteCheckList(id: Long, name: String)
}