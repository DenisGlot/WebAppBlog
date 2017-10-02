package  all.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  all.entity.Blog;
import  all.entity.Role;
import all.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	List<Blog> findByUser(User user);

}
