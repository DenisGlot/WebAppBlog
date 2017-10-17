package all.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import all.entity.Blog;
import all.entity.Item;
import all.entity.Role;
import all.entity.User;
import all.repository.BlogRepository;
import all.repository.ItemRepository;
import all.repository.RoleRepository;
import all.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BlogRepository blogRepository;

	@PostConstruct
	public void init() {
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);

			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			User userAdmin = new User();
			userAdmin.setEnabled(true);
			userAdmin.setName("admin");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userAdmin.setPassword(encoder.encode("123456asdzxcv"));
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);

			Blog tomcat = new Blog();
			tomcat.setName("Tomcat expert");
			tomcat.setUrl("http://www.tomcatexpert.com/blog/feed");
			tomcat.setUser(userAdmin);
			blogRepository.save(tomcat);

			// Item item1 = new Item();
			// item1.setBlog(blogJava);
			// item1.setTitle("First");
			// item1.setLink("https://www.optionrally.com/ru/");
			// item1.setPublishedDate(new Date());
			// itemRepository.save(item1);
			//
			// Item item2 = new Item();
			// item2.setBlog(blogJava);
			// item2.setTitle("Second");
			// item2.setLink("https://binomo.com/ru");
			// item2.setPublishedDate(new Date());
			// itemRepository.save(item2);
		}
	}
}