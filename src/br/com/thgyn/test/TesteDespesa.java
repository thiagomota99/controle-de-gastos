package br.com.thgyn.test;

import java.util.Date;

import br.com.thgyn.dao.CategoriaDaoJDBC;
import br.com.thgyn.dao.DespesaDaoJDBC;
import br.com.thgyn.enums.FormaDePagamento;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.modelo.services.CategoriaService;
import br.com.thgyn.modelo.services.DespesaService;
import br.com.thgyn.validadores.AdicionarDespesa;
import br.com.thgyn.validadores.AtualizarDespesa;

public class TesteDespesa {
	
	public static void main(String[] args) {
		Categoria categoria = new Categoria(41, null);
		Despesa despesa = new Despesa(4, 9.49, FormaDePagamento.CREDITO, new Date(), categoria, "WITCH IT");
		AdicionarDespesa adicionarDespesa = new AdicionarDespesa();
		CategoriaService categoriaService = new CategoriaService(new CategoriaDaoJDBC());
		
		DespesaService despesaService = new DespesaService(new DespesaDaoJDBC(), categoriaService);
		
		//despesaService.adicionar(despesa, adicionarDespesa);
		//despesaService.listar().forEach(item -> System.out.println(item));
		//System.out.println(despesaService.buscar(4));
		//despesaService.atualizar(despesa, new AtualizarDespesa());
		//despesaService.deletar(7);
	}
}
