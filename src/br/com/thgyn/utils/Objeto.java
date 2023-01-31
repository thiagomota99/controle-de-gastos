package br.com.thgyn.utils;


public class Objeto {
	
	public static void notNullOrException(Object ref) {
		if (ref == null) {
			throw new NullPointerException("Objeto não pode ser nulo.");
		}
	}
	
	public static void nullOrException(Object ref) {
		if(ref != null) {
			throw new IllegalArgumentException("Objeto deve ser ser nulo.");
		}
	}
	
	public static void notLessEqualZeroOrException(Integer valor) {
		notNullOrException(valor);
		if(valor.intValue() <= 0)
			throw new IllegalArgumentException("Objeto menor ou igual a zero.");
	}
	
	public static void notLessEqualZeroOrException(Double valor) {
		notNullOrException(valor);
		if(valor.doubleValue() <= 0)
			throw new IllegalArgumentException("Objeto menor ou igual a zero.");
	}
}
