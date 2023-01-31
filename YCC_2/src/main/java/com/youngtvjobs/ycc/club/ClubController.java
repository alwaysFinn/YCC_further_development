/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.31
 * 업데이트 내용 : 동아리 장인지 아닌지 체크하는 기능 추가
 * 기능 : 동아리 불러오기 기능 구현된 동아리 controller 
 */

package com.youngtvjobs.ycc.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.youngtvjobs.ycc.common.PageResolver;
import com.youngtvjobs.ycc.common.SearchItem;
import com.youngtvjobs.ycc.member.security.CustomUser;

@Controller
public class ClubController
{
	@Autowired
	ClubService clubService;

	//동아리 메인 페이지
	@GetMapping("/club")
	public String clubMain(SearchItem sc, Authentication auth, Model m) throws Exception{

		//로그인한 정보가 있다면 해당 유저가 가입한 동아리 목록 불러오는 기능
		try {
			if(auth != null) {
				
				String user_id = auth.getName();
				String club_master_id = auth.getName();
				List<ClubDto> myMsList = clubService.selectMasterMyClub(club_master_id);	//login한 user가 동아리 장인 동아리 목록
				List<ClubDto> myList = clubService.selectMyClub(user_id); //login한 user가 가입한 동아리 목록
				m.addAttribute("myMsList", myMsList);
				m.addAttribute("myList", myList);  
				System.out.println("나의 동아리 목록 : " + myList);
				System.out.println("내가 만든 동아리 목록 : " + myMsList);
			}
			
			int totalCnt = clubService.getAllClubSearchResultCnt(sc);
			m.addAttribute("totalCnt", totalCnt);
			
			PageResolver pageResolver = new PageResolver(totalCnt, sc);
			
			List<ClubDto> list = clubService.getAllClubSearchResultPage(sc);
			m.addAttribute("list", list);
			m.addAttribute("pr", pageResolver);
			System.out.println("totalCnt:" + totalCnt);
			System.out.println("pr:" + pageResolver);
			/*
			 * List<ClubDto> list = clubService.selectAllClub(); //생성되어있는 모든 동아리 목록
			 * m.addAttribute("list", list); System.out.println("모든 동아리 목록 : " + list);
			 */
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "club/clubmain";
	}
	
	//새로운 동아리 만드는 페이지로 이동하는 getmapping
	@GetMapping("/club/create")
	public String clubCreate(Model m){
		m.addAttribute("mode", "new");
		
		try {
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "club/clubcreate";
	}
	
	//동아리 만든 후 서버로 전송하는 postmapping
	@PostMapping("/club/create")
	public String clubCreate(Model m, ClubDto clubDto, String club_title, String club_info, Authentication auth) {

		clubDto.setClub_master_id(auth.getName());
		clubDto.setClub_title(club_title);
		clubDto.setClub_info(club_info);
		
		// user의 name에 접근하기 위한 객체 생성
		CustomUser user = (CustomUser) auth.getPrincipal();
		// 위에서 만든 객체를 기반으로 DTO의 memberDto에 접근하여 getname
		String user_name = user.getMember().getUser_name();
		//setter를 사용하여 얻어낸 user_name 전송
		clubDto.setClub_master_nm(user_name);
		
		try {
			if(clubService.overlapCreateValChk(clubDto) != 1) {//동아리 중복 생성 방지 유효성 체크
				if(clubService.createClub(clubDto) != 1) {	//동아리 생성하는 insert, 성공하면 0, 실패하면 1을 return
					System.out.println("동아리 생성 실패");
					m.addAttribute("msg", "CREATE_FAIL");
					return "redirect:/club/create";
				}else {
					System.out.println("동아리 생성 성공");
					m.addAttribute("msg", "CREATE_OK");
					return "redirect:/club";
				}
			}else {
				System.out.println("동아리 중복 생성 불가");
				m.addAttribute("msg", "CREATE_ERR");
				return "redirect:/club";
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("club create Exception 발생");
			m.addAttribute("msg", "CREATE_FAIL");
			return "redirect:/club/create";
		}
	}
	
	//동아리 상세보기 페이지 접근하는 getmapping
	@GetMapping("/club/detail")
	public String clubDetail(ClubSearchItem sc, HttpServletRequest request, Authentication auth, ClubDto clubDto, Model m) {
		
		int club_id = Integer.parseInt(request.getParameter("id"));	//request.getParameter는 string으로 불러오므로 int로 형변환 필수
		
		try {
			if(auth != null) {
				
				String user_id = auth.getName();
				clubDto.setClub_id(club_id);
				List<ClubDto> clubDetail = clubService.selectClubDetail(club_id);
				m.addAttribute("clubDetail", clubDetail);
				System.out.println("clubDetail : " + clubDetail);
				
				List<ClubDto> cbList = clubService.selectClubBoard(club_id);
				m.addAttribute("cbList", cbList);
				
				clubDto.setUser_id(user_id);
				
				
				int totalCnt = clubService.getClubSearchResultCnt(sc);
				m.addAttribute("totalCnt", totalCnt);
				
				/*
				 * PageResolver pageResolver = new PageResolver(totalCnt, sc);
				 * m.addAttribute("pr", pageResolver);
				 */
				
				List<ClubDto> list = clubService.getClubSearchResultPage(sc);
				m.addAttribute("list", list);
				
				clubDto.setClub_master_id(user_id); // for chkClubMaster()
			}else {
				return "redirect:/login";
			}
			if(clubService.chkClubMember(clubDto) == 1) {
				m.addAttribute("mode", "CM");
			}else if(clubService.chkClubMaster(clubDto) == 1) {
				m.addAttribute("mode", "CMT");
			}else {
				m.addAttribute("mode", "N");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "club/clubdetail";
	}
	
	//동아리 가입하기 클릭 시 작동하는 postmapping
	@PostMapping("/club/detail")
	public String clubDetail(ClubDto clubDto, RedirectAttributes rattr) {
		try {
			if(clubService.joinClub(clubDto) != 1) {
				rattr.addFlashAttribute("msg", "JOIN_FAIL");
				return "redirect:/club";
			}else {
				System.out.println("동아리 가입 성공");
				rattr.addFlashAttribute("msg", "JOIN_SUCCESS");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "club/clubmain";
	}

	@PostMapping("/club/board")
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

	@GetMapping("/club/board/write")
	public String clubWrite(@RequestParam("id") int club_id, Authentication auth, ClubDto clubDto, Model m) {
		m.addAttribute("mode", "new");
		m.addAttribute("club_id", club_id);
		
		return "club/clubboard";
	}

	@PostMapping("club/board/write")
	public String clubWrite(RedirectAttributes rattr, HttpServletRequest request, Model m, ClubDto clubDto, Authentication auth, String club_article_title, String club_article_contents, int club_id) {
		
		
		try {
			System.out.println("club_article_title : " + club_article_title);
			System.out.println("club_article_contents : " + club_article_contents);
			System.out.println("club_id : " + club_id);
			
			String user_id = auth.getName();
			System.out.println("user_id : " + user_id);
			clubDto.setClub_id(club_id);
			clubDto.setUser_id(user_id);
			clubDto.setClub_article_title(club_article_title);
			clubDto.setClub_article_content(club_article_contents);
			
			if(clubService.BoardWrite(clubDto) != 1) {
				System.out.println("글 작성 실패");
				rattr.addFlashAttribute("msg", "WRITE_FAIL");
				return "redirect:/club/board/write";
			}else {
				System.out.println("글 정상 등록");
				rattr.addFlashAttribute("msg", "WRITE_OK");
				return "redirect:/club/detail?id=" + club_id;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("글 등록 오류");
			rattr.addFlashAttribute("msg", "WRITE_ERR");
			return "redirect:/club/board/write";
		}
	}
	
}
