package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoginData;
import com.example.demo.entity.Song;
import com.example.demo.entity.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UserServices;

import jakarta.servlet.http.HttpSession;
@CrossOrigin()
@RestController
public class CrudController {
	@Autowired
	SongService ss;
	@Autowired
	UserServices rs;
	
	@PostMapping("/validate")
	public String addvalidate(@RequestBody LoginData data,HttpSession session,Model model) {
		
		System.out.println("call received");
		String email=data.getEmail();
		String password=data.getPassword();
		if(rs.validateUser(email,password) == true) {
			String role=rs.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminHome";
			}else {
				Users user=rs.getUser(email);
				boolean userStatus=user.isPremium();
				List<Song> songList=ss.fetchAllSongs();
				model.addAttribute("songs",songList);
				model.addAttribute("isPremium",userStatus);
		return "customerHome";
			}
		
		}else {
			return "login";
		}
	}
	@PostMapping("/register")

	public String getAllUser(@ModelAttribute Users user) {

		boolean userStatus = rs.emailExists(user.getEmail());
		if(userStatus == false) {
			rs.getAllUser(user);
			System.out.println("user added");
		}else {
			System.out.println("user already exit");
		}

		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}