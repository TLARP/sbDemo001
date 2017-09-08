package com.devi.test.service;

import com.devi.cache.interceptor.GuavaLocalCache;
import com.devi.test.domain.User;
import com.devi.test.mapper.UserMapper;
import com.devi.test.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Integer saveDouble(User user) {
        userMapper.insert(user);
        user.setId(null);
        userMapper.insert(user);

        return user.getId();
    }


    public Integer save(User user) {
        userMapper.insert(user);

        return user.getId();
    }

    @Cacheable(value = {"ELEMENT_COMMON"}, key = "T(com.devi.test.constant.CacheKeyUtil).USER_ID+#id", cacheManager = "guavaCacheManager")
    public User getUserById(Integer id) {
        logger.info(Thread.currentThread().getName() + "getUserById 重新取值");
        return userMapper.selectByPrimaryKey(id);
    }


    @Cacheable(value = {"ELEMENT_COMMON"}, key = "T(com.devi.test.constant.CacheKeyUtil).USER_ID+#id", cacheManager = "redisCacheManager")
    @CachePut(value = {"ELEMENT_COMMON"}, key = "T(com.devi.test.constant.CacheKeyUtil).USER_ID+#user.id", cacheManager = "redisCacheManager")
    public User updateUserById(User user) {
        logger.info(Thread.currentThread().getName() + " updateUserById:" + user);
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    @Cacheable(value = {"ELEMENT_COMMON"}, key = "T(com.devi.test.constant.CacheKeyUtil).USER_ID+#id", cacheManager = "redisCacheManager")
    public User getUserByIdRedis(Integer id) {
        logger.info(Thread.currentThread().getName() + "getUserByIdRedis 重新取值");
        return userMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = {"ELEMENT_COMMON2", "ELEMENT_COMMON"}, key = "T(com.devi.test.constant.CacheKeyUtil).USER_ID+#id", cacheManager = "redisCacheManager")
    public User getUserByIdRedis2(Integer id) {
        logger.info(Thread.currentThread().getName() + "getUserByIdRedis 重新取值");
        return null;//userMapper.selectByPrimaryKey(id);
    }


    @CachePut(value = {"ELEMENT_COMMON"}, key = "T(com.devi.test.constant.CacheKeyUtil).USER_ID+#user.id", cacheManager = "redisCacheManager")
    public User updateUserByIdRedis(User user) {
        logger.info(Thread.currentThread().getName() + " updateUserByIdRedis:" + user);
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    @CacheEvict(value = {"ELEMENT_COMMON"}, allEntries = true, cacheManager = "redisCacheManager")
    public void evictUserRedis() {
        logger.info(Thread.currentThread().getName() + " evictUsers:");
    }

    @CacheEvict(value = {"ELEMENT_COMMON2"}, allEntries = true, cacheManager = "redisCacheManager")
    public void evictUserRedis2() {
        logger.info(Thread.currentThread().getName() + " evictUsers:");
    }

    @GuavaLocalCache
    public Page<User> queryPage(Integer pageNumber, Integer pageSize) {
        Page<User> page = new Page<>(pageNumber, pageSize);
        userMapper.queryPage(page);
        return page;
    }
}
