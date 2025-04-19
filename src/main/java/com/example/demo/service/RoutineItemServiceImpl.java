package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Routine;
import com.example.demo.repository.RoutineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoutineItemServiceImpl implements RoutineItemService {

	private final RoutineRepository routineRepository;
	
	@Override
	public List<Routine> getRoutineItems(int userId, int routineId) {		
		
		return routineRepository.findByUserIdAndRoutineId(userId, routineId);
	}

	@Override
	public void saveRoutineItems(List<String> items, int userId, int routineId) {
		
		// ① 既存の同じ routine_id のルーティンを削除
        routineRepository.deleteByUserIdAndRoutineId(userId, routineId);

        // ② 入力されたルーティンを新しく登録
        for (String text : items) {
            if (text != null && !text.isBlank()) {
                Routine r = new Routine();
                r.setUserId(userId);
                r.setRoutineId(routineId);
                r.setItemText(text);
                routineRepository.insert(r);
            }
        }
	}

}
