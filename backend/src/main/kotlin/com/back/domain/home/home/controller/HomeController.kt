package com.back.domain.home.home.controller

import com.back.global.app.AppConfig
import com.back.global.rq.Rq
import com.back.standard.extensions.logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import java.io.File
import java.net.InetAddress
import java.util.UUID.randomUUID

@Controller
class HomeController(
    private val rq: Rq
) {
    private val log = logger()

    @GetMapping("/")
    @ResponseBody
    fun main(): String {
        val localHost = InetAddress.getLocalHost()

        val hostname = localHost.hostName

        log.debug("dev/test only")

        return "main, hostname : $hostname"
    }


    @GetMapping("/cookie/{name}/{value}")
    @ResponseBody
    fun setCookie(
        @PathVariable name: String,
        @PathVariable value: String
    ): String {
        rq.setCookie(name, value)

        return "${name}=${value}"
    }

    @GetMapping("/cookie/{name}")
    @ResponseBody
    fun getCookie(
        @PathVariable name: String
    ): String {
        return rq.getCookieValue(name) ?: ""
    }

    @GetMapping("/newFile")
    @ResponseBody
    fun newFile(): String {
        val fileName = "${randomUUID()}.html"
        val filePath = "${AppConfig.getGenFileDirPath()}/${fileName}"

        if (!File(AppConfig.getGenFileDirPath()).exists()) {
            File(AppConfig.getGenFileDirPath()).mkdirs()
        }

        File(filePath).writeText("<h1>${fileName}</h1>")

        return "${AppConfig.getSiteBackUrl()}/gen/${fileName}"
    }
}