package com.ccunix.hospital.admin.domain;

import lombok.Data;

@Data
public class News {
    private String newsId;
    private String title;
    private String createTime;
    private String content;
    private Integer isOn;
}
