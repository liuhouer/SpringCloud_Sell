package com.imooc.user.service;

import com.imooc.user.dataobject.UserInfo;

/**
 * @author hellozjf
 */
public interface UserService {
    UserInfo findByOpenid(String openid);
}
