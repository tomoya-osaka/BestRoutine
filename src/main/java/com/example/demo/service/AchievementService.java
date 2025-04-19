package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Achievement;

public interface AchievementService {
	
	//その日の達成状況を登録
    void saveAchievement(int userId, Date submitDate, boolean isAllChecked);
    
    //特定日の達成状況を取得
    Optional<Achievement> getAchievementByUserIdAndDate(int userId, Date submitDate);
    
    //達成日一覧を取得
    List<String> findAchievedDatesByUserId(int userId);
    
}
