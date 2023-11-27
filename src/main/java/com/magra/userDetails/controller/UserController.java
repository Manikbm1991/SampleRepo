package com.magra.userDetails.controller;

import java.net.http.HttpClient.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.magra.userDetails.UserRepo.UserDao;
import com.magra.userDetails.model.UserInfo;


@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@GetMapping("/welcomePage")
	public String getUserWelcomePange(Model m) {
		List<UserInfo> user=new ArrayList<UserInfo>();
		user=userDao.findAll();
		m.addAttribute("userList", user);	
		return "welcomePage";
	}
	
	@GetMapping("/userpage")
	public String getAddUser(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		return "addUserPage";
	}

	@PostMapping("/addUser")
	public String addUser(@ModelAttribute UserInfo ui,RedirectAttributes redirectAttributes) {
		userDao.save(ui);
		redirectAttributes.addFlashAttribute("message", "User added successfully!");
		 return "redirect:/welcomePage";
	}
	
	@GetMapping("/editUsers/{id}")
	public String getUpdateUser(@PathVariable long id, Model model) {
		Optional<UserInfo> ui=userDao.findById(id);
		ui.ifPresent(item->model.addAttribute("userInfo", item));
		return "editUsers";
	}

	@PostMapping("/UpdatedUser")
	public String updateUser(@ModelAttribute UserInfo ui) {
		if(userDao.existsById(ui.getId())) {
		userDao.save(ui);
		}
		 return "redirect:/welcomePage";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable long id,UserInfo ui) {
		if(userDao.existsById(ui.getId())) {
		userDao.deleteById(id);
		}
		 return "redirect:/welcomePage";
	}
}
