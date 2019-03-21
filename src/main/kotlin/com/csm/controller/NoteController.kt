package com.csm.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


/*
* Created by I503342 - 20/03/2019
*/

@RequestMapping(NoteController.PATH)
@RestController
@Api(tags = ["Notes end-point."])
class NoteController {
    companion object {
        const val PATH = "/note"
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all notes for a user.")
    fun getNotes() = Flux.just("Notes")
}