package all.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import all.repository.UserRepository;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(userRepository == null) {
			return true;
		}
		return userRepository.findByName(username) == null;
	}

}