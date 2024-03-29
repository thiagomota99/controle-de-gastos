package br.com.thgyn.test;

import br.com.thgyn.dao.CategoriaDaoJDBC;
import br.com.thgyn.exceptions.CategoriaException;
import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.services.impl.CategoriaServiceImpl;
import br.com.thgyn.services.ServiceCRUD;
import br.com.thgyn.validadores.impl.AdicionarCategoriaImp;
import br.com.thgyn.validadores.impl.AtualizarCategoria;

public class TesteCategoria {
	
	private static ServiceCRUD<Categoria> categoriaService = new CategoriaServiceImpl(new CategoriaDaoJDBC());
	
	public static void main(String[] args) {
		TesteCategoria.adicionar();
		TesteCategoria.listar();
		TesteCategoria.buscar();
		TesteCategoria.atualizar();
		TesteCategoria.deletar();
	}
	
	private static void adicionar() {
		Categoria categoria = new Categoria(null, "Sa�de");
		
		try {
			categoriaService.adicionar(categoria, new AdicionarCategoriaImp());
		} catch (EntidadeException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void listar() {
		try {
			categoriaService.listar().forEach(categoria -> System.out.println(categoria));
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void buscar() {
		try {
			System.out.println(categoriaService.buscar(0));
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void atualizar() {
		try {
			categoriaService.atualizar(new Categoria(0, ""), new AtualizarCategoria());
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private static void deletar() {
		try {
			categoriaService.deletar(22);
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
}
