package com.ltri.localcache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ltri
 * @date 2020/7/7 9:08 下午
 */
@RestController
public class LocalCacheController {

    @GetMapping("/check")
    public String checkHealth() {
        return "OK";
    }
}
