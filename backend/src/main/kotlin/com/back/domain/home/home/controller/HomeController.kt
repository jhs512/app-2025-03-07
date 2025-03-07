package com.back.domain.home.home.controller

import com.back.standard.extensions.logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.net.InetAddress

@Controller
class HomeController {
    private val log = logger()

    @GetMapping("/")
    @ResponseBody
    fun main(): String {
        val localHost = InetAddress.getLocalHost()

        val hostname = localHost.hostName

        log.debug("dev/test only")

        return "main, hostname : $hostname"
    }
}