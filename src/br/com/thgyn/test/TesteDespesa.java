package br.com.thgyn.test;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.CategoriaDaoJDBC;
import br.com.thgyn.dao.DespesaDAO;
import br.com.thgyn.enums.FormaDePagamento;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.modelo.services.CategoriaService;
import br.com.thgyn.modelo.services.DespesaService;
import br.com.thgyn.validadores.AdicionarCategoria;
import br.com.thgyn.validadores.AdicionarDespesa;
import br.com.thgyn.validadores.AtualizarCategoria;
import br.com.thgyn.validadores.AtualizarDespesa;

public class TesteDespesa {
	
	private static CategoriaService categoriaService = new CategoriaService(new CategoriaDaoJDBC(DB.getConnection()));
	private static DespesaService despesaSerivce = new DespesaService(new DespesaDAO(), categoriaService);
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		Categoria categoria = new Categoria(null, "Diversão");
		categoriaService.adicionar(categoria, new AdicionarCategoria());
		
		int opcao = 0;
		while (opcao != 6) {
			System.out.println("=====================DESPESAS====================");
			System.out.println("O que deseja fazer? ");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Listar");
			System.out.println("3 - Buscar");
			System.out.println("4 - Atualizar");
			System.out.println("5 - Deletar");
			System.out.println("6 - Sair");
			opcao = scanner.nextInt();
			scanner.nextLine();

			if (opcao == 1)
				TesteDespesa.adicionar();
			else if (opcao == 2)
				TesteDespesa.listar();
			else if (opcao == 3)
				TesteDespesa.buscar();
			else if (opcao == 4)
				TesteDespesa.atualizar();
			else if (opcao == 5)
				TesteDespesa.deletar();
		}
		System.out.println("Obrigado, volte sempre!!");
	}

	public static void adicionar() {
		try {
			Categoria categoria = categoriaService.buscar(1);
			Despesa despesa = new Despesa(null, 20.00, FormaDePagamento.PIX, new Date(), categoria, "Monitor 24 Polegadas");
			despesaSerivce.adicionar(despesa, new AdicionarDespesa());
			System.out.println("Despesa adicionada!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static List<Despesa> listar() {
		System.out.println("================Listando Despesas====================");
		List<Despesa> despesas = despesaSerivce.listar();
		if (despesas.size() > 0)
			despesas.forEach(obj -> System.out.println(obj));
		else 
			System.out.println("Não existe despesas.");
		System.out.println("======================================================");

		return despesas;
	}
	
	public static void buscar() {
		System.out.println("================Buscar Despesa====================");
		try {
			System.out.print("Matricula: ");
			Integer id = scanner.nextInt();
			Despesa despesa = despesaSerivce.buscar(id);
			System.out.println(despesa);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("========================================================");
	}
	
	public static void atualizar() {
		Integer id = null;
		String nome = null;
		Double valor = null;

		if (TesteDespesa.listar().size() > 0) {
			System.out.println("================Atualizar Despesa====================");
			System.out.println("Qual de deseja atualizar? ");
			System.out.print("Digite o Id: ");
			id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Nome: ");
			nome = scanner.nextLine();
			System.out.println("Valor: ");
			valor = scanner.nextDouble();
			
			try {
				Despesa despesa = despesaSerivce.buscar(id);
				despesa.setDescricao(nome);
				despesa.setValor(valor);
				despesaSerivce.atualizar(despesa, new AtualizarDespesa(new AtualizarCategoria()));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.printf("Erro: %s", e.getMessage());
			}
		}
	}
	
	public static void deletar() {
		Integer id = null;
		System.out.println("================Deletar Despesa====================");
		try {
			System.out.print("Matricula: ");
			id = scanner.nextInt();
			despesaSerivce.deletar(id);
			System.out.println("Deletado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		System.out.println("========================================================");		
	}
}
