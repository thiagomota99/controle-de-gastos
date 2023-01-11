package br.com.thgyn.test;

import java.util.Scanner;

import br.com.thgyn.dao.CategoriaDAO;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.services.CategoriaService;

public class TesteCategoria {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CategoriaService categoriaService = new CategoriaService(new CategoriaDAO());
		
		System.out.println("=====================CATEGORIAS====================");
		System.out.println("O que deseja fazer? ");
		System.out.println("1 - Adicionar");
		System.out.println("2 - Listar");
		System.out.println("3 - Lista uma Categoria");
		System.out.println("4 - Sair");
		System.out.print("R: ");
		int opcao = scanner.nextInt();
		scanner.nextLine();
		while (opcao != 4) {
			if(opcao == 1) {
				System.out.println("===============Adicionando Nova Categoria==================");
				System.out.print("Nome: ");
				String nome = scanner.nextLine();
				try {
					categoriaService.adicionar(new Categoria(null, nome));
					System.out.println("Categoria adicionada!");
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("============================================================");
			}
			if(opcao == 2) {
				System.out.println("================Listando Categorias====================");
				for(Categoria obj : categoriaService.listar()) {
					System.out.println("Id: " + obj.getId());
					System.out.println("Nome: " + obj.getNome());
				}
				System.out.println("========================================================");
			}
			
			if(opcao == 3) {
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
			System.out.println("O que deseja fazer? ");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Listar");
			System.out.println("3 - Lista uma Categoria");
			System.out.println("4 - Sair");
			System.out.print("R: ");
			opcao = scanner.nextInt();
			scanner.nextLine();
		}
		System.out.println("Obrigado!!");
		scanner.close();
	}

}
