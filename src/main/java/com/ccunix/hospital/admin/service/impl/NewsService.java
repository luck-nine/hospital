package com.ccunix.hospital.admin.service.impl;

import com.ccunix.hospital.admin.domain.News;
import com.ccunix.hospital.admin.mapper.NewsMapper;
import com.ccunix.hospital.admin.service.INewsService;
import com.ccunix.hospital.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsService implements INewsService {
    @Autowired
    NewsMapper newsMapper;
    @Override
    public Integer add(News news) {
        String id = UUID.randomUUID().toString().replace("-","");
        news.setNewsId(id);
        return newsMapper.insert(news);
    }

    @Override
    public News selectNewsById(String newId) {
        return newsMapper.selectNewsById(newId);
    }

    @Override
    public List<News> selectNewsList() {
        return newsMapper.selectNewsList();
    }

    @Override
    public Integer updateNewsById(News news) {
        return newsMapper.updateNewsById(news);
    }

    @Override
    public Integer deleteNewsByIds(String[] ids) {
        return newsMapper.deleteNewsByIds(ids);
    }
}
