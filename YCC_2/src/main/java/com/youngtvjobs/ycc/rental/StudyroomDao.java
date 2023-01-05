package com.youngtvjobs.ycc.rental;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudyroomDao {
		
	List<StudyroomDto> sroomDefault() throws Exception;
	int insertStudyroomrental(StudyroomDto studyroomDto)throws Exception;
	int chkRental(StudyroomDto studyroomDto) throws Exception;
	
	

}
