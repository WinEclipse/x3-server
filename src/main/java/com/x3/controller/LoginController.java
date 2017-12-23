package com.x3.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x3.core.ResponseState;
import com.x3.core.dto.ParamsBody;
import com.x3.core.dto.ReturnBody;
import com.x3.model.User;


/**
 * 
 * @author heming
 *
 */

@RestController
@RequestMapping("/api")
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers="Accept=application/json")
	public ReturnBody login(@RequestBody ParamsBody paramsBody, HttpServletRequest request) {
		ReturnBody rbody = new ReturnBody();
		Map params = paramsBody.getBody();
		String username = (String) params.get("username");
		String password = (String) params.get("password");
		if("admin".equals(username) && "admin".equals(password)) {
            String token = UUID.randomUUID().toString();
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setUsername(username);
            user.setToken(token);
            rbody.setData(user);
			rbody.setStatus(ResponseState.SUCCESS);
			rbody.setMsg("登录成功");
		} else {
			rbody.setStatus(ResponseState.INVALID_USER);
			rbody.setMsg("登录失败");
		}
		
		return rbody;
	}
	
}
