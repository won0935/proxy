package com.hello.advanced.app.v1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping// 스프링은 @Controller, @RequestMapping 이 있어야 스프링 컨트롤러로 인식
@ResponseBody
interface OrderControllerV1 {

    @GetMapping("/v1/request")
    fun request(@RequestParam("itemId") itemId: String) : String

    @GetMapping("/v1/no-log")
    fun noLog() : String

}
