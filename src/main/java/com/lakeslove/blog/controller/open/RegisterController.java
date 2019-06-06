package com.lakeslove.blog.controller.open;

import com.lakeslove.blog.client.RegisterClient;
import com.lakeslove.blog.controller.AbstractController;
import com.lakeslove.blog.dao.UserDao;
import com.lakeslove.blog.model.User;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
		
	@Autowired
	private RegisterClient registerClient;

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = { "register.htm" },method = RequestMethod.GET)
	public String registerGet(Model model) {
		model.addAttribute("user", new User());
		return "tiles.view.body.register";
	}
	
	@ResponseBody
	@RequestMapping(value = { "getVerificationCode.htm" },method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String validateEmail(String email) {
		return registerClient.sendVerificationCode(email);
	}
	
	@RequestMapping(value = { "register.htm" },method = RequestMethod.POST)
	public String registerPost(User user, String code, Model model) {
		
		boolean existVerificationCode = registerClient.valid(user.getEmail(),code);
		if(!existVerificationCode){
			model.addAttribute("user", user);
			model.addAttribute("validateError", getI18nMessage("email.or.verification.code.has.error"));
			return "tiles.view.body.register";
		}
		userDao.insertUser(user);
		HttpSession session = getSession();
		session.setAttribute(SESSION_LOGIN_USER, user);
		return "redirect:index.htm";
	}
	
}
