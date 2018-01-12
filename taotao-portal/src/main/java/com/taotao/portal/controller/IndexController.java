package com.taotao.portal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.service.ContentService;

/**
 * 首页访问Controller
 * 
 * @author fanjunlin
 *
 */
@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		// 取大广告位内容
		String json = contentService.getAd1List();
		model.addAttribute("ad1", json);
		return "index";
	}

	/**
	 * 测试httpClient的Post请求Controller
	 * 
	 * @return
	 */
	@RequestMapping(value = "/posttest", method = RequestMethod.POST)
	@ResponseBody
	public String postTest(@RequestBody Map map) {
		System.out.println(map);
		System.out.println("name" + map.get("name"));
		System.out.println("pass" + map.get("pass"));
		return "OK";
	}

}
