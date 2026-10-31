/* Software de Comunicaciones - Prácticas de la asignatura
 * DTE, ETSIST, UPM
 * Curso 2026/2027
 * Práctica 1, fase 2
 * Archivo: AdaptadorOperacionesCalculadora.java
 */

package servidor;

import io.grpc.stub.StreamObserver;
import stubCalculadora.Calculadora.Operandos;
import stubCalculadora.Calculadora.Resultado;
import stubCalculadora.ServicioCalculadoraGrpc.ServicioCalculadoraImplBase;

public class AdaptadorOperacionesCalculadora extends ServicioCalculadoraImplBase {
	
	public void sumar(Operandos request, StreamObserver<Resultado> respondeObserver) {
		
		final double operando1 = request.getOperando1();
		final double operando2 = request.getOperando2();
		
		final double resultado = operando1 + operando2;
		
		Resultado.Builder responseBuilder = Resultado.newBuilder();
		responseBuilder = responseBuilder.setValor(resultado);
		
		
		
		
	}


}
