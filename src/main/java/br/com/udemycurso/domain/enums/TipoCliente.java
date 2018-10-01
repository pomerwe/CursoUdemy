package br.com.udemycurso.domain.enums;

import java.util.ArrayList;
import java.util.List;

import br.com.udemycurso.domain.Pedido;

public enum TipoCliente {
	
	PESSOAFÍSICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Jurídica");
	

	private int cod;	
	private String descricao;
	
	
	private List<Pedido> pedidos = new ArrayList<>();
	
	private TipoCliente(int cod, String descricao) {
		
		this.cod=cod;
		this.descricao=descricao;
		
	}
	
	public int getCod() {
		
		return this.cod;
	}
	public String getDescricao() {
		
		return this.descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for(TipoCliente tipoCliente : TipoCliente.values()) {
			
			if(cod.equals(tipoCliente.getCod())) {
				
				return tipoCliente;
			}
			
		}
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
