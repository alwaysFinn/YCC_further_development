package com.youngtvjobs.ycc.rental;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyroomServiceImpl implements StudyroomService{
	
	@Autowired
	StudyroomDao studyroomDao;

	@Override
	public List<StudyroomDto> sroomFix() throws Exception {
		return studyroomDao.sroomDefault();
	}

	@Override
	public int sroomInsert(StudyroomDto studyroomDto) throws Exception {
		return studyroomDao.insertStudyroomrental(studyroomDto);
	}

	@Override
	public int validationChkRentaled(StudyroomDto studyroomDto) throws Exception {
		return studyroomDao.chkRental(studyroomDto);
	}
	
	
}
	