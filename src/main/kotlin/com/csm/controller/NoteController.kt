package com.csm.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono


/*
* Created by I503342 - 20/03/2019
*/

@RequestMapping(NoteController.PATH)
class NoteController {
    companion object {
        const val PATH = "/note"
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getNotes() = Mono.just("Notes")
}