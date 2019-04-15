package com.csm.controller

import com.csm.domain.dto.CheckListDTO
import com.csm.service.def.CheckListService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal


/*
* Created by I503342 - 01/04/2019
*/

@RequestMapping(ListController.PATH)
@RestController
@Api(tags = ["Lists, end-point."])
class ListController(
        val checkListService: CheckListService
) {
    companion object {
        const val PATH = "/api/v1/list"
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Request the server to create a list for you.")
    @GetMapping
    fun newCheckList(principal: Principal) = checkListService.createCheckList(name = principal.name)

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Request the server to create a list froma provided copy.")
    @PostMapping
    fun saveRemoteCreatedCheckList(@RequestBody checkListDTO: CheckListDTO, principal: Principal) = checkListService.saveRemoteCreatedCheckList(checkListDTO = checkListDTO, name = principal.name)

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve the requested list for the authenticated user.")
    @GetMapping("/{id}")
    fun getCheckList(@PathVariable id: Long, principal: Principal) = checkListService.getCheckList(id = id, name = principal.name)

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a certain list. The server updates all the fields that are not null. If a field is null it will ignore it.")
    @PutMapping
    fun updateCheckList(@RequestBody checkListDTO: CheckListDTO, principal: Principal) = checkListService.updateCheckList(checkListDTO = checkListDTO, name = principal.name)

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a list by id and authenticated user.")
    @DeleteMapping("/{id}")
    fun deleteCheckList(@PathVariable id: Long, principal: Principal) = checkListService.deleteCheckList(id = id, name = principal.name)

}