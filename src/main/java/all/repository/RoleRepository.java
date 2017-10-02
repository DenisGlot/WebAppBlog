package  all.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import  all.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
