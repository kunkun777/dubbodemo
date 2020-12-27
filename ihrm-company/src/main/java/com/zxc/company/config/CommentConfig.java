package com.zxc.company.config;

import com.zxc.common.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfig {
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
