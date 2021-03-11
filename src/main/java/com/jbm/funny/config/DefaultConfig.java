package com.jbm.funny.config;

import com.jbm.funny.rest.ChpApi;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/02/20
 * @Modified By：
 */
@Configuration
public class DefaultConfig {

    @Bean
    @ConfigurationProperties(prefix = "api")
    public ChpApi getDataSource() {
        ChpApi  chpApi= new ChpApi();
        return chpApi;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        return service;
    }

}
