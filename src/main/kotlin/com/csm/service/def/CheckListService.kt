package com.csm.service.def

import com.csm.domain.dto.CheckListDTO


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListService {
    fun createCheckList(): CheckListDTO
    fun saveRemoteCreatedCheckList(checkListDTO: CheckListDTO)
    fun getCheckList(id: Long): CheckListDTO
    fun getCheckLists(): List<CheckListDTO>
    fun updateCheckList(checkListDTO: CheckListDTO)
    fun deleteCheckList(id: Long)
}