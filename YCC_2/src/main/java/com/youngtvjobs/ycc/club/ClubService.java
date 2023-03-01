/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.03.01
 * 업데이트 내용 : 동아리 게시판 수정하기 post 기능 연동, 게시글 삭제 기능 연동
 * 기능 : 동아리 CRUD 기능 구현된 동아리 service file 
 */

package com.youngtvjobs.ycc.club;

import java.util.List;

import com.youngtvjobs.ycc.common.SearchItem;


public interface ClubService {
	
	List<ClubDto> selectAllClub() throws Exception;	//모든 동아리 목록 가져오는 select
	List<ClubDto> selectMyClub(String user_id) throws Exception; //로그인 한 user의 동아리 목록 가져오는 select
	int createClub(ClubDto clubDto)throws Exception; //동아리 생성하는 insert
	int overlapCreateValChk(ClubDto clubDto)throws Exception;	//동아리 장인 유저가 또 동아리를 생성하려고 하는 것을 방지하기 위한 유효성 체크
	List<ClubDto> selectMasterMyClub(String club_master_id) throws Exception; //로그인 한 user의 동아리 목록 가져오는 select
	List<ClubDto> clubMainBoard(String club_master_id)throws Exception; //동아리 메인 페이지 게시글 노출 select
	
	List<ClubDto> selectClubDetail(int club_id) throws Exception; // 선택한 동아리의 id에 따라 해당 동아리로 이동 및 정보들 보여줄 기능
	
	List<ClubDto> selectClubBoard(int club_id) throws Exception; // 선택한 동아리의 id에 따라 해당 동아리의 게시글 불러오는 기능

	int chkClubMember(ClubDto clubDto) throws Exception; //해당 동아리에 가입한 사람인지 아닌지 체크
	int chkClubMaster(ClubDto clubDto) throws Exception; //해당 동아리의 장인지 아닌지 체크
	
	int joinClub(ClubDto clubDto) throws Exception;//동아리 가입하는 insert기능
	
	int getAllClubSearchResultCnt(SearchItem sc) throws Exception; //동아리 검색결과 개수
	List<ClubDto> getAllClubSearchResultPage(SearchItem sc) throws Exception; //동아리 검색 결과 페이징
	
	int clubBoardWrite(ClubDto clubDto)throws Exception; // 작성용 insert
	List<ClubDto> clubBoardRead(ClubDto clubDto)throws Exception; // 읽기용 select
	List<ClubDto> clubBoardModRead(int club_article_id)throws Exception; // 수정용 select
	int clubBoardModPost(ClubDto clubDto) throws Exception; // 수정용 update
	int clubBoardDelete(int club_article_id) throws Exception; // 삭제용 delete
	
	int getClubSearchResultCnt(ClubSearchItem sc) throws Exception;
	List<ClubDto> getClubSearchResultPage(ClubSearchItem sc) throws Exception; 
}

