package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.domain.publish.LolVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LolVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LolVideo record);

    int insertSelective(LolVideo record);

    LolVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LolVideo record);

    int updateByPrimaryKey(LolVideo record);

    LolVideo findBySourceTypeAndTitle(@Param("sourceType") Integer sourceType,
                                      @Param("title") String title);

    Integer count();

    List<LolVideo> findLolVideosByPageParam(@Param("pageParam") PageParam pageParam);
}