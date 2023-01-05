package com.youngtvjobs.ycc.rental;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudyroomDaoImpl implements StudyroomDao{
	
	@Autowired
	private SqlSession session;
	private static String namespace = "com.youngtvjobs.ycc.rental.studyroomMapper.";

	@Override
	public List<StudyroomDto> sroomDefault() throws Exception {
		return session.selectList(namespace + "studyroomRentalChk");
	}

	@Override
	public int insertStudyroomrental(StudyroomDto studyroomDto) throws Exception {
		return session.insert(namespace + "insertRentalInfo", studyroomDto);
	}

	@Override
	public int chkRental(StudyroomDto studyroomDto) throws Exception{
		return session.selectOne(namespace + "rentalChk", studyroomDto);
	}
	

}
