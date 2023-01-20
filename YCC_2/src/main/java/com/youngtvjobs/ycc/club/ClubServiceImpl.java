/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.20
 * 업데이트 내용 : 해당 동아리에 가입한 사람인지 아닌지 확인하는 기능 추가
 * 기능 : 동아리 CRUD 기능 구현된 동아리 serviceImpl로 clubDao와 연결됨 
 */


package com.youngtvjobs.ycc.club;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubServiceImpl implements ClubService{
	
	@Autowired
	ClubDao clubDao;

	@Override	//모든 동아리 목록 가져오는 select문 Dao의 selectClub()와 동일
	public List<ClubDto> selectAllClub() throws Exception {
		return clubDao.selectClub();
	}

	@Override
	public List<ClubDto> selectMyClub(String user_id) throws Exception {
		return clubDao.selectMyClub(user_id);
	}

	@Override
	public int createClub(ClubDto clubDto) throws Exception {
		return clubDao.createClub(clubDto);
	}

	@Override
	public int overlapCreateValChk(ClubDto clubDto) throws Exception {
		return clubDao.createOnceValChk(clubDto);
	}

	@Override
	public List<ClubDto> selectMasterMyClub(String club_master_id) throws Exception {
		return clubDao.selectMasterMyClub(club_master_id);
	}

	@Override
	public List<ClubDto> selectClubDetail(int club_id) throws Exception {
		return clubDao.selectClubDetail(club_id);
	}

	@Override
	public List<ClubDto> selectClubBoard(int club_id) throws Exception {
		return clubDao.selectClubBoard(club_id);
	}

	@Override
	public int chkClubMember(ClubDto clubDto) throws Exception {
		return clubDao.clubMemberYn(clubDto);
	}

	@Override
	public int joinClub(ClubDto clubDto) throws Exception {
		return clubDao.joinClub(clubDto);
	}

}
