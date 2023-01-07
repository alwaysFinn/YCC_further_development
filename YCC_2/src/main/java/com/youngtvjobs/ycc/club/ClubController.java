/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.06
 * 업데이트 내용 : 동아리 목록 가져오는 select 기능 추가
 * 기능 : 동아리 불러오기 기능 구현된 동아리 controller 
 */

package com.youngtvjobs.ycc.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClubController
{
	@Autowired
	ClubService clubService;

	@GetMapping("/club")
	public String clubMain(Model m) throws Exception{
		try {
			
			
			
			List<ClubDto> list = clubService.selectAllClub();
			m.addAttribute("list", list);
			System.out.println(list);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "club/clubmain";
	}
	
	@RequestMapping("/club/create")
	public String clubCreate(HttpServletRequest request)
	{

		return "club/create";
	}

	@RequestMapping("/club/board")
	public String clubBoard(HttpServletRequest request)
	{

		return "club/club_board";
	}

	@RequestMapping("club/board/view")
	public String boardView(HttpServletRequest request)
	{
		
		return "club/board/view";
	}
	
	@RequestMapping("club/board/edit")
	public String clubEdit(HttpServletRequest request)
	{
		
		return "club/board/edit";
	}

	@RequestMapping("club/board/write")
	public String clubWrite(HttpServletRequest request)
	{
	
		return "club/board/write";
	}

	
}
