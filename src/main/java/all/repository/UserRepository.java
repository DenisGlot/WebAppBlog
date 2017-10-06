package  all.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import  all.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

}
