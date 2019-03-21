package com.csm.service.impl

import com.csm.domain.dto.CheckListDTO
import com.csm.domain.repo.CheckListRepo
import com.csm.service.def.CheckListService
import org.springframework.stereotype.Service


/*
* Created by I503342 - 21/03/2019
*/
@Service
class CheckListServiceImpl(
        val checkListRepo: CheckListRepo
) : CheckListService {
    override fun createCheckList(): CheckListDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveRemoteCreatedCheckList(checkListDTO: CheckListDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCheckList(id: Long): CheckListDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCheckLists(): List<CheckListDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateCheckList(checkListDTO: CheckListDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCheckList(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}