package br.com.udemycurso.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.domain.ItemPedido;
import br.com.udemycurso.domain.Pagamento;
import br.com.udemycurso.domain.Pedido;
import br.com.udemycurso.domain.enums.EstadoPagamento;
import br.com.udemycurso.domain.enums.Perfil;
import br.com.udemycurso.dto.PedidoDTO;
import br.com.udemycurso.repositories.EnderecoRepository;
import br.com.udemycurso.repositories.ItemPedidoRepository;
import br.com.udemycurso.repositories.PagamentoRepository;
import br.com.udemycurso.repositories.PedidoRepository;
import br.com.udemycurso.security.UserSS;
import br.com.udemycurso.services.exceptions.AuthorizationException;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class PedidoService {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClienteService cliServ;
	
	@Autowired
	private EnderecoRepository endRepo;
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private PagamentoRepository pagRepo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
public List<Pedido> listar(){
		
		return repo.findAll();
		
	}
	
	public Pedido buscar(Long id){
		
		UserSS user = UserService.authenticatedUser();
		Pedido x = validation(id);
		
		if(user==null || !user.hasRole(Perfil.ADMIN) && user.getId()!= x.getCliente().getId()) {
			throw new AuthorizationException("Acesso negado");			
		}
		
			
		return x ;
	}
     
     
	public Pedido validation(Long id){
		Optional<Pedido> x = repo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Estado não encontrada no sistema!")
				);
	}
//	
//	public Pedido salvar(Pedido obj) {
//		
//		
//		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
//		obj.getPagamento().setPedido(obj);
//		obj.setCliente(cliServ.buscar(obj.getCliente().getId()));
//		if(obj.getPagamento() instanceof PagamentoComBoleto) {			
//			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
//			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
//		}
//		obj = this.repo.save(obj);
//		
//		this.pagRepo.save(obj.getPagamento());
//		for(ItemPedido ip : obj.getItens()) {
//			ip.setDesconto(0.0);
//			ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
//			ip.setPreco(ip.getProduto().getPreco());
//			ip.setPedido(obj);
//			
//		}
//		itemPedidoRepository.saveAll(obj.getItens());
//		emailService.sendOrderEmailConfirmation(obj);
//		return obj;
//	}
	
   public Page<Pedido> pesquisar(Integer page, Integer linesPerPage, String orderBy,String direction){
		
	  
	    UserSS user = UserService.authenticatedUser();
	    Cliente cli = cliServ.buscar(user.getId());		
		if(user==null || !user.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("Acesso negado");			
		}
		@SuppressWarnings("deprecation")
		PageRequest pageRequest= new PageRequest(page,linesPerPage , Direction.valueOf(direction),orderBy);
		Page<Pedido> pedido = repo.findByCliente(cli, pageRequest);
		return pedido;
		
	}
	
   public Pedido savePedido(PedidoDTO objDto) {
	   
	   Pagamento pagamento = objDto.getPagamento(); 
	   pagamento.setEstado(EstadoPagamento.PENDENTE);
	   Pedido pedido = new Pedido();
	   pedido.setCliente(cliServ.buscar(objDto.getIdcliente()));
	   pedido.setEnderecoDeEntrega(endRepo.findById(objDto.getIdenderecoDeEntrega()).get());
	   pedido.setId(null);
	   pedido.setInstante(new Date(System.currentTimeMillis()));
	   pedido.setItens(objDto.getItens());	  
	   pedido = repo.save(pedido);
		pagamento.setPedido(pedido);
		pagRepo.save(pagamento);
	   for(ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
			ip.setSubTotal();
			
		}
		
	   List<ItemPedido> itens = itemPedidoRepository.saveAll(pedido.getItens());
		pedido.setValorTotal(itens);
		pedido = repo.save(pedido);
		
		emailService.sendOrderEmailConfirmation(pedido);
	   return pedido;
   }
   
}
