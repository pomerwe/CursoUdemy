package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.udemycurso.domain.Cidade;
import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.domain.Endereco;
import br.com.udemycurso.domain.enums.TipoCliente;
import br.com.udemycurso.dto.ClienteDTO;
import br.com.udemycurso.dto.ClienteNewDTO;
import br.com.udemycurso.repositories.CidadeRepository;
import br.com.udemycurso.repositories.ClienteRepository;
import br.com.udemycurso.repositories.EnderecoRepository;
import br.com.udemycurso.services.exceptions.BadRequestException;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository endRepo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
public List<Cliente> listar(){
		
		return repo.findAll();
		
	}
	
	public Cliente buscar(Long id){
		Cliente x = validation(id);	
		return x ;
	}
     
     
	public Cliente validation(Long id){
		Optional<Cliente> x = repo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Cliente não encontrado no sistema!")
				);
	}
     
     
	@Transactional
	public Cliente salvar(Cliente obj) {		
		obj.setId(null);
		obj = this.repo.save(obj);
		endRepo.saveAll(obj.getEnderecos());
		return obj ;
		
	}
	
	public Cliente atualizar(Long id, Cliente obj) {
		
		Cliente newObj=buscar(id);		
		updateData(newObj,obj);
		
		return this.repo.save(newObj);
		
		
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
		
	}

	public void deletar(Long id) {
		Cliente cat = validation(id);
		try {
			this.repo.deleteById(cat.getId());
			
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("Não é possível excluir pois há pedidos relacionados!");
		}
		
	}
	public Page<Cliente> listarPages(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
		
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null,objDto.getNome(),objDto.getEmail(),objDto.getCpfcnpj(),TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = cidadeRepository.findById(objDto.getIdCidade()).get();
	    Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemeto(),objDto.getBairro(),objDto.getCep(),cid,cli);
	    cli.getEnderecos().add(end);
	    cli.getTelefones().add(objDto.getTelefone1());
	    if(objDto.getTelefone2()!=null) {
	    	
	    	cli.getTelefones().add(objDto.getTelefone2());
	    }
	    if(objDto.getTelefone3()!=null) {
	    	
	    	cli.getTelefones().add(objDto.getTelefone3());
	    }
	    return cli;
	}
}
