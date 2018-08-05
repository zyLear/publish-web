package com.zylear.publish.web.service.pubilsh;

import com.zylear.publish.web.domain.Article;

/**
 * Created by xiezongyu on 2018/8/5.
 */
public interface ArticleService {
    Article selectByPrimaryKey(Integer id);
}
