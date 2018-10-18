package br.com.udemycurso.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.udemycurso.security.UserSS;

@Service
public class UserService {

	public static UserSS authenticatedUser() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   }
		catch(Exception e){
			return null;
			
		}
	}
}
