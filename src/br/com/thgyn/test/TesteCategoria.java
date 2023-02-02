package br.com.thgyn.test;

import br.com.thgyn.dao.CategoriaDaoJDBC;
import br.com.thgyn.exceptions.CategoriaException;
import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.services.CategoriaService;
import br.com.thgyn.modelo.services.ServiceCRUD;
import br.com.thgyn.validadores.AdicionarCategoria;
import br.com.thgyn.validadores.AtualizarCategoria;

public class TesteCategoria {
	
	private static ServiceCRUD<Categoria> categoriaService = new CategoriaService(new CategoriaDaoJDBC());
	
	public static void main(String[] args) {
		//TesteCategoria.adicionar();
		//TesteCategoria.listar();
		//TesteCategoria.buscar();
		//TesteCategoria.atualizar();
		TesteCategoria.deletar();
	}
	
	private static void adicionar() {
		Categoria categoria = new Categoria(null, "Saúde");
		
		try {
			categoriaService.adicionar(categoria, new AdicionarCategoria());
		} catch (EntidadeException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static void listar() {
		try {
			categoriaService.listar().forEach(categoria -> System.out.println(categoria));
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void buscar() {
		try {
			System.out.println(categoriaService.buscar(0));
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void atualizar() {
		try {
			categoriaService.atualizar(new Categoria(0, ""), new AtualizarCategoria());
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	
	private static void deletar() {
		try {
			categoriaService.deletar(22);
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			System.out.println(e);
		}		
	}	
}
