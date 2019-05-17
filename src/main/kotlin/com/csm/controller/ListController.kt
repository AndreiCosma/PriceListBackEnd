package com.csm.controller

import com.csm.domain.dto.CheckListDTO
import com.csm.domain.entity.Authority
import com.csm.domain.entity.User
import com.csm.service.def.AuthenticationService
import com.csm.service.def.ListService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.toMono


/*
* Created by I503342 - 01/04/2019
*/

@RequestMapping(path = [ListController.PATH])
@RestController
@Api(tags = ["List, end-point."])
class ListController(
        val checkListService: ListService,
        val authenticationService: AuthenticationService
) {

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(Authority.ROLE_USER)
    @ApiOperation(value = "Request the server to create a list for you.")
    @GetMapping
    fun newCheckList() = checkListService.createCheckList(user = authenticationService.getAuthenticatedUser()).toMono()

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Secured(Authority.ROLE_USER)
    @ApiOperation(value = "Request the server to create a list from a provided copy.")
    @PostMapping
    fun saveRemoteCreatedCheckList(auth: Authentication, @RequestBody checkListDTO: CheckListDTO) = checkListService.persistRemoteCheckList(checkListDTO = checkListDTO, user = auth.principal as User)

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Secured(Authority.ROLE_USER)
    @ApiOperation(value = "Retrieve the requested list for the authenticated user.")
    @GetMapping(path = ["/l/{id}"])
    fun getCheckList(@PathVariable id: String) = checkListService.getCheckList(id = id, user = authenticationService.getAuthenticatedUser())

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Secured(Authority.ROLE_USER)
    @ApiOperation(value = "Retrieve the lists for the authenticated user.")
    @GetMapping(path = ["/all"])
    fun getCheckLists() = checkListService.getCheckLists(user = authenticationService.getAuthenticatedUser())


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured(Authority.ROLE_USER)
    @ApiOperation(value = "Update a certain list. The server updates all the fields that are not null. If a field is null it will ignore it.")
    @PutMapping
    fun updateCheckList(auth: Authentication, @RequestBody checkListDTO: CheckListDTO) = checkListService.updateCheckList(checkListDTO = checkListDTO, user = auth.principal as User)

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured(Authority.ROLE_USER)
    @ApiOperation(value = "Delete a list by id and authenticated user.")
    @DeleteMapping(path = ["/l/{id}"])
    fun deleteCheckList(@PathVariable id: String) = checkListService.deleteCheckList(id = id, user = authenticationService.getAuthenticatedUser())

    companion object {
        const val PATH = "/api/v1/list"
    }

}