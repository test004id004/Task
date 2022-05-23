package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DataSource datasource;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		user.setUnmaskedPassword(user.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		//String encodedPassword = user.getPassword();
		user.setPassword(encodedPassword);
		userRepo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model, @RequestParam(name="email", defaultValue = "") String emailId) {
		try{
			if (null == emailId || emailId.isEmpty()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				CustomUserDetails userr = (CustomUserDetails) auth.getPrincipal();
				emailId = userr.getUsername();
			}
			String query = "SELECT first_name, last_name, email, unmasked_password from users where email=\"" + emailId + "\"";
			Connection conn = datasource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			List<User> listUsers = new ArrayList<User>();
			while(resultSet.next()) {
				User u = new User();
				u.setEmail(resultSet.getString("email"));
				u.setUnmaskedPassword(resultSet.getString("unmasked_password"));
				u.setFirstName(resultSet.getString("first_name"));
				u.setLastName(resultSet.getString("last_name"));
				listUsers.add(u);
			}
			model.addAttribute("listUsers", listUsers);

			return "users";

		} catch (Exception e) {
			System.out.println(e);
		}
		return "users";
	}
}
