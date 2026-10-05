/* Software de Comunicaciones - Prácticas de la asignatura
 * DTE, ETSIST, UPM
 * Curso 2026/2027
 * Práctica 1, fase 2
 * Archivo: AdaptadorOperacionesCalculadoraGUI.java
 */

package cliente;

import calculadoraGUI.ICalculadora;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import stubCalculadora.ServicioCalculadoraGrpc;
import stubCalculadora.ServicioCalculadoraGrpc.ServicioCalculadoraBlockingStub;

/**
 * Esta clase sirve para adaptar el stub cliente de gRPC generado a partir del fichero .proto de la calculadora
 * a la interfaz de {@link calculadoraGUI.ICalculadora}.
 * Se debe decidir qué tipo de adaptador (clase u objeto) es conveniente/posible.
 *
 * @see calculadoraGUI.ICalculadora
 */
class AdaptadorOperacionesCalculadoraGUI implements ICalculadora {
	
	public double sumar(double operando1, double operando2) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress("localHost",50051 );
		channelBuilder = channelBuilder.usePlaintext();
		ManagedChannel channel = channelBuilder().build();
		
		ServicioCalculadoraBlockingStub stub = ServicioCalculadoraGrpc.newBlockingStub(channel);
		
		Operandos.Builder builder = operandos.newBuilder();
		
		
		
	}

	private ManagedChannelBuilder channelBuilder() {
		// TODO Auto-generated method stub
		return null;
	}


}