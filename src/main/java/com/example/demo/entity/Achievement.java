package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Achievement {
	private Integer achievementId;
    private Integer userId;
    private Date submitDate;
    private Boolean isAllChecked;
}
