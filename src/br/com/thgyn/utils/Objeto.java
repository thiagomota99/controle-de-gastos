package br.com.thgyn.utils;


public class Objeto {
	
	public static void isNotNull(Object ref) {
		if (ref == null) {
			throw new NullPointerException("Objeto não pode ser nulo.");
		}
	}
	
	public static void isNull(Object ref) {
		if(ref != null) {
			throw new IllegalArgumentException("Objeto deve ser ser nulo.");
		}
	}
	
	public static void isNotLessOrEqualZero(Integer valor) {
		isNotNull(valor);
		if(valor.intValue() <= 0)
			throw new IllegalArgumentException("Objeto menor ou igual a zero.");
	}
	
	public static void isNotLessOrEqualZero(Double valor) {
		isNotNull(valor);
		if(valor.doubleValue() <= 0)
			throw new IllegalArgumentException("Objeto menor ou igual a zero.");
	}
}
