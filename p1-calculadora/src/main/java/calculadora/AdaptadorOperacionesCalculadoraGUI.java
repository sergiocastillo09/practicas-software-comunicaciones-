/* Software de Comunicaciones - Prácticas de la asignatura
 * DTE, ETSIST, UPM
 * Curso 2026/2027
 * Práctica 1, fase 1
 * Archivo: AdaptadorOperacionesCalculadoraGUI.java
 */

package calculadora;

import calculadoraGUI.ICalculadora;

/**
 * Esta clase sirve para adaptar la interfaz de la clase {@link calculadora.OperacionesCalculadora}
 * a la interfaz de {@link calculadoraGUI.ICalculadora}.
 * Se puede utilizar un adaptador de clase o de objeto.
 *
 * @see calculadora.OperacionesCalculadora
 * @see calculadoraGUI.ICalculadora
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadora {
   
	private OperacionesCalculadora operaciones;
	
	public AdaptadorOperacionesCalculadoraGUI() {
		operaciones = new OperacionesCalculadora();
	}
	
	public double sumar(double operando1, double operando2) {
		return operaciones.implementacionSumar(operando1, operando2);
		
	}
	
    public double restar(double operando1, double operando2) {
    	return operaciones.implementacionRestar(operando1, operando2);
		
	}
    
    public double multiplicar(double operando1, double operando2) {
		return operaciones.implementacionMultiplicar(operando1, operando2);
	}
    
    public double dividir(double operando1, double operando2) {
		return operaciones.implementacionDividir(operando1, operando2);
	}
	
    public double obtenerUltimoResultado() {
    	return operaciones.implementacionUR();
    }
     
    public void memoriaLimpiar() {
    	operaciones.implementacionML();
    }
    
    public void memoriaAniadir() {
    	operaciones.implementacionMA();
    }
    
    public double memoriaObtener() {
    	return operaciones.implementacionMO();
    }
    
    public double raizCuadrada(double operando) {
    	return operaciones.implementacionRaizCuadrada(operando);
    }


}
