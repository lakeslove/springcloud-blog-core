package com.lakeslove.blog.controller.manager;

import com.lakeslove.blog.controller.AbstractController;
import com.lakeslove.blog.model.User;
import com.lakeslove.blog.model.UserMessage;
import com.lakeslove.blog.service.UserMessageService;
import com.lakeslove.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"manager"})
public class PersonalDataController extends AbstractController {
	
	private static final Logger log = LoggerFactory.getLogger(PersonalDataController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMessageService userMessageService;
	
	@RequestMapping(value = { "mypage.htm" },method = RequestMethod.GET)
	public String mypage(Model model) {
		
		User user = (User)getSession().getAttribute(SESSION_LOGIN_USER);
		UserMessage userMessage = userMessageService.getUserMessage(user.getId());
		model.addAttribute("user", user);
		model.addAttribute("userMessage", userMessage);
		return "tiles.view.body.mypage";
	}
	
	@RequestMapping(value = { "setting.htm" },method = RequestMethod.GET)
	public String settingGet(Model model) {
		User user = (User)getSession().getAttribute(SESSION_LOGIN_USER);
		model.addAttribute("user", user);
		return "tiles.view.body.setting";
	}
	
	@RequestMapping(value = { "setting.htm" },method = RequestMethod.POST)
	public String settingPost(Model model, User user) {
		User oldUser = (User)getSession().getAttribute(SESSION_LOGIN_USER);
		oldUser.setName(user.getName());
		oldUser.setPassword(user.getPassword());
		oldUser.setSlogan(user.getSlogan());
		userService.updateUser(oldUser);
//		因为oldUser为引用，所以下面这句可以不写
//		getSession().setAttribute(SESSION_LOGIN_USER, oldUser);
		return "tiles.view.body.mypage";
	}

}
