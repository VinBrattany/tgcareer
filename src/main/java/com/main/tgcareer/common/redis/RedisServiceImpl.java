package com.main.tgcareer.common.redis;

import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class RedisServiceImpl extends AbstractRedisService<Object> {

    @Override
    protected String getRedisKey() {
        return "TGCAREER_REDIS_KEY";
    }
}