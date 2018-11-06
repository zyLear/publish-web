package com.zylear.publish.web.dao.mybatis.passcheck;

import com.zylear.publish.web.domain.passcheck.CardInfo;
import org.apache.ibatis.annotations.Param;

public interface CardInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CardInfo record);

    int insertSelective(CardInfo record);

    CardInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CardInfo record);

    int updateByPrimaryKey(CardInfo record);


    CardInfo findByNumberAndPassword(@Param("cardNumber") String cardNumber,
                                     @Param("cardPassword") String cardPassword);

    void updateByCardNumber(@Param("cardNumber")String cardNumber,
                            @Param("isUsed")Boolean isUsed,
                            @Param("useAccount")String useAccount);
}