/* Software de Comunicaciones - Prácticas de la asignatura
 * DTE, ETSIST, UPM
 * Curso 2026/2027
 * Práctica 1, fase 2
 * Archivo: AdaptadorOperacionesCalculadoraGUI.java
 */

package cliente;

import com.google.protobuf.Empty;

import calculadoraGUI.ICalculadora;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import stubCalculadora.Calculadora.Operandos;
import stubCalculadora.Calculadora.PeticionSesion;
import stubCalculadora.Calculadora.RespuestaIdSesion;
import stubCalculadora.Calculadora.Resultado;
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
	
	private final ManagedChannel channel;
	private final ServicioCalculadoraBlockingStub stub;
	int sessionId;
	
	
	public AdaptadorOperacionesCalculadoraGUI() {
		this.channel = ManagedChannelBuilder
				.forAddress("localHost",50051 )
		        .usePlaintext()
		        .build();
		
		this.stub = ServicioCalculadoraGrpc.newBlockingStub(channel);
		this.obtenerSesionDelServidor();
	}
	
	private void obtenerSesionDelServidor() {
	try {
		Empty request = Empty.getDefaultInstance();
		RespuestaIdSesion result = stub.obtenerIdSesion(request);
		this.sessionId = result.getSessionId();
		System.out.println("Sesion estableceida correctamente. ID asignado: "+this.sessionId);
		
	}catch(io.grpc.StatusRuntimeException e) {
		io.grpc.Status status = e.getStatus();
        int codigo = status.getCode().value();
        String descripcion = e.getStatus().getDescription();
        
        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
	}
	}
	
	
	
	public double sumar(double operando1, double operando2) {
		
		Operandos.Builder requestBuilder = Operandos.newBuilder();
		requestBuilder.setOperando1(operando1);
		requestBuilder.setOperando2(operando2);
		requestBuilder.setSessionId(sessionId);
		Operandos request = requestBuilder.build();
		
		try {
			final Resultado result = stub.sumar(request);
			return result.getValor();
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
	}
	
	
	
	
	public double restar(double operando1, double operando2) {
	  
	  Operandos.Builder requestBuilder = Operandos.newBuilder();
		requestBuilder.setOperando1(operando1);
		requestBuilder.setOperando2(operando2);
		requestBuilder.setSessionId(sessionId);
		Operandos request = requestBuilder.build();
		
		try {
			final Resultado result = stub.restar(request);
			return result.getValor();
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
	}
	
	
	
	
	public double multiplicar(double operando1, double operando2) {
	
	  Operandos.Builder requestBuilder = Operandos.newBuilder();
		requestBuilder.setOperando1(operando1);
		requestBuilder.setOperando2(operando2);
		requestBuilder.setSessionId(sessionId);
		Operandos request = requestBuilder.build();
		
		try {
			final Resultado result = stub.multiplicar(request);
			return result.getValor();
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
	}
	
	
	
	public double dividir(double operando1, double operando2) {
		
	  Operandos.Builder requestBuilder = Operandos.newBuilder();
		requestBuilder.setOperando1(operando1);
		requestBuilder.setOperando2(operando2);
		requestBuilder.setSessionId(sessionId);
		Operandos request = requestBuilder.build();
	
		try {
			final Resultado result = stub.dividir(request);
			return result.getValor();
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
	}
	
	
	
	
	public double raizCuadrada(double operando) {
		
		Operandos.Builder requestBuilder = Operandos.newBuilder();
		requestBuilder.setOperando1(operando);
		requestBuilder.setOperando2(0.0);
		requestBuilder.setSessionId(sessionId);
		Operandos request = requestBuilder.build();
		
		try {
			final Resultado result = stub.raizCuadrada(request);
			return result.getValor();
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
	  
	}
	
	public double obtenerUltimoResultado() {
		PeticionSesion.Builder requestBuilder = PeticionSesion.newBuilder();
		requestBuilder.setSessionId(sessionId);
		PeticionSesion request = requestBuilder.build();
		
		
		
		
		try {
			final Resultado result = stub.obtenerUltimoResultado(request);
			return result.getValor();
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
		
	}
	
	public void memoriaLimpiar() {
		
		PeticionSesion.Builder requestBuilder = PeticionSesion.newBuilder();
		requestBuilder.setSessionId(sessionId);
		PeticionSesion request = requestBuilder.build();
		
		try {
			
			stub.memoriaLimpiar(request);
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
		
	}
	
	public void memoriaAniadir() {
		
		PeticionSesion.Builder requestBuilder = PeticionSesion.newBuilder();
		requestBuilder.setSessionId(sessionId);
		PeticionSesion request = requestBuilder.build();
		
		
		try {
		
			stub.memoriaAniadir(request);
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
	}
	
	public double memoriaObtener() {
		
		PeticionSesion.Builder requestBuilder = PeticionSesion.newBuilder();
		requestBuilder.setSessionId(sessionId);
		PeticionSesion request = requestBuilder.build();
		
		try {
			
			final Resultado result = stub.memoriaObtener(request);
			return result.getValor();
			
		}catch(io.grpc.StatusRuntimeException e) {
			io.grpc.Status status = e.getStatus();
	        int codigo = status.getCode().value();
	        String descripcion = e.getStatus().getDescription();
	        
	        System.err.println("Error gRPC con código = " + codigo + " y descripción: " + descripcion);
	        throw new RuntimeException("Fallo en el servidor gRPC: " + descripcion, e);
		}
	}
	
	
	}

	
	

	


