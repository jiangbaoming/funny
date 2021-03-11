package com.jbm.funny.service.impl;

import cn.hutool.http.HttpUtil;
import com.jbm.funny.rest.ChpApi;
import com.jbm.funny.service.ChpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/02/20
 * @Modified By：
 */

@Service
public class ChpServiceImpl implements ChpService {

    @Autowired
    private ChpApi chpApi;

    @Override
    public Object getChp() {

        String result= HttpUtil.get(chpApi.getSentence());

        return result;
    }
}
