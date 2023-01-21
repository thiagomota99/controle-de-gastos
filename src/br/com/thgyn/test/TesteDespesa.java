package br.com.thgyn.test;

import java.util.Date;
import java.util.Scanner;

import br.com.thgyn.dao.CategoriaDAO;
import br.com.thgyn.dao.DespesaDAO;
import br.com.thgyn.enums.FormaDePagamento;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.modelo.services.CategoriaService;
import br.com.thgyn.modelo.services.DespesaService;
import br.com.thgyn.validadores.AdicionarCategoria;
import br.com.thgyn.validadores.AdicionarDespesa;

public class TesteDespesa {

	private static DespesaService despesaSerivce = new DespesaService(new DespesaDAO());
	private static CategoriaService categoriaService = new CategoriaService(new CategoriaDAO());
	
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Categoria categoria = new Categoria(null, "Diversão");
		categoriaService.adicionar(categoria, new AdicionarCategoria());
		
		int opcao = 0;
		while (opcao != 6) {
			System.out.println("=====================DESPESAS====================");
			System.out.println("O que deseja fazer? ");
			System.out.println("1 - Adicionar");
			opcao = scanner.nextInt();
			scanner.nextLine();

			if (opcao == 1)
				TesteDespesa.adicionar();
		}
		System.out.println("Obrigado, volte sempre!!");
	}

	public static void adicionar() {
		try {
			Categoria categoria = categoriaService.buscar(1);
			Despesa despesa = new Despesa(null, 20, FormaDePagamento.PIX, new Date(), categoria, "Monitor 24 Polegadas");
			despesaSerivce.adicionar(despesa, new AdicionarDespesa());
			System.out.println("Despesa adicionada!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
