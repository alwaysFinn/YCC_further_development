/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.18
 * 업데이트 내용 : 선택한 동아리 이름에 따라 해당 동아리 상세 정보 페이지로 이동하는 기능
 * 기능 : 동아리 CRUD 기능 구현된 동아리 DaoImpl로 clubmapper와 연결됨 
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

	@Override	// login한 user가 가입한 동아리 목록 가져오는 select / mapper의 selectMyClub과 연결
	public List<ClubDto> selectMyClub(String user_id) throws Exception {
		return session.selectList(namespace + "selectMyClub", user_id);
	}

	@Override	//새로운 동아리 생성하는 insert / mapper의 createClub과 연결
	public int createClub(ClubDto clubDto) throws Exception {
		return session.insert(namespace + "createClub", clubDto);
	}

	@Override	//이미 동아리를 생성한 사람은 또 동아리를 만들 수 없게 하는 validationCheck #1
	public int createOnceValChk(ClubDto clubDto) throws Exception {
		return session.selectOne(namespace + "onlyOnceValChk", clubDto);
	}

	@Override
	public List<ClubDto> selectMasterMyClub(String club_master_id) throws Exception {
		return session.selectList(namespace + "selectMasterMyClub", club_master_id);
	}

	@Override
	public List<ClubDto> selectClubDetail(int club_id) throws Exception {
		return session.selectList(namespace + "selectClubDetail", club_id);
	}

	@Override
	public List<ClubDto> selectClubBoard(int club_id) throws Exception {
		return session.selectList(namespace + "selectBoardFromClub", club_id);
	}

	@Override
	public int clubMemberYn(ClubDto clubDto) throws Exception {
		return session.selectOne(namespace + "clubMemberYn", clubDto);
	}

	@Override
	public int joinClub(ClubDto clubDto) throws Exception {
		return session.insert(namespace + "joinClub", clubDto);
	}

}
