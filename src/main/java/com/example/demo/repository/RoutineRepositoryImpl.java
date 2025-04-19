package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Routine;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoutineRepositoryImpl implements RoutineRepository {

	private final JdbcTemplate jdbcTemplate;
			
	@Override
	public void insert(Routine routine) {
		
		System.out.println("[DEBUG] INSERT実行：userId=" + routine.getUserId()
        + ", routineId=" + routine.getRoutineId()
        + ", itemText=" + routine.getItemText());
		
		String sql = """
                INSERT INTO t_routine_item (user_id, routine_id, item_text)
                VALUES (?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                routine.getUserId(),
                routine.getRoutineId(),
                routine.getItemText());
	}

	@Override
	public void deleteByUserIdAndRoutineId(int userId, int routineId) {
		String sql = """
                DELETE FROM t_routine_item
                WHERE user_id = ? AND routine_id = ?
                """;
        jdbcTemplate.update(sql, userId, routineId);
	}

	@Override
	public List<Routine> findByUserIdAndRoutineId(int userId, int routineId) {
		String sql = """
                SELECT item_id, user_id, routine_id, item_text
                FROM t_routine_item
                WHERE user_id = ? AND routine_id = ?
                ORDER BY item_id
                """;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userId, routineId);

        List<Routine> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Routine r = new Routine();
            r.setItemId((Integer) row.get("item_id"));
            r.setUserId((Integer) row.get("user_id"));
            r.setRoutineId((Integer) row.get("routine_id"));
            r.setItemText((String) row.get("item_text"));
            result.add(r);
        }

        return result;
	}

}
