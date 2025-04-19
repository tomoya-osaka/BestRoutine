package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Achievement;
import com.example.demo.repository.AchievementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
	
	private final AchievementRepository achievementRepository;

	@Override
	public void saveAchievement(int userId, Date submitDate, boolean isAllChecked) {
		achievementRepository.deleteByUserIdAndDate(userId, submitDate);
		
		Achievement achievement = new Achievement();
        achievement.setUserId(userId);
        achievement.setSubmitDate(submitDate);
        achievement.setIsAllChecked(isAllChecked);
        achievementRepository.insert(achievement);

	}

	@Override
	public Optional<Achievement> getAchievementByUserIdAndDate(int userId, Date submitDate) {
		return achievementRepository.findByUserIdAndSubmitDate(userId, submitDate);
	}

	@Override
	public List<String> findAchievedDatesByUserId(int userId) {
		return achievementRepository.findDatesByUserId(userId);
	}

}
