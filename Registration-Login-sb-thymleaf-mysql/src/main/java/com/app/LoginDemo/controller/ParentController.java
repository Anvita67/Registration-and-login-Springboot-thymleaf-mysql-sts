package com.app.LoginDemo.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.LoginDemo.entity.User;
import com.app.LoginDemo.repository.UserRepository;


@Controller
public class ParentController {
 
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/")
	//@ResponseBody
	public  String show() {
		return "home";
		//return "welcome to springboot login and registration!!!!";
	}
	@GetMapping("/login")
    public String loginPage(User user) {
		return "loginPage";
	}
	@PostMapping("/register")
    @ResponseBody
	public String register(@ModelAttribute("user") User user) {
		System.out.println(user);
		String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return "Data saved successfully";
	}
	@PostMapping("/login")
	@ResponseBody
	public String loginProcess(@RequestParam("username") String username,@RequestParam("password") String password) {
	User dbUser = userRepository.findByUsername(username);
	Boolean isPasswordMatch = BCrypt.checkpw(password, dbUser.getPassword());
	if(isPasswordMatch)
		return "welcome to dashboard of user" + dbUser.getName();
	else
	    return"Failed to login";
	
}
}
