package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Achievement;

public interface AchievementRepository {
	
	//その日の達成状況を登録
	void insert(Achievement achievement);

	//登録済みかどうかを確認
    Optional<Achievement> findByUserIdAndSubmitDate(int userId, Date submitDate);
    
    //達成日一覧を取得
    List<String> findDatesByUserId(int userId);
    
    //既存記録を消去
    void deleteByUserIdAndDate(int userId, Date submitDate);
    
}
