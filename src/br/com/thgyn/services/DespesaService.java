package br.com.thgyn.services;

import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.validadores.AdicionarDespesa;

public interface DespesaService extends Listavel<Despesa>, Buscavel<Despesa>, Atualizavel<Despesa>, Deletavel {
	
	public void adicionar(Despesa obj, AdicionarDespesa validacoes);
}
