package br.com.thgyn.test;

import br.com.thgyn.dao.CategoriaDaoJDBC;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.services.CategoriaService;
import br.com.thgyn.validadores.AdicionarCategoria;
import br.com.thgyn.validadores.AtualizarCategoria;

public class TesteCategoria {

	public static void main(String[] args) {
		Categoria categoria = new Categoria(null, "Saúde");
		new CategoriaService(new CategoriaDaoJDBC()).adicionar(categoria, new AdicionarCategoria());
		new CategoriaService(new CategoriaDaoJDBC()).listar().forEach(c -> System.out.println(c));
		Categoria categoria2 = new CategoriaService(new CategoriaDaoJDBC()).buscar(48);
		new CategoriaService(new CategoriaDaoJDBC()).atualizar(categoria2, new AtualizarCategoria());
		new CategoriaService(new CategoriaDaoJDBC()).deletar(categoria2.getId());
	}
}
