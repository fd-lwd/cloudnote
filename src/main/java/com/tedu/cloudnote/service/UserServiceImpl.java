package com.tedu.cloudnote.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteException;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("userService")//扫描
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;//注入
	
	@Transactional(readOnly=true)
	public NoteResult checkLogin(
			String name, String password) {
		NoteResult result = new NoteResult();
		//判断用户名
		User user = userDao.findByName(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//判断密码
		//将用户输入的明文加密
		try{
			String md5_pwd = NoteUtil.md5(password);
			if(!user.getCn_user_password()
					.equals(md5_pwd)){
				result.setStatus(2);
				result.setMsg("密码错误");
				return result;
			}
		}catch(Exception e){
			throw new NoteException(
				"密码加密异常", e);	
		}
		//登录成功
		result.setStatus(0);
		result.setMsg("登录成功");
		user.setCn_user_password("");//把密码屏蔽不返回
		result.setData(user);//返回user信息
		return result;
	}

	@Transactional
	public NoteResult addUser(
		String name, String nick, String password) {
		NoteResult result = new NoteResult();
		try{
			//检测是否重名
			User has_user = 
				userDao.findByName(name);
			if(has_user != null){
				result.setStatus(1);
				result.setMsg("用户名已被占用");
				return result;
			}
			//执行用户注册
			User user = new User();
			user.setCn_user_name(name);//设置用户名
			user.setCn_user_nick(nick);//设置昵称
			String md5_pwd = NoteUtil.md5(password);
			user.setCn_user_password(md5_pwd);//设置加密密码
			String userId = NoteUtil.createId();
			user.setCn_user_id(userId);//设置用户ID
			userDao.save(user);
			//创建返回结果
			result.setStatus(0);
			result.setMsg("注册成功");
			/*//模拟异常
			String s = null;
			s.length();//抛一个空指针异常*/
			return result;
		}catch(Exception e){
			throw new NoteException("用户注册异常",e);
		}
		
	}
/*修改密码*/
	public NoteResult changePass(String userId, String lastpass, String newpass) throws Exception {		
		NoteResult result = new NoteResult();
		String md5 = NoteUtil.md5(lastpass);
		String Sqllastpass = userDao.findPassByuserId(userId);
		if(!Sqllastpass.equals(md5)){
			result.setStatus(1);
			result.setMsg("原密码错误");
			return result;
		}
		User user= new User();
		user.setCn_user_id(userId);
		user.setCn_user_password(NoteUtil.md5(newpass));
		userDao.changePass(user);
		result.setStatus(0);
		result.setMsg("修改密码成功");
		return result;
		
	}

}
