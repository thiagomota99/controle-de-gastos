package br.com.thgyn.modelo.services;

import br.com.thgyn.dao.Persistivel;
import br.com.thgyn.enums.AceitaNulo;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Validador;
import br.com.thgyn.validadores.ValidarReferencia;

public class DespesaService {
	
	private Persistivel<Despesa> repository;
	
	public DespesaService(Persistivel<Despesa> repository) {
		ValidarReferencia.verify(repository, AceitaNulo.NAO);
		this.repository = repository;
	}
	
	public void adicionar(Despesa despesa, Validador<Despesa> validacoes) {
		ValidarReferencia.verify(validacoes, AceitaNulo.NAO);
		validacoes.aplicar(despesa);
		repository.adicionar(despesa);
	}
}
