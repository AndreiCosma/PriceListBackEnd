package com.csm.controller

import com.csm.processor.ListEdmProviderProcessor
import io.netty.handler.codec.http.HttpRequest
import io.netty.handler.codec.http.HttpResponse
import io.swagger.annotations.Api
import org.apache.olingo.netty.server.api.ODataNetty
import org.springframework.web.bind.annotation.*


/*
* Created by I503342 - 20/03/2019
*/

@RequestMapping(OdataListController.PATH)
@RestController
@Api(tags = ["Lists, oData v.4 end-point."])
class OdataListController {
    companion object {
        const val PATH = "/api/v1/odata/v4/NOTES"
    }

    @RequestMapping
    fun handle(httpRequest: HttpRequest, httpResponse: HttpResponse) {
        val oData = ODataNetty.newInstance()
        val edm = oData.createServiceMetadata(ListEdmProviderProcessor(), arrayListOf())
        val handler = oData.createNettyHandler(edm)

        handler.processNettyRequest(httpRequest, httpResponse, mapOf())
    }

}