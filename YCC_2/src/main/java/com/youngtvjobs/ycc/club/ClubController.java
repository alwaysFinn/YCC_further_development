/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.03.01
 * 업데이트 내용 : 게시글 수정하기 기능 활성화, 게시글 삭제 기능 활성화
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
				
				String user_id = auth.getName(); //시큐리티 통해 user_id 획득
				String club_master_id = auth.getName(); // 시큐리티 통해 얻은 접속한 아이디를 club_master_id에도 부여 -> for 내가 동아리 장인 경우 check 
				List<ClubDto> myMsList = clubService.selectMasterMyClub(club_master_id);	//login한 user가 동아리 장인 동아리 목록
				List<ClubDto> myList = clubService.selectMyClub(user_id); //login한 user가 가입한 동아리 목록
				m.addAttribute("myMsList", myMsList); 
				m.addAttribute("myList", myList); 
				System.out.println("나의 동아리 목록 : " + myList); //Test
				System.out.println("내가 만든 동아리 목록 : " + myMsList); //Test
				List<ClubDto> bList = clubService.clubMainBoard(club_master_id); //내가 동아리 장인 경우 최근 게시글 상위 세개를 보여주는 목록 
				m.addAttribute("bList", bList); 
				System.out.println("내가 만든 동아리 게시글 목록 : " + bList); //Test
			}
			
			int totalCnt = clubService.getAllClubSearchResultCnt(sc); // 페이지네이션을 하기 위해 총 동아리 개수 체크
			m.addAttribute("totalCnt", totalCnt);
			
			PageResolver pageResolver = new PageResolver(totalCnt, sc); // 검색결과 + 총 동아리 개수를 기반으로 페이지네이션
			
			List<ClubDto> list = clubService.getAllClubSearchResultPage(sc); // 검색결과에 따른 목록 보여주는 용도 
			m.addAttribute("list", list);
			m.addAttribute("pr", pageResolver);
			System.out.println("totalCnt:" + totalCnt); //Test
			System.out.println("pr:" + pageResolver); //Test

		} catch(Exception e) {
			e.printStackTrace();
		}
		return "club/clubmain";
	}
	
	//새로운 동아리 만드는 페이지로 이동하는 getmapping
	@GetMapping("/club/create")
	public String clubCreate(Model m){
		m.addAttribute("mode", "new"); //동아리 제목 및 소개 수정 기능 추가 시 해당 기능은 mode = mod로 진입
		
		try {
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "club/clubcreate";
	}
	
	//동아리 만든 후 서버로 전송하는 postmapping
	@PostMapping("/club/create")
	public String clubCreate(Model m, ClubDto clubDto, String club_title, String club_info, Authentication auth, RedirectAttributes rattr) {

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
					rattr.addFlashAttribute("msg", "CREATE_FAIL");
					return "redirect:/club/create";
				}else {
					System.out.println("동아리 생성 성공");
					rattr.addFlashAttribute("msg", "CREATE_OK");
					return "redirect:/club";
				}
			}else {
				System.out.println("동아리 중복 생성 불가");
				rattr.addFlashAttribute("msg", "CREATE_ERR");
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
					System.out.println("cbList : " + cbList);
					
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
	@PostMapping("/club/join")
	public String clubDetail(ClubDto clubDto, int club_id, RedirectAttributes rattr, Authentication auth, HttpServletRequest request) {
		try {

			String user_id = auth.getName();
			
			CustomUser user = (CustomUser) auth.getPrincipal();
			String user_name = user.getMember().getUser_name();
			
			clubDto.setUser_id(user_id);
			clubDto.setUser_name(user_name);
			clubDto.setClub_id(club_id);
			
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

	@GetMapping("club/board/view")
	public String boardView(HttpServletRequest request, RedirectAttributes rattr, Model m, ClubDto clubDto)
	{
		int club_id = Integer.parseInt(request.getParameter("id"));
		String article_id = request.getParameter("article_id");
		
		int club_article_id = Integer.valueOf(article_id);
		
		try {
			clubDto.setClub_id(club_id);
			clubDto.setClub_article_id(club_article_id);
			List<ClubDto> cbdetail = clubService.clubBoardRead(clubDto);
			m.addAttribute("cbdetail", cbdetail);
		
		}catch(Exception e) {
			System.out.println("상세 게시글 접근 중 Exception 발생");
			rattr.addFlashAttribute("msg", "READ_ERR");
			return "redirect:/club/detail?id=" + club_id;
		}
		
		return "club/cboarddetail";
	}
	
	@GetMapping("club/board/edit")
	public String clubEdit(HttpServletRequest request, Model m){
		try {
			
			int club_article_id = Integer.parseInt(request.getParameter("article_id"));
		
			List<ClubDto> list = clubService.clubBoardModRead(club_article_id);
			m.addAttribute("list", list);
			System.out.println("list : " + list);
			m.addAttribute("mode", "modi");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "club/clubboard";
	}
	
	
	@PostMapping("club/board/edit") 
	public String clubEdit(RedirectAttributes rattr, Authentication auth, int club_article_id, String club_article_title, String club_article_content, int club_id) {
		try {
				ClubDto clubDto = new ClubDto();
				clubDto.setClub_article_title(club_article_title);
				clubDto.setClub_article_content(club_article_content);
				clubDto.setClub_id(club_id);
				clubDto.setClub_article_id(club_article_id);
				
				String user_id = auth.getName();
				clubDto.setUser_id(user_id);
			
			if(clubService.clubBoardModPost(clubDto) != 1) {
				rattr.addFlashAttribute("msg", "MOD_FAIL");
				System.out.println("글 업데이트 실패");
				return "redirect:/club/board/edit?article_id=" + club_article_id;
			}else {
				rattr.addFlashAttribute("msg", "MOD_SUCCESS");
				System.out.println("글 업데이트 성공");
				return "redirect:/club/board/view?id="+club_id+"&article_id="+club_article_id;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("업데이트 중 예외 발생");
			rattr.addFlashAttribute("msg", "MOD_ERR");
			
			return "redirect:/club/board/edit?article_id=" + club_article_id;
		}
	}
	

	@GetMapping("/club/board/write")
	public String clubWrite(@RequestParam("id") int club_id, Model m) {
		m.addAttribute("mode", "new");
		m.addAttribute("club_id", club_id);
		
		return "club/clubboard";
	}

	@PostMapping("club/board/write")
	public String clubWrite(RedirectAttributes rattr, HttpServletRequest request, Model m, ClubDto clubDto,
			Authentication auth, String club_article_title, String club_article_content, int club_id) {
		
		
		try {
			String user_id = auth.getName();

			clubDto.setClub_id(club_id);
			clubDto.setUser_id(user_id);
			clubDto.setClub_article_title(club_article_title);
			clubDto.setClub_article_content(club_article_content);
			
			if(clubService.clubBoardWrite(clubDto) != 1) {
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
	
	@PostMapping("club/board/remove")
	public String clubDelete(RedirectAttributes rattr, int club_article_id, int club_id) {
		try {
			
			if(clubService.clubBoardDelete(club_article_id) != 1) {
				rattr.addFlashAttribute("msg", "REMOVE_FAIL");
				System.out.println("글 삭제 실패");
				return "redirect:/club/board/view?id=" + club_id + "&article_id=" + club_article_id;
			}else {
				rattr.addFlashAttribute("msg", "REMOVE_SUCCESS");
				System.out.println("글 삭제 성공");
				return "redirect:/club/detail?id=" + club_id;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "REMOVE_ERR");
			System.out.println("글 삭제 중 예외 발생");
			return "redirect:/club/board/view?id=" + club_id + "&article_id=" + club_article_id;
		}
	}
	
}
