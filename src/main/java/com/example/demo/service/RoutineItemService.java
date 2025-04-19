package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Routine;

public interface RoutineItemService {
	
	//ルーティンを取得
	List<Routine> getRoutineItems(int userId, int routineId);	
	
	//ルーティンを保存
	void saveRoutineItems(List<String> items, int userId, int routineId);
	
}
