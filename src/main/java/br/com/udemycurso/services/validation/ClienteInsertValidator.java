package br.com.udemycurso.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.udemycurso.domain.enums.TipoCliente;
import br.com.udemycurso.dto.ClienteNewDTO;
import br.com.udemycurso.resources.exceptions.FieldMessage;
import br.com.udemycurso.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFÍSICA) && !BR.isValidCPF(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage ("cpf_cnpj", "CPF inválido"));
		}

		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA) && !BR.isValidCPF(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage ("cpf_cnpj", "CNPJ inválido"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}