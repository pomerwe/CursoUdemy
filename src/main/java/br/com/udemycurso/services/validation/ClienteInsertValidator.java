package br.com.udemycurso.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.domain.enums.TipoCliente;
import br.com.udemycurso.dto.ClienteNewDTO;
import br.com.udemycurso.repositories.ClienteRepository;
import br.com.udemycurso.resources.exceptions.FieldMessage;
import br.com.udemycurso.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFÍSICA) && !BR.isValidCPF(objDto.getCpfcnpj())) {
			list.add(new FieldMessage ("cpf_cnpj", "CPF inválido"));
		}

		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA) && !BR.isValidCPF(objDto.getCpfcnpj())) {
			list.add(new FieldMessage ("cpf_cnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux!=null) {
			
			list.add(new FieldMessage("email","Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}