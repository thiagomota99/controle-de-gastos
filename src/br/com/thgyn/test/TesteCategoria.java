package br.com.thgyn.test;

import java.util.List;
import java.util.Scanner;

import br.com.thgyn.dao.CategoriaDAO;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.services.CategoriaService;
import br.com.thgyn.validadores.AdicaoCategoria;
import br.com.thgyn.validadores.AtualizarCategoria;

public class TesteCategoria {
	
	private static CategoriaService categoriaService = new CategoriaService(new CategoriaDAO());
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opcao = 0;
		while(opcao != 5) {
			System.out.println("=====================CATEGORIAS====================");
			System.out.println("O que deseja fazer? ");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Listar");
			System.out.println("3 - Lista uma Categoria");
			System.out.println("4 - Atualizar uma Categoria");
			System.out.println("5 - Sair");
			System.out.print("R: ");
			opcao = scanner.nextInt();
			scanner.nextLine();
			
			if(opcao == 1)
				TesteCategoria.adicionar();
			else if (opcao == 2)
				TesteCategoria.listar();
			else if (opcao == 3)
				TesteCategoria.buscar();
			else if (opcao == 4)
				TesteCategoria.atualizar();
		}
		System.out.println("Obrigado, volte sempre!!");
	}
	
	public static void adicionar() {
		System.out.println("===============Adicionando Nova Categoria==================");
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		try {
			categoriaService.adicionar(new Categoria(null, nome), new AdicaoCategoria());
			System.out.println("Categoria adicionada!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("============================================================");
	}
	
	public static void listar() {
		System.out.println("================Listando Categorias====================");
		for(Categoria obj : categoriaService.listar()) {
			System.out.println("Id: " + obj.getId());
			System.out.println("Nome: " + obj.getNome());
		}
		System.out.println("========================================================");
	}
	
	public static void buscar() {
		System.out.println("================Buscar Categorias====================");
		try {
			System.out.print("Matricula: ");
			Integer id = scanner.nextInt();
			Categoria categoria = categoriaService.buscar(id);
			System.out.println(categoria);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("========================================================");
	}
	
	public static void atualizar() {
		List<Categoria> categorias = categoriaService.listar();
		int indice = 1;
		System.out.println("================Atualizar Categorias====================");
		for (Categoria categoria : categorias) {
			System.out.println("Categoria " + indice + ": " + categoria);
		}
		System.out.print("Qual deseja atualizar: ");
		indice = scanner.nextInt();
		Categoria obj = categorias.get(indice - 1);
		System.out.println("Nome: ");
		scanner.nextLine();
		obj.setNome(scanner.nextLine());
		categoriaService.atualizar(obj, new AtualizarCategoria());
	}
}
