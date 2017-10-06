package all.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import all.entity.User;
import all.service.UserService;

@Controller
@RequestMapping("/registrer")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}
	
	@RequestMapping
	public String showRegister() {
		return "user-registrer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,BindingResult result) {
		if(result.hasErrors()){
			return "user-registrer";
		}
		userService.save(user);
		return "redirect://registrer.html?success=true";
	}
}
