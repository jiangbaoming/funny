package com.jbm.funny.api;


import com.jbm.funny.service.ChpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/02/20
 * @Modified By：
 */
@RestController
@RequestMapping("/chp")
public class ChpController {

    @Autowired
    private ChpService chpService;

    @RequestMapping("/word")
    public Object chpApi(HttpServletRequest request, HttpServletResponse response){
        return chpService.getChp();
    }

}
