package br.com.thgyn.test;

import java.util.Date;

import br.com.thgyn.dao.CategoriaDaoJDBC;
import br.com.thgyn.dao.DespesaDaoJDBC;
import br.com.thgyn.enums.FormaDePagamento;
import br.com.thgyn.exceptions.DespesaException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.modelo.services.impl.CategoriaServiceImpl;
import br.com.thgyn.modelo.services.impl.DespesaServiceImpl;
import br.com.thgyn.services.Atualizavel;
import br.com.thgyn.services.Buscavel;
import br.com.thgyn.services.CategoriaService;
import br.com.thgyn.services.Deletavel;
import br.com.thgyn.services.DespesaService;
import br.com.thgyn.services.Listavel;
import br.com.thgyn.validadores.impl.AdicionarDespesaImpl;
import br.com.thgyn.validadores.impl.AtualizarDespesa;

public class TesteDespesa {
	
	private static CategoriaService categoriaService = new CategoriaServiceImpl(new CategoriaDaoJDBC());
	private static DespesaService despesaService = new DespesaServiceImpl(new DespesaDaoJDBC(), categoriaService);
	
	public static void main(String[] args) {
		TesteDespesa.adicionar();
		//TesteDespesa.listar();
		//TesteDespesa.buscar();
		//TesteDespesa.atualizar();
		//TesteDespesa.deletar();
	}
	
	public static void adicionar() {
		Categoria categoria = new Categoria(41, null);
		Despesa despesa = new Despesa(null, 9.40, FormaDePagamento.CREDITO, new Date(), categoria, "TESTE");
		
		try {
			despesaService.adicionar(despesa, new AdicionarDespesaImpl(new DespesaException(null)));
		} catch (DespesaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void listar() {
		Listavel<Despesa> despesas = despesaService;
		
		try {
			despesas.listar().forEach(despesa -> System.out.println(despesa));
		} catch (DespesaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void buscar() {
		Buscavel<Despesa> despesa = despesaService;
		
		try {
			despesa.buscar(42);
		} catch (DespesaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void atualizar() {
		Despesa obj = new Despesa(null, 9.40, FormaDePagamento.CREDITO, new Date(), null, "TESTE");
		Atualizavel<Despesa> despesa = despesaService;
		
		try {
			despesa.atualizar(obj, new AtualizarDespesa());
		} catch (DespesaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deletar() {
		Deletavel despesa = despesaService;
		
		try {
			despesa.deletar(99);
		} catch (DespesaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
