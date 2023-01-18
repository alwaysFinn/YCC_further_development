/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.18
 * 업데이트 내용 : 클릭한 동아리에 따라 club_title을 기반으로 select해오는 기능 추가
 * 기능 : 동아리 CRUD 기능 구현된 동아리 Dao file 
 */

package com.youngtvjobs.ycc.club;

import java.util.List;

public interface ClubDao {
	
	List<ClubDto> selectClub() throws Exception;	//모든 동아리 불러오는 기능
	List<ClubDto> selectMyClub(String user_id) throws Exception; //내가 멤버인 동아리 불러오는 기능 
	int createClub(ClubDto clubDto) throws Exception;	//새로운 동아리 생성하는 기능
	int createOnceValChk(ClubDto clubDto) throws Exception;	//로그인 한 유저가 동아리장인지 확인하는 유효성 체크
	List<ClubDto> selectMasterMyClub(String club_master_id) throws Exception; //내가 동아리 장인 동아리 불러오는 기능
	
	List<ClubDto> selectClubDetail(String club_title) throws Exception; //클럽 제목에 따라 해당 클럽으로 이동하는 기능 
	

}
