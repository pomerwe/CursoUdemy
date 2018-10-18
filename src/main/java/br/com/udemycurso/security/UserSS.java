package br.com.udemycurso.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.udemycurso.domain.Senha;
import br.com.udemycurso.domain.enums.Perfil;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String email;
	private Senha senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
		
		
	}
	
	
	
	public UserSS(Long id, String email, Senha senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}



	public Long getId() {
		
		return this.id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {
		
		return senha.getSenha();
	}

	@Override
	public String getUsername() {
		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
