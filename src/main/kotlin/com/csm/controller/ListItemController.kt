package com.csm.controller

import com.csm.domain.dto.CheckListItemDTO
import com.csm.service.def.AuthenticationService
import com.csm.service.def.ListItemService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.toMono


/*
* Created by I503342 - 03/04/2019
*/
@RequestMapping(path = [ListItemController.PATH])
@RestController
@Api(tags = ["List item, end-point."])
class ListItemController(
        val listItemService: ListItemService,
        val authenticationService: AuthenticationService
) {
    companion object {
        const val PATH = "/api/v1/item"
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Request the server to create an item for you.")
    @GetMapping(path = ["/new"])
    fun requestNewItemForParentList(parentId: String) = listItemService.requestNewItemForParentList(parentId = parentId, user = authenticationService.getAuthenticatedUser()).toMono()

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Persist a locally created item.")
    @PostMapping(path = ["/new"])
    fun persistRemoteItem(checkListItemDTO: CheckListItemDTO) = listItemService.persistRemoteItem(checkListItemDTO = checkListItemDTO, user = authenticationService.getAuthenticatedUser())

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Request the server to create an item for you.")
    @GetMapping(path = ["/i/{itemId}"])
    fun getItem(@PathVariable itemId: String) = listItemService.getItem(itemId = itemId, user = authenticationService.getAuthenticatedUser()).toMono()

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a certain item. The server updates all the info provided in the DTO except the id's.")
    @PutMapping
    fun updateItem(@RequestBody checkListItemDTO: CheckListItemDTO) = listItemService.updateItem(checkListItemDTO = checkListItemDTO, user = authenticationService.getAuthenticatedUser())

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an item by id and authenticated user.")
    @DeleteMapping(path = ["/i/{itemId}"])
    fun deleteItem(@PathVariable itemId: String, parentId: String) = listItemService.deleteItem(itemId = itemId, parentId = parentId, user = authenticationService.getAuthenticatedUser())
}