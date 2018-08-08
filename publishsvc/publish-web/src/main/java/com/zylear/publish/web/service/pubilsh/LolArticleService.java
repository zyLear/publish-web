package com.zylear.publish.web.service.pubilsh;

import com.zylear.publish.web.domain.LolArticle;

/**
 * Created by xiezongyu on 2018/8/8.
 */
public interface LolArticleService {
    LolArticle selectByPrimaryKey(Integer id);

    void insert(LolArticle article);

    LolArticle findBySourceTypeAndTitle(Integer value, String title);
}
