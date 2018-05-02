package com.hw.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	Logger loggerSpeed = Logger.getLogger("speed");
	Logger loggerTemp = Logger.getLogger("temperature");
	@RequestMapping("/main.do")
	@ResponseBody
	public String main(HttpServletRequest request) {
		String speed = request.getParameter("speed");

		String temp = request.getParameter("temp");
		loggerSpeed.debug(speed);
		loggerTemp.debug(temp);
		return "main";
	}
}



