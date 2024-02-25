package com.qiu.dao;

import com.qiu.constant.UserStatusEnum;
import com.qiu.entity.Role;
import com.qiu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 9:26
 * @description user
 */
@Component
public interface UserDao extends BaseDao<User> {
    /**
     * 查询手机号是否存在
     *
     * @param telephone 手机号
     * @return 是否绑定手机号
     */
    Boolean existsWithPrimaryPhone(String telephone);

    /**
     * 查询用户状态
     *
     * @param accountNumber 用户账号
     * @return 账号是否被锁定
     */
    Boolean selectUserState(String accountNumber);

    /**
     * 通过手机号查询用户
     *
     * @param telephone 手机号
     * @return 用户信息
     */
    User selectByPhone(String telephone);

    /**
     * 获取用户对应角色
     * @param userId 用户唯一标识
     * @return 角色列表
     */
    List<Role> getRoleList(@Param("userId") Integer userId);

    /**
     * 获取用户信息根据用户身份
     * @param status 用户身份
     * @return 用户列表
     */
    List<User> queryAllByStatus(@Param("status") UserStatusEnum status);
}
