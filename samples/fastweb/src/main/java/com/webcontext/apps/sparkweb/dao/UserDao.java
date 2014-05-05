package com.webcontext.apps.sparkweb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webcontext.apps.sparkweb.model.User;

public class UserDao extends GenericDao<User, Long> {
	public UserDao() {
		super();
	}

	public UserDao(String unitName) {
		super(unitName);
	}
	
	public List<User> findByUsername(String username){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username",username);
		return findByNamedQuery("findByUsername", params);
	}
	public List<User> findByLastname(String lastname){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("lastname",lastname);
		return findByNamedQuery("findByLastname", params);
	}
}
