package com.scorpio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author lyj
 *
 */

@Controller
public class IndexController {
    
    /**
     * 主页面
     * @return
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
    
    /**
     * 标签页面
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

}
