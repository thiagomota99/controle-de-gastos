package br.com.thgyn.utils;


public class Objeto {
	
	public static void notNullOrException(Object ref) {
		if (isNull(ref)) {
			throw new NullPointerException("Objeto não pode ser nulo.");
		}
	}
	
	public static void nullOrException(Object ref) {
		if(isNotNull(ref)) {
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
	
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	public static boolean isNotNull(Object obj) {
		return obj != null;
	}
	
	public static boolean isEmpty(String obj) {
		return obj == null || obj.trim().isEmpty();
	}
}
