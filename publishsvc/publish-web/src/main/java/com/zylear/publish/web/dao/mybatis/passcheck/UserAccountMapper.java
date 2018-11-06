package com.zylear.publish.web.dao.mybatis.passcheck;

import com.zylear.publish.web.domain.passcheck.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);


    UserAccount findByAccount(@Param("account") String account);

    UserAccount findByDeviceId(@Param("deviceId") String deviceId);

    UserAccount findByAccountAndPassword(@Param("account") String account,
                                         @Param("password") String password);

    void updateVipExpireTimeByAccount(@Param("account") String account,
                                      @Param("vipExpireTime") Date vipExpireTime);
}