/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.06
 * 업데이트 내용 : 동아리 목록 가져오는 select 기능 추가
 * 기능 : 동아리 불러오기 기능 구현된 동아리 serviceImpl로 clubDao와 연결됨 
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

}
