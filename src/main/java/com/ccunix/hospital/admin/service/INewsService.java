package com.ccunix.hospital.admin.service;

import com.ccunix.hospital.admin.domain.News;

import java.util.List;

public interface INewsService {
    /**
     * 添加新闻
     * @param news
     * @return
     */
    Integer add(News news);

    /**
     * 按照id查询信息
     * @param newId
     * @return
     */
    News selectNewsById(String newId);

    /**
     * 查询所有新闻信息
     * @return
     */
    List<News> selectNewsList();

    /**
     * 更新新闻数据
     * @param news
     * @return
     */
    Integer updateNewsById(News news);

    /**
     * 按照主键删除新闻
     * @param ids
     * @return
     */
    Integer deleteNewsByIds(String[] ids);
}
