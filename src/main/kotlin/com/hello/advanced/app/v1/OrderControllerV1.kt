package com.hello.advanced.app.v1

import org.springframework.web.bind.annotation.*

@RestController //Spring boot 3.0부터는 @Controller 명시해야 컨트롤러로 등록됨
//@RequestMapping// 스프링은 @Controller, @RequestMapping 이 있어야 스프링 컨트롤러로 인식
//@ResponseBody
interface OrderControllerV1 {

    @GetMapping("/v1/request")
    fun request(@RequestParam("itemId") itemId: String) : String

    @GetMapping("/v1/no-log")
    fun noLog() : String

}
