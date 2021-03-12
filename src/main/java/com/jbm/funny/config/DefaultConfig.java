package com.jbm.funny.config;

import com.baidu.aip.ocr.AipOcr;
import com.jbm.funny.aipocr.baidu.AipOcrConfig;
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
        ChpApi chpApi = new ChpApi();
        return chpApi;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        return service;
    }

    @Bean
    @ConfigurationProperties(prefix = "qcr")
    public AipOcrConfig aipOcrConfig() {
        AipOcrConfig service = new AipOcrConfig();
        return service;
    }


    @Bean
    public AipOcr aipOcr(AipOcrConfig config) {
        AipOcr client = new AipOcr(config.getAppId(), config.getApiKey(), config.getSecretKey());
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

}
