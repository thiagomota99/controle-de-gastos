package br.com.thgyn.test;

import java.util.List;
import java.util.Scanner;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.CategoriaDaoJDBC;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.services.CategoriaService;
import br.com.thgyn.validadores.AdicionarCategoria;
import br.com.thgyn.validadores.AtualizarCategoria;

public class TesteCategoria {

	private static Scanner scanner = new Scanner(System.in);
	private static CategoriaService categoriaService = new CategoriaService(new CategoriaDaoJDBC(DB.getConnection()));
	
	public static void main(String[] args) {
		int opcao = 0;
		while (opcao != 6) {
			System.out.println("=====================CATEGORIAS====================");
			System.out.println("O que deseja fazer? ");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Listar");
			System.out.println("3 - Buscar Categoria");
			System.out.println("4 - Atualizar Categoria");
			System.out.println("5 - Deletar Categoria");
			System.out.println("6 - Sair");
			System.out.print("R: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			if (opcao == 1)
				TesteCategoria.adicionar();
			else if (opcao == 2)
				TesteCategoria.listar();
			else if (opcao == 3)
				TesteCategoria.buscar();
			else if (opcao == 4)
				TesteCategoria.atualizar();
			else if (opcao == 5)
				TesteCategoria.deletar();
		}
		DB.closeConnection();
		System.out.println("Obrigado, volte sempre!!");
	}

	public static void adicionar() {
		System.out.println("===============Adicionando Nova Categoria==================");
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		try {
			categoriaService.adicionar(new Categoria(null, nome), new AdicionarCategoria());
			System.out.println("Categoria adicionada!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("============================================================");
	}

	public static List<Categoria> listar() {
		System.out.println("================Listando Categorias====================");
		List<Categoria> categorias = categoriaService.listar();
		if (categorias.size() > 0) {
			for (Categoria obj : categorias) {
				System.out.println(obj);
			}
		} else {
			System.out.println("Não existe categorias.");
		}
		System.out.println("========================================================");

		return categorias;
	}

	public static void buscar() {
		System.out.println("================Buscar Categoria====================");
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
		Integer id = null;
		String nome = null;

		if (TesteCategoria.listar().size() > 0) {
			System.out.println("================Atualizar Categorias====================");
			System.out.println("Qual de deseja atualizar? ");
			System.out.print("Digite o Id: ");
			id = scanner.nextInt();
			System.out.println("Nome: ");
			scanner.nextLine();
			nome = scanner.nextLine();
			
			try {
				categoriaService.atualizar(new Categoria(id, nome), new AtualizarCategoria());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.printf("Erro: %s", e.getMessage());
			}
		}
	}
	
	public static void deletar() {
		Integer id = null;
		System.out.println("================Deletar Categoria====================");
		try {
			System.out.print("Matricula: ");
			id = scanner.nextInt();
			categoriaService.deletar(id);
			System.out.println("Deletado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		System.out.println("=======================================================");		
	}
}
