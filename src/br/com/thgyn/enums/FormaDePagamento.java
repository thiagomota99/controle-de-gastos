package br.com.thgyn.enums;

public enum FormaDePagamento {
	
	DINHEIRO(1),
	DEBITO(2),
	PIX(3),
	CREDITO(4);
	
	private int codigo;
	
	private FormaDePagamento(int codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public static FormaDePagamento valueOf(int codigo) {
		for (FormaDePagamento value : FormaDePagamento.values()) {
			if(value.getCodigo() == codigo) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código invalido para Forma de Pagamento.");
	}
}
