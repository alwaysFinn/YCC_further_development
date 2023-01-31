/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.31
 * 업데이트 내용 : user_id == club_master_id 체크하는 기능 추가
 * 기능 : 동아리 CRUD 기능 구현된 동아리 serviceImpl로 clubDao와 연결됨 
 */


package com.youngtvjobs.ycc.club;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youngtvjobs.ycc.common.SearchItem;

@Service
public class ClubServiceImpl implements ClubService{
	
	@Autowired
	ClubDao clubDao;

	@Override	//모든 동아리 목록 가져오는 select문 Dao의 selectClub()와 동일
	public List<ClubDto> selectAllClub() throws Exception {
		return clubDao.selectClub();
	}

	//내가 가입한 동아리 목록 가져오는 select
	@Override
	public List<ClubDto> selectMyClub(String user_id) throws Exception {
		return clubDao.selectMyClub(user_id);
	}

	//동아리 만드는 insert
	@Override
	public int createClub(ClubDto clubDto) throws Exception {
		return clubDao.createClub(clubDto);
	}

	//이미 만든 동아리가 있는지 확인하는 유효성 체크#1
	@Override
	public int overlapCreateValChk(ClubDto clubDto) throws Exception {
		return clubDao.createOnceValChk(clubDto);
	}

	//내가 동아리 장인 동아리 목록 보여주는 select
	@Override
	public List<ClubDto> selectMasterMyClub(String club_master_id) throws Exception {
		return clubDao.selectMasterMyClub(club_master_id);
	}

	//특정 동아리 접근 시 해당 동아리 id를 기반으로 상세보기(이름, 설명, 멤버 수 ,...) 제공하는 select
	@Override
	public List<ClubDto> selectClubDetail(int club_id) throws Exception {
		return clubDao.selectClubDetail(club_id);
	}

	//해당 동아리 게시글 불러오는 select
	@Override
	public List<ClubDto> selectClubBoard(int club_id) throws Exception {
		return clubDao.selectClubBoard(club_id);
	}

	//해당 동아리에 가입한 멤버인지 아닌지 체크하는 select 
	@Override
	public int chkClubMember(ClubDto clubDto) throws Exception {
		return clubDao.clubMemberYn(clubDto);
	}
	
	//해당 동아리의 장인지 아닌지 체크하는 select
	@Override
	public int chkClubMaster(ClubDto clubDto) throws Exception {
		return clubDao.clubMasterYn(clubDto);
	}

	//동아리 가입하는 insert와 가입, 동시에 해당 동아리 총원 수 +1하는 update
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int joinClub(ClubDto clubDto) throws Exception {
		clubDao.updateClubMemberCnt(clubDto.getClub_id(), 1);// 동아리 가입 시 동아리 총원 +1 동시에 진행되게 설정
		return clubDao.joinClub(clubDto);
	}
	
	@Override
	public int getAllClubSearchResultCnt(SearchItem sc) throws Exception {
		return clubDao.allClubSearchResultCnt(sc);
	}

	@Override
	public List<ClubDto> getAllClubSearchResultPage(SearchItem sc) throws Exception {
		return clubDao.allClubSearchSelectPage(sc);
	}

	@Override
	public int BoardWrite(ClubDto clubDto) throws Exception {
		return clubDao.clubBoardWrite(clubDto);
	}
	
	@Override
	public List<ClubDto> BoardRead(ClubDto clubDto) throws Exception {
		return clubDao.clubBoardRead(clubDto);
	}

	@Override
	public int getClubSearchResultCnt(ClubSearchItem sc) throws Exception {
		return clubDao.clubSearchResultCnt(sc);
	}

	@Override
	public List<ClubDto> getClubSearchResultPage(ClubSearchItem sc) throws Exception {
		return clubDao.clubSearchSelectPage(sc);
	}

	

	

	
}
