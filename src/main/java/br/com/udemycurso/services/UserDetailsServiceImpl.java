package br.com.udemycurso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.repositories.ClienteRepository;
import br.com.udemycurso.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepository cliRepo;
		
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = cliRepo.findByEmail(email);
		
		if(cli == null) {			
			throw new UsernameNotFoundException(email);
		}
	
	    return new UserSS(cli.getId(),cli.getEmail(),cli.getSenha(),cli.getPerfis());	
		
		
	
	}

}
