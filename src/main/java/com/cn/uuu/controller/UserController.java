//package com.cn.uuu.controller;
//
//
//
//import com.cn.uuu.pojo.User;
//import com.cn.uuu.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//
//	@Resource(name = "userServiceImpl")
//	private UserService userService;
//
//	public UserService getUserService() {
//		return userService;
//	}
//
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//
//
//	@RequestMapping(value = "/showuser.do", method = RequestMethod.POST)
//	public String toIndex(HttpServletRequest request, Model model) {
//		System.out.println("denglu");
//		User userLogin = new User();
//		userLogin.setUsername(request.getParameter("username"));
//		userLogin.setPassword(request.getParameter("password"));
//		System.out.println(request.getMethod());
//		User user = new User();
//
//		if (this.userService.getLoginUser(userLogin)) {
//			user = this.userService.getUserByusername(userLogin.getUsername());
//			System.out.println(user.toString() + "get user success");
//			model.addAttribute("user", user);
//			return "success";
//
//		} else {
//
//			model.addAttribute("user can not in use");
//			System.out.println("can not get user");
//		}
//		return "fail";
//	}
//
//
//	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
//	public String registerIndex(HttpServletRequest request, Model model) {
//
//		//ModelAndView mav=new ModelAndView();
//		System.out.println("zhuce");
//		System.out.println(request.getMethod());
//		User userRegister = new User();
//		String username2 = request.getParameter("username");
//		String password2 = request.getParameter("password");
//		//Integer age2=Integer.parseInt(request.getParameter("age"));
//		System.out.println("username " + username2);
//		userRegister.setUsername(username2);
//		userRegister.setPassword(password2);
//		System.out.println(username2 + "密码是 " + password2);
//
////		System.out.println(this.userService.selectLogin(username2).getUsername());
////		if(this.userService.selectLogin(username2).getUsername()!=null) {
////			System.out.println("hhh");
////			return "userExistence";
////		}
//		System.out.println("aaa");
//		if (userRegister.getUsername() == "") {       //判断用户是否输入
//			System.out.println("hhh");
//			return "zhucefail";
//		} else {
//			System.out.println("abcd");
//			if (this.userService.selectLogin(userRegister.getUsername()).getId() != null) {    //如果用id或者username去判断，会报空指针错误    //判断用户是否已经存在
//				System.out.println("bbb");
//				return "userExistence";
//			} else {
//				System.out.println("yyy");
//				User user2 = new User();
//				user2.setUsername(userRegister.getUsername());
//				user2.setPassword(userRegister.getPassword());
//				userRegister = user2;
//				int id = this.userService.inster(userRegister);
//				User user = this.userService.getUserById(id);
//				model.addAttribute("user", user);
//				return "zhucesucc";
//
//			}
//		}
//	}
//}
