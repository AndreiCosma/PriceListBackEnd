package com.csm.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


/*
* Created by I503342 - 20/03/2019
*/

@RequestMapping(NoteController.PATH)
@RestController
class NoteController {
    companion object {
        const val PATH = "/note"
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getNotes() = Mono.just("Notes")
}