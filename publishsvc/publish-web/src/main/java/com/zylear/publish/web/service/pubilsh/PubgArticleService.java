package com.zylear.publish.web.service.pubilsh;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.domain.PubgArticle;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/5.
 */
public interface PubgArticleService {
    PubgArticle selectByPrimaryKey(Integer id);

    PubgArticle findBySourceTypeAndTitle(Integer sourceType, String title);

    void insert(PubgArticle article);

    Integer count();

    List<PubgArticle> findPubgArticlesByPageParam(PageParam pageParam);
}
