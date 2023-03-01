/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.03.01
 * 업데이트 내용 : 동아리 게시글 수정하기 post 연동, 게시글 삭제 기능 연동
 * 기능 : 동아리 CRUD 기능 구현된 동아리 Dao file 
 */

package com.youngtvjobs.ycc.club;

import java.util.List;

import com.youngtvjobs.ycc.common.SearchItem;

public interface ClubDao {
	
	List<ClubDto> selectClub() throws Exception;	//모든 동아리 불러오는 기능
	List<ClubDto> selectMyClub(String user_id) throws Exception; //내가 멤버인 동아리 불러오는 기능
	List<ClubDto> mainClubBoard(String club_master_id)throws Exception; // 클럽 메인 화면 게시글 목록 보여주는 기능
	
	int createClub(ClubDto clubDto) throws Exception;	//새로운 동아리 생성하는 기능
	int createOnceValChk(ClubDto clubDto) throws Exception;	//로그인 한 유저가 동아리장인지 확인하는 유효성 체크
	List<ClubDto> selectMasterMyClub(String club_master_id) throws Exception; //내가 동아리 장인 동아리 불러오는 기능
	
	List<ClubDto> selectClubDetail(int club_id) throws Exception; //클럽 id에 따라 해당 클럽으로 이동하는 기능 
	
	List<ClubDto> selectClubBoard(int club_id) throws Exception; //클럽 id에 따라 각 동아리 게시물 가져오는 기능
	
	int clubMemberYn(ClubDto clubDto) throws Exception; //클럽 id와 user_id를 기반으로 비교해 해당 동아리에 가입한 사람인지 아닌지 체크하는 기능
	int clubMasterYn(ClubDto clubDto) throws Exception; //클럽 id와 user_id를 기반으로 비교해 해당 동아리의 장인지 아닌지 체크하는 기능
	
	int joinClub(ClubDto clubDto) throws Exception; //동아리 가입
	int updateClubMemberCnt(Integer club_id) throws Exception;//동아리 가입 시 총원 +1
	
	int allClubSearchResultCnt(SearchItem sc) throws Exception; //클럽 메인 화면 모든 클럽 보여주는 기능
	List<ClubDto> allClubSearchSelectPage(SearchItem sc) throws Exception; // 모든 클럽 검색 결과
	
	//클럽 게시판 
	int clubBoardWrite(ClubDto clubDto) throws Exception;	// 클럽 게시판 작성
	List<ClubDto> clubBoardRead(ClubDto clubDto) throws Exception; //클럽 게시판 상세보기
	int clubBoardCntPlus(int club_article_id)throws Exception; //클럽 게시판 조회수 +증가
	List<ClubDto> clubBoardModRead(int club_article_id) throws Exception; //클럽 게시판 수ㅈㅇ용 상세보기
	int clubBoardModUpdate(ClubDto clubDto) throws Exception; // 클럽 게시판 수정하기
	int clubBoardDelete(int club_article_id) throws Exception; // 클럽 게시판 삭제하기
	int clubSearchResultCnt(ClubSearchItem sc) throws Exception; // 검색 결과 갯수
	List<ClubDto> clubSearchSelectPage(ClubSearchItem sc) throws Exception; // 검색 결과 페이지
	
	
}
