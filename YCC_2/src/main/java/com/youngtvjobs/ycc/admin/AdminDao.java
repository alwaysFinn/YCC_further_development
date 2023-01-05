package com.youngtvjobs.ycc.admin;

import java.util.List;

public interface AdminDao {
	
	AdminDto select() throws Exception;
	int update(AdminDto adminDto) throws Exception;
	

}
