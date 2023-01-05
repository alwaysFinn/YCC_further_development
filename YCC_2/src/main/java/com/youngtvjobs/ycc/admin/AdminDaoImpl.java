package com.youngtvjobs.ycc.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private SqlSession session;
	private static String namespace = "com.youngtvjobs.ycc.admin.adminMapper.";
	

	@Override
	public AdminDto select() throws Exception {
		return session.selectOne(namespace + "selectAll");
	}


	@Override
	public int update(AdminDto adminDto) throws Exception {
		return session.update(namespace + "modifyterms", adminDto);
	}

}
