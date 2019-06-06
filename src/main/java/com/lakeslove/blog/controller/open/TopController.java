package com.lakeslove.blog.controller.open;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TopController {
	private static final Logger log = LoggerFactory.getLogger(TopController.class);


	@RequestMapping("/")
	public String welcomeFile() {
		return "forward:/index.htm";
	}

	@RequestMapping(value = { "index.htm" })
	public String index() {
		log.debug("index.htm");
		return "tiles.view.body.index";
	}
	
	@RequestMapping(value = { "prose.htm" },method = RequestMethod.GET)
	public String prose(Model model) {
		return "tiles.view.body.prose";
	}
	
	@RequestMapping(value = { "poem.htm" },method = RequestMethod.GET)
	public String poem(Model model) {
		return "tiles.view.body.poem";
	}
	
	@RequestMapping(value = { "novel.htm" },method = RequestMethod.GET)
	public String novel(Model model) {
		return "tiles.view.body.novel";
	}
	
	@RequestMapping(value = { "notes.htm" },method = RequestMethod.GET)
	public String notes(Model model) {
		return "tiles.view.body.notes";
	}

}
