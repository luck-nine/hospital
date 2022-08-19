package com.ccunix.hospital.admin.controller.common;

import com.ccunix.hospital.admin.domain.News;
import com.ccunix.hospital.admin.service.INewsService;
import com.ccunix.hospital.common.domain.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    INewsService newsService;
    @PostMapping("/add")
    public ResponseResult<Integer> add(@RequestBody News news){
        Integer row = newsService.add(news);
        return ResponseResult.success(row);
    }
    @GetMapping("/{id}")
    public ResponseResult<News> getInfo(@PathVariable("id") String id){
        return ResponseResult.success(newsService.selectNewsById(id));
    }
    @GetMapping("/list")
    public ResponseResult<List<News>> list(){
        return ResponseResult.success(newsService.selectNewsList());
    }
    @PutMapping
    public ResponseResult<Integer> update(@RequestBody News news){
        return ResponseResult.success(newsService.updateNewsById(news));
    }
    @DeleteMapping("/{ids}")
    public ResponseResult<Integer> remove(@PathVariable("ids") String[] ids){
        return ResponseResult.success(newsService.deleteNewsByIds(ids));
    }
}
