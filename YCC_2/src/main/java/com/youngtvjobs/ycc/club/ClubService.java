/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.11
 * 업데이트 내용 : 내가 동아리 장인 동아리 불러오는 기능 추가
 * 기능 : 동아리 CRUD 기능 구현된 동아리 service file 
 */

package com.youngtvjobs.ycc.club;

import java.util.List;


public interface ClubService {
	
	List<ClubDto> selectAllClub() throws Exception;	//모든 동아리 목록 가져오는 select
	List<ClubDto> selectMyClub(String user_id) throws Exception; //로그인 한 user의 동아리 목록 가져오는 select
	int createClub(ClubDto clubDto)throws Exception; //동아리 생성하는 insert
	int overlapCreateValChk(ClubDto clubDto)throws Exception;	//동아리 장인 유저가 또 동아리를 생성하려고 하는 것을 방지하기 위한 유효성 체크
	List<ClubDto> selectMasterMyClub(String club_master_id) throws Exception; //로그인 한 user의 동아리 목록 가져오는 select
}
