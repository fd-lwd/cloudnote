package com.tedu.cloudnote.dao;

import com.tedu.cloudnote.entity.User;

public interface UserDao {
	public void save(User user);
	public User findByName(String name);
	/*根据用户id查找用户原来密码*/
	public String findPassByuserId(String userId);
	/*根据用户id更改用户密码*/
	public void changePass(User user);
}
