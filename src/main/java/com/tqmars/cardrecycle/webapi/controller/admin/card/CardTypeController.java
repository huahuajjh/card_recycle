package com.tqmars.cardrecycle.webapi.controller.admin.card;

import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jjh on 1/16/17.
 */
@RestController
@RequestMapping(value = "/admin/card",method = RequestMethod.POST)
public class CardTypeController extends ControllerBase {
    public CardTypeController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        return "";
    }

    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/modify")
    public String modify(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/del")
    public String del(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/cardItem/add")
    public String addCardItem(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/cardItem/modify")
    public String modifyCardItem(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/cardItem/del")
    public String delCardItem(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/cardItem/query")
    public String queryCardItem(@RequestParam(value = "input") String input){
        return "";
    }

}
