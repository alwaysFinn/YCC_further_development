package com.youngtvjobs.ycc.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDao adminDao;

	@Override
	public AdminDto select() throws Exception {
		return adminDao.select();
	}

	@Override
	public int joinTermsUpdate(AdminDto adminDto) throws Exception {
		return adminDao.update(adminDto);
	}
	
	
}
