package com.tqmars.cardrecycle.webapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/3/15.
 */
@RestController
@RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
public class Test {

    @RequestMapping(value = "/test")
    public void test() {
        int a = 1/0;
    }
}
