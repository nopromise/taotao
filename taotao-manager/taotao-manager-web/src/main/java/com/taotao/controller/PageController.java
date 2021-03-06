package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	/**
	 * 访问首页
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	/**
	 * 从首页index.jsp访问不同页面的Controller
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {//@PathVariable:路径变量
		return page;
	}

}
