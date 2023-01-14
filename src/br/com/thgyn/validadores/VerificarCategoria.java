package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.services.CategoriaService;

public class VerificarCategoria extends ValidarAtualizacaoCategoria {
	
	private CategoriaService categoriaService;
	
	public VerificarCategoria(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}
	
	@Override
	public void aplicar(Categoria entity) {
		super.aplicar(entity);
		verify(entity);
	}
	
	public void verify(Categoria entity) {
		categoriaService.buscar(entity.getId());
	}
}
