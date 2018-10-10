package br.com.udemycurso.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.ItemPedido;
import br.com.udemycurso.domain.PagamentoComBoleto;
import br.com.udemycurso.domain.Pedido;
import br.com.udemycurso.domain.enums.EstadoPagamento;
import br.com.udemycurso.repositories.ItemPedidoRepository;
import br.com.udemycurso.repositories.PagamentoRepository;
import br.com.udemycurso.repositories.PedidoRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class PedidoService {

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
		Pedido x = validation(id);	
		return x ;
	}
     
     
	public Pedido validation(Long id){
		Optional<Pedido> x = repo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Estado não encontrada no sistema!")
				);
	}
	
	public Pedido salvar(Pedido obj) {
		obj.setId(null);
		obj.setInstante(LocalDateTime.now());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {			
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.prencherPagamentoComBoleto(pagto , obj.getInstante());
		}
		obj = this.repo.save(obj);
		this.pagRepo.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.buscar(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
	
}
