package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Routine;

public interface RoutineRepository {
	
	//ルーティンを登録
	void insert(Routine routine);
	
	//既に登録済みのルーティンを削除
	void deleteByUserIdAndRoutineId(int userId, int routineId);
	
	//登録済みのルーティンを取得
	List<Routine> findByUserIdAndRoutineId(int userId, int routineId);
	
}
