package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 处理器类
 *
 */
@Controller
public class HelloController {
	
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println("hello()");
		//返回视图名
		return "hello";
	}
	
	@RequestMapping(value="/toLogin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "login";
	}
	
	@RequestMapping("/login.do")
	/*
	 * DispatcherServlet在调用处理器方法
	 * 之前，会分析方法的结构（使用反射机制）,
	 * 如果发现需要一个request对象，则
	 * DispatcherServlet会将request对象传递
	 * 进来。(了解)
	 */
	public String login(
			HttpServletRequest request){
		System.out.println("login()");
		String adminCode = 
			request.getParameter("adminCode");
		System.out.println("adminCode:"
			+ adminCode);
		return "index";
	}
	
	@RequestMapping("/login2.do")
	/**
	 * 建议在每一个形参前，都添加上@RequestParam。
	 */
	public String login2(
			@RequestParam("adminCode") 
			String code,
			String pwd){
		System.out.println("login2()");
		System.out.println("adminCode:"
				+ code);
		return "index";
	}
	
	@RequestMapping("/login3.do")
	public String login3(AdminParam ap){
		System.out.println("login3()");
		System.out.println("adminCode:" 
		+ ap.getAdminCode());
		return "index";
	}
	
	@RequestMapping("/login4.do")
	public String login4(AdminParam ap,
			HttpServletRequest request){
		System.out.println("login4()");
		System.out.println("adminCode:" 
				+ ap.getAdminCode());
		//绑订数据到request对象
		request.setAttribute("adminCode", 
				ap.getAdminCode());
		//DispatcherServlet默认使用转发
		return "index";
	}
	
	@RequestMapping("/login5.do")
	public ModelAndView login5(AdminParam ap){
		System.out.println("login5()");
		System.out.println("adminCode:" 
				+ ap.getAdminCode());
		/*
		 * 将处理结果添加到ModelAndView对象里面。
		 * ModelAndView(String viewName,Map data)
		 */
		Map<String,Object> data = 
				new HashMap<String,Object>();
		//相当于request.setAttribute(
		//"adminCode",ap.getAdminCode());
		data.put("adminCode", 
				ap.getAdminCode());
		ModelAndView mav = 
				new ModelAndView("index",data);
		return mav;
	}
	
	@RequestMapping("/login6.do")
	public String login6(AdminParam ap,
			ModelMap mm){
		System.out.println("login6()");
		System.out.println("adminCode:" 
				+ ap.getAdminCode());
		//将数据添加到ModelMap对象
		//相当于执行了request.setAttribute
		mm.addAttribute("adminCode",
				ap.getAdminCode());
		return "index";
	}
	
	@RequestMapping("/login7.do")
	public String login7(AdminParam ap,
			HttpSession session){
		System.out.println("login7()");
		System.out.println("adminCode:" 
				+ ap.getAdminCode());
		//绑订数据到session对象
		session.setAttribute("adminCode",
				ap.getAdminCode());
		return "index";
	}
	
	@RequestMapping("/login8.do")
	public String login8(){
		System.out.println("login8()");
		return "redirect:toIndex.do";
	}
	
	@RequestMapping("/login9.do")
	public ModelAndView login9(){
		System.out.println("login9()");
		RedirectView rv = 
				new RedirectView("toIndex.do");
		ModelAndView mav = 
				new ModelAndView(rv);
		return mav;
	}
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "index";
	}
	
	
	
	
	@RequestMapping("/toBmi.do")
	public String toBmi(){
		System.out.println("toBmi()");
		return "bmi";
	}
	
	@RequestMapping("/bmi.do")
	public String bmi(
			HttpServletRequest request){
		System.out.println("bmi()");
		String height = 
			request.getParameter("height");
		String weight = 
			request.getParameter("weight");
		double bmi = 
			Double.parseDouble(weight) /
			Double.parseDouble(height)/
			Double.parseDouble(height);
		String status = "正常";
		if(bmi < 19){
			status = "过轻";
		}
		if(bmi > 25){
			status = "过重";
		}
		request.setAttribute("status", status);
		return "view";
	}
	
}

