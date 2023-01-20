package br.com.thgyn.validadores;

import br.com.thgyn.enums.AceitaNulo;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.services.CategoriaService;

public class VerificarCategoria extends ValidarAtualizacaoCategoria {
	
	private CategoriaService categoriaService;
	
	public VerificarCategoria(CategoriaService categoriaService) {
		super();
		ValidarReferencia.verify(categoriaService, AceitaNulo.NAO);
		this.categoriaService = categoriaService;
	}
	
	@Override
	public void aplicar(Categoria entity) {
		super.aplicar(entity);
		verify(entity);
	}
	
	private void verify(Categoria entity) {
		categoriaService.buscar(entity.getId());
	}
}
