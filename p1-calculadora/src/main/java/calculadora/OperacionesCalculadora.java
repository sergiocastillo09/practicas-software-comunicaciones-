/* Software de Comunicaciones - Prácticas de la asignatura
 * DTE, ETSIST, UPM
 * Curso 2026/2027
 * Práctica 1
 * Archivo: OperacionesCalculadora.java
 */

package calculadora;

/** Esta clase contiene la implementación final de cada operación disponible en la calculadora.
 * Debe contener un método público de instancia por cada método de la interfaz {@link CalculadoraGUI.ICalculadora}.
 * Para evitar confusiones, se recomienda que cada uno de los métodos citados anteponga 'implementacion' al
 * nombre del método de {@link CalculadoraGUI.ICalculadora}. Por ejemplo: si se desea crear el método que implementa
 * la suma, su nombre en esta clase será 'implementacionSumar'.
 */
public class OperacionesCalculadora {

	// Realizar aquí la implementación de los métodos correspondientes a cada
	// operación de la calculadora, siguiendo las indicaciones del enunciado.
	private double memoria ;
    private double ultimoResultado;
	
	
	public OperacionesCalculadora() {
		 memoria = 0;
		 ultimoResultado = 0;
		
	}
	
	public double implementacionSumar(double operando1, double operando2) {
		return ultimoResultado = operando1 + operando2;
		
	}
	
    public double implementacionRestar(double operando1, double operando2) {
    	return ultimoResultado = operando1-operando2;
		
	}
     
    public double implementacionMultiplicar(double operando1, double operando2) {
    	return ultimoResultado = operando1*operando2;
    }
    
    public double implementacionDividir(double operando1, double operando2) {
    	return ultimoResultado = operando1/operando2;
    }
    
    public double  implementacionUR() {
    	return ultimoResultado;
    }
    
    public void implementacionML() {
    	memoria = 0;
    }
    
    public void implementacionMA() {
    	memoria = memoria + ultimoResultado;
    }
    
    public double  implementacionMO() {
    	return memoria;
    }
    
   public double implementacionRaizCuadrada(double operando){
    	return ultimoResultado = Math.sqrt(operando);
    }

}
