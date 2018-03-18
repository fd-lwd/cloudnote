package com.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

@Controller

public class UserChangePassController {
	@Resource
	UserService userService;
	@RequestMapping("/user/changePass.do")
	@ResponseBody
	public NoteResult execute(String userId,String lastpass,String newpass) throws Exception{
		NoteResult result = new NoteResult();
		result = userService.changePass(userId, lastpass, newpass);
		return result;
	}
}
