package com.csm.controller

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/*
* Created by I503342 - 01/04/2019
*/

@RequestMapping(ListController.PATH)
@RestController
@Api(tags = ["Lists, end-point."])
class ListController {
    companion object {
        const val PATH = "/api/v/notes"
    }
}