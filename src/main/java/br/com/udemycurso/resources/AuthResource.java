package br.com.udemycurso.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemycurso.dto.EmailDTO;
import br.com.udemycurso.security.JWTUtil;
import br.com.udemycurso.security.UserSS;
import br.com.udemycurso.services.AuthService;
import br.com.udemycurso.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired 
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse res){
		UserSS user = UserService.authenticatedUser();
		String token = jwtUtil.generateToken(user.getUsername());
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("acess-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
		
		
	}
	
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> newPassword(@Valid @RequestBody EmailDTO objDto){
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
		
		
	}


}
