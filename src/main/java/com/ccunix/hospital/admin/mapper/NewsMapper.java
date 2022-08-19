package com.ccunix.hospital.admin.mapper;

import com.ccunix.hospital.admin.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    /**
     * 添加新闻
     * @param news
     * @return
     */
    Integer insert(News news);

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
