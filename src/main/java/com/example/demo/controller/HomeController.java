package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Routine;
import com.example.demo.service.AchievementService;
import com.example.demo.service.RoutineItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final RoutineItemService routineItemService;
	private final AchievementService achievementService;
	
	//メインページ
	@GetMapping("/home")
	public String mainPage() {
		return "mainpage";
	}
	
	//モーニングページ
	@GetMapping("/morning")
	public String morningPage(Model model) {
		int userId=1;
		int routineId=1;//モーニング
		
		List<Routine> morningList=routineItemService.getRoutineItems(userId, routineId);
		model.addAttribute("morningList", morningList);
		
	    return "morning";
	}
	
	//ルーティン登録
	@PostMapping("/routine/save")
	public String saveMorning(@RequestParam("items") List<String> items) {
	    int userId = 1;
	    int routineId = 1;
	    routineItemService.saveRoutineItems(items, userId, routineId);
	    return "redirect:/morning";
	}
	
	
	//ナイトページ
	@GetMapping("/night")
	public String nightPage(Model model){
	    int userId=1;
	    int routineId=2;
	    
	    List<Routine> nightList = routineItemService.getRoutineItems(userId, routineId);
	    model.addAttribute("nightList", nightList);
	    return "night";
	}
	
	//ルーティン登録
	@PostMapping("/routine/save/night")
	public String saveNight(@RequestParam("items") List<String> items) {
	    int userId = 1;
	    int routineId = 2;
	    routineItemService.saveRoutineItems(items, userId, routineId);
	    return "redirect:/night";
	}
	
	//マイページ
	@GetMapping("/mypage")
	public String myPage(Model model) {
		int userId=1;
		
		List<Routine> mymorningList=routineItemService.getRoutineItems(userId, 1);
		model.addAttribute("mymorningList", mymorningList);
		
		List<Routine> mynightList = routineItemService.getRoutineItems(userId, 2);
	    model.addAttribute("mynightList", mynightList);
	    
	    List<String> achievedDates = achievementService.findAchievedDatesByUserId(userId);
	    model.addAttribute("achievementDates", achievedDates);
		
		return "mypage";
	}
	
	//カレンダー登録
	@PostMapping("/achievement/save")
    public String saveAchievement(@RequestParam("isAllChecked") boolean isAllChecked) {

        int userId = 1;
        Date today = Date.valueOf(LocalDate.now());

        achievementService.saveAchievement(userId, today, isAllChecked);

        return "redirect:/mypage";
	}
	
}
