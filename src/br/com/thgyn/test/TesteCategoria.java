package br.com.thgyn.test;

import java.util.Scanner;

import br.com.thgyn.dao.categoria.CategoriaDAO;
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
		System.out.println("3 - Sair");
		System.out.print("R: ");
		int opcao = scanner.nextInt();
		scanner.nextLine();
		while (opcao != 3) {
			if(opcao == 1) {
				System.out.println("===============Adicionando Nova Categoria==================");
				System.out.print("Nome: ");
				String nome = scanner.nextLine();
				categoriaService.adicionar(new Categoria(null, nome));
				System.out.println("Categoria adicionada!");
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
			System.out.println("O que deseja fazer? ");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Listar");
			System.out.println("3 - Sair");
			System.out.print("R: ");
			opcao = scanner.nextInt();
			scanner.nextLine();
		}
		System.out.println("Obrigado!!");
		scanner.close();
	}

}
