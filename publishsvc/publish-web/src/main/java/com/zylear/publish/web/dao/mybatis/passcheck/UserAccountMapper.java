package com.zylear.publish.web.dao.mybatis.passcheck;

import com.zylear.publish.web.domain.passcheck.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserAccountMapper {

    int insert(UserAccount record);

    UserAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UserAccount record);


    UserAccount findByAccount(@Param("account") String account);

    UserAccount findByDeviceId(@Param("deviceId") String deviceId);

    UserAccount findByAccountAndPassword(@Param("account") String account,
                                         @Param("password") String password);

    void updateVipExpireTimeByAccount(@Param("account") String account,
                                      @Param("vipExpireTime") Date vipExpireTime,
                                      @Param("accumulateVipDay") Integer accumulateVipDay);

    void updatePluginVipExpireTimeByAccount(@Param("account") String account,
                                            @Param("pluginVipExpireTime") Date pluginVipExpireTime,
                                            @Param("pluginAccumulateVipDay") Integer pluginAccumulateVipDay);
}