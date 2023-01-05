package com.youngtvjobs.ycc.club;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youngtvjobs.ycc.common.YccMethod;

@Controller
public class ClubController
{

	@RequestMapping("/club")
	public String clubMain(HttpServletRequest request)
	{
		
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
