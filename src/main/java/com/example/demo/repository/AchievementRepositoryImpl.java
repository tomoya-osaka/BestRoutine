package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Achievement;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AchievementRepositoryImpl implements AchievementRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Achievement achievement) {
		String sql = """
            INSERT INTO t_routine_achievement (user_id, submit_date, is_all_checked)
            VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            achievement.getUserId(),
            achievement.getSubmitDate(),
            achievement.getIsAllChecked()
        );

	}

	@Override
	public Optional<Achievement> findByUserIdAndSubmitDate(int userId, Date submitDate) {
		String sql = """
	            SELECT achievement_id, user_id, submit_date, is_all_checked
	            FROM t_routine_achievement
	            WHERE user_id = ? AND submit_date = ?
	        """;

	        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userId, submitDate);

	        if (rows.isEmpty()) {
	            return Optional.empty();
	        }

	        Map<String, Object> row = rows.get(0);
	        Achievement achievement = new Achievement();
	        achievement.setAchievementId((Integer) row.get("achievement_id"));
	        achievement.setUserId((Integer) row.get("user_id"));
	        achievement.setSubmitDate((Date) row.get("submit_date"));
	        achievement.setIsAllChecked((Boolean) row.get("is_all_checked"));

	        return Optional.of(achievement);
	}

	@Override
	public List<String> findDatesByUserId(int userId) {
		String sql = """
		        SELECT submit_date FROM t_routine_achievement
		        WHERE user_id = ? AND is_all_checked = true
		    """;

		    return jdbcTemplate.query(sql, (rs, rowNum) ->
		        rs.getDate("submit_date").toString(), userId);
	}

	@Override
	public void deleteByUserIdAndDate(int userId, Date submitDate) {
		String sql = "DELETE FROM t_routine_achievement WHERE user_id = ? AND submit_date = ?";
	    jdbcTemplate.update(sql, userId, submitDate);		
	}

}
