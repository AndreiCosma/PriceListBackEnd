package com.csm.controller

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/*
* Created by I503342 - 03/04/2019
*/
@RequestMapping
@RestController
@Api(tags = ["List item, end-point."])
class ListItemController {
    companion object {
        const val PATH = "/api/v1/item"
    }

}