/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.06
 * 업데이트 내용 : 동아리 목록 가져오는 select 기능 추가
 * 기능 : 동아리 불러오기 기능 구현된 동아리 DaoImpl로 clubmapper와 연결됨 
 */


package com.youngtvjobs.ycc.club;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClubDaoImpl implements ClubDao{
	
	@Autowired
	private SqlSession session;
	private static String namespace = "com.youngtvjobs.ycc.club.clubMapper.";

	@Override	// 모든 동아리 목록 가져오는 select / mapper의 selectAllClub와 연결
	public List<ClubDto> selectClub() throws Exception {
		return session.selectList(namespace + "selectAllClub");
	}

}
