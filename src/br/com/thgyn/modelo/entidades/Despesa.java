package br.com.thgyn.modelo.entidades;

import java.util.Date;

import br.com.thgyn.enums.FormaDePagamento;

public class Despesa {
	
	private final Integer id;
	private final Double valor;
	private final FormaDePagamento formaDePagamento;
	private final Date data;
	private final String descricao;
	
	private Categoria categoria;
	
	public Despesa(Integer id, double valor, FormaDePagamento fp, Date data, Categoria categoria, String descricao) {
		this.id = id;
		this.valor = valor;
		this.formaDePagamento = fp;
		this.data = data;
		this.categoria = categoria;
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public Double getValor() {
		return valor;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public Date getData() {
		return data;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "Despesa [id=" + id + ", valor=" + valor + ", formaDePagamento=" + formaDePagamento + ", data=" + data
				+ ", categoria=" + categoria + ", descricao=" + descricao + "]";
	}
}
