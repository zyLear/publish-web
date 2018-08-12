package com.zylear.publish.web.service.pubilsh;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.domain.LolVideo;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/12.
 */
public interface LolVideoService {
    void insert(LolVideo video);

    LolVideo findBySourceTypeAndTitle(Integer sourceType, String title);

    LolVideo selectByPrimaryKey(Integer id);

    Integer count();

    List<LolVideo> findLolVideosByPageParam(PageParam pageParam);
}
