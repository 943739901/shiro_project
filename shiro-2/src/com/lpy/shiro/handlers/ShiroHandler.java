package com.lpy.shiro.handlers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/shiro")
@Controller
public class ShiroHandler {
	
	@RequestMapping("/login")
	public String Login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
            	System.out.println(token.hashCode());
                currentUser.login(token);
            } catch (AuthenticationException ae) {
            	System.out.println("登陆失败：" + ae.getMessage());
            }
        }
		
		return "redirect:/list.jsp";
	}
}
