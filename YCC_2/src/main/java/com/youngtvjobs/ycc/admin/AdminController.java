package com.youngtvjobs.ycc.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.youngtvjobs.ycc.common.YccMethod;

@Controller
public class AdminController
{
	
	@Autowired
	AdminService adminService;
	//관리자페이지 메인메뉴
	@RequestMapping("/admin")
	public String adminmain(HttpServletRequest request) throws Exception
	{

		return "admin/adminmain";
	}

	@GetMapping("/admin/profile")
	public String adminprofile(HttpServletRequest request) throws Exception
	{
		
		return "admin/profile";
	}


	//관리자페이지 : 공지사항 관리
	@GetMapping("/admin/popup")
	public String popupSetting(HttpServletRequest request) throws Exception
	{
		
		return "admin/popup";
	}
	
	//공지사항 관리 : 저장 버튼 눌렀을 때 동작
	@PostMapping("/admin/popup")
	public String popupSave(String action, String url, String files, Model m) throws Exception
	{
		if(action.equals("save"))
		{
			m.addAttribute("alert", "<script>alert('저장 되었습니다.')</script>");
		}
		
		else if(action.equals("delete"))
		{
			m.addAttribute("alert", "<script>alert('삭제 되었습니다.')</script>");
		}
		System.out.println("post 구문 시작");
		return "redirect:/admin/popup";
	}
	
	//이용약관 관리
	@GetMapping("/admin/agreement")
	public String agreement(HttpServletRequest request, Model m) throws Exception
	{
		
		AdminDto adminDto = adminService.select();
		
		try {

			m.addAttribute("adminDto", adminDto);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "admin/agreement";
	}
	
	//약관 수정 후 등록
	@PostMapping("/admin/agreement")
	public String agreement(HttpServletRequest request, String join_privacy_terms, 
			String join_terms, RedirectAttributes rattr) throws Exception
	{
		AdminDto adminDto = new AdminDto();
		adminDto.setJoin_terms(join_terms);
		adminDto.setJoin_privacy_terms(join_privacy_terms);
		
		try {
			
			if(adminService.joinTermsUpdate(adminDto) != 1) {
				rattr.addFlashAttribute("msg", "UPDATE_FAIL");
				return "redirect:/admin";
				

			}else{
				System.out.println("업데이트 성공");
				rattr.addFlashAttribute("msg", "UPDATE_SUCCESS");
				return "redirect:/admin";
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("업데이트 실패");
		}
		
		
		return "admin/adminmain";
	}


}
