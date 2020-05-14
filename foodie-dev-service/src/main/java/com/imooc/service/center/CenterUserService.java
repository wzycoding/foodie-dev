package com.imooc.service.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;

public interface CenterUserService {
    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    Users queryUserInfo(String userId);

    /**
     * 更新用户信息
     * @param userId 用户id
     * @param centerUserBO 用户bo
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId 用户id
     * @param faceUrl 头像url
     * @return
     */
    Users updateUserFace(String userId, String faceUrl);
}
