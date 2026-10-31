/* Software de Comunicaciones - Prácticas de la asignatura
 * DTE, ETSIST, UPM
 * Curso 2026/2027
 * Práctica 1, fase 2
 * Archivo: AdaptadorOperacionesCalculadora.java
 */

/*¿Qué observa si en un cliente realiza una operación y en el otro solicita visualizar el último resultado?
¿Por qué ocurre eso?Lo que se observa: Al realizar una operación en un cliente (por ejemplo, una suma) y 
posteriormente solicitar visualizar el último resultado o el estado de la memoria en un segundo cliente diferente,
este último no ve afectado su estado ni recibe el resultado del primer cliente; cada cliente opera de forma completamente 
independiente y aislada sobre su propia instancia de cálculo.   
Por qué ocurre: Esto sucede gracias al uso del patrón DAO y al almacenamiento basado en un ConcurrentMap<Integer, OperacionesCalculadora>. 
Al arrancar cada cliente, este obtiene un identificador de sesión exclusivo (sessionId) mediante el método obtenerIdSesion. 
Cuando se ejecuta cualquier procedimiento remoto, la petición incluye dicho sessionId, lo que permite al adaptador del servidor buscar 
y recuperar de manera unívoca únicamente la instancia de OperacionesCalculadora asociada a ese cliente concreto, evitando interferencias o 
condiciones de compartición de estado entre múltiples usuarios simultáneos. */

package servidor;

import com.google.protobuf.Empty;

import calculadora.OperacionesCalculadora;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import persistencia.CalculadoraDAO_Mapa;
import persistencia.ICalculadoraDAO;
import stubCalculadora.Calculadora.Operandos;
import stubCalculadora.Calculadora.PeticionSesion;
import stubCalculadora.Calculadora.Resultado;
import stubCalculadora.Calculadora.RespuestaIdSesion;
import stubCalculadora.ServicioCalculadoraGrpc.ServicioCalculadoraImplBase;

public class AdaptadorOperacionesCalculadora extends ServicioCalculadoraImplBase {
	
	
	private final ICalculadoraDAO calculadoraDAO;
	
	
	public AdaptadorOperacionesCalculadora() {
		
		this.calculadoraDAO = new CalculadoraDAO_Mapa();
		
	}
	
	public void obtenerIdSesion(Empty request, StreamObserver<RespuestaIdSesion> responseObserver) {
		
		try {
			OperacionesCalculadora operaciones = new OperacionesCalculadora();
			int sessionId = calculadoraDAO.create(operaciones);
			
			RespuestaIdSesion responsebuilder = RespuestaIdSesion.newBuilder()
					.setSessionId(sessionId)
					.build();
			
			responseObserver.onNext(responsebuilder);
			responseObserver.onCompleted();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			final String mensaje = e.getMessage();
			final Status status = Status.INTERNAL.withDescription("Error al crear sesión: " + mensaje);
			responseObserver.onError(status.asRuntimeException());
		}
		
		
	}
	
	
	
	
	
	
	public void sumar(Operandos request, StreamObserver<Resultado> responseObserver) {
		
		try {
			
			int sessionId = request.getSessionId();
			OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
			
			final double operando1 = request.getOperando1();
			final double operando2 = request.getOperando2();
			final double resultado = operaciones.implementacionSumar(operando1, operando2);
			
			
			Resultado.Builder responseBuilder = Resultado.newBuilder();
			responseBuilder = responseBuilder.setValor(resultado);
			final Resultado resultadoADevolver = responseBuilder.build();
			
			responseObserver.onNext(resultadoADevolver);
			responseObserver.onCompleted();
			
		}catch(Exception e) {
			final String mensaje = e.getMessage();
			final Status status = Status.INTERNAL.withDescription(mensaje);
			responseObserver.onError(status.asRuntimeException());
			
		}
		
	}
	
	
     public void restar(Operandos request, StreamObserver<Resultado> responseObserver) {
		
		try {
			
			int sessionId = request.getSessionId();
			OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
			
			final double operando1 = request.getOperando1();
			final double operando2 = request.getOperando2();
			
			final double resultado = operaciones.implementacionRestar(operando1, operando2);
			
			
			Resultado.Builder responseBuilder = Resultado.newBuilder();
			responseBuilder = responseBuilder.setValor(resultado);
			final Resultado resultadoADevolver = responseBuilder.build();
			
			responseObserver.onNext(resultadoADevolver);
			responseObserver.onCompleted();
			
		}catch(Exception e) {
			final String mensaje = e.getMessage();
			final Status status = Status.INTERNAL.withDescription(mensaje);
			responseObserver.onError(status.asRuntimeException());
			
		}	
	}
     
     
     public void multiplicar(Operandos request, StreamObserver<Resultado> responseObserver) {
 		
 		try {
 			
 			int sessionId = request.getSessionId();
			OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
			
 			final double operando1 = request.getOperando1();
 			final double operando2 = request.getOperando2();
 			
 			final double resultado = operaciones.implementacionMultiplicar(operando1, operando2);
 			
 			Resultado.Builder responseBuilder = Resultado.newBuilder();
 			responseBuilder = responseBuilder.setValor(resultado);
 			final Resultado resultadoADevolver = responseBuilder.build();
 			
 			responseObserver.onNext(resultadoADevolver);
 			responseObserver.onCompleted();
 			
 		}catch(Exception e) {
 			final String mensaje = e.getMessage();
 			final Status status = Status.INTERNAL.withDescription(mensaje);
 			responseObserver.onError(status.asRuntimeException());
 			
 		}
 		
 	}
     
     
     public void dividir(Operandos request, StreamObserver<Resultado> responseObserver) {
  		
  		try {
  			
  			int sessionId = request.getSessionId();
			OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
  			
  			
  			final double operando1 = request.getOperando1();
  			final double operando2 = request.getOperando2();
  			
  			final double resultado = operaciones.implementacionDividir(operando1, operando2);
  			
  			Resultado.Builder responseBuilder = Resultado.newBuilder();
  			responseBuilder = responseBuilder.setValor(resultado);
  			final Resultado resultadoADevolver = responseBuilder.build();
  			
  			responseObserver.onNext(resultadoADevolver);
  			responseObserver.onCompleted();
  			
  		}catch(Exception e) {
  			final String mensaje = e.getMessage();
  			final Status status = Status.INTERNAL.withDescription(mensaje);
  			responseObserver.onError(status.asRuntimeException());
  			
  		}
  		
  	}
     
     

    public void raizCuadrada(Operandos request, StreamObserver<Resultado> responseObserver) {
   		
   		try {
   			
   			int sessionId = request.getSessionId();
			OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
   			final double operando1 = request.getOperando1();
   			
   			
   			final double resultado = operaciones.implementacionRaizCuadrada(operando1);
   			
   			Resultado.Builder responseBuilder = Resultado.newBuilder();
   			responseBuilder = responseBuilder.setValor(resultado);
   			final Resultado resultadoADevolver = responseBuilder.build();
   			
   			responseObserver.onNext(resultadoADevolver);
   			responseObserver.onCompleted();
   			
   		}catch(Exception e) {
   			final String mensaje = e.getMessage();
   			final Status status = Status.INTERNAL.withDescription(mensaje);
   			responseObserver.onError(status.asRuntimeException());
   			
   		}
   		
   	}
     
     
     
     public void memoriaAniadir(PeticionSesion request, StreamObserver<Empty> responseObserver) {
    	 
    	 try {
    		int sessionId = request.getSessionId();
    		OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
   			operaciones.implementacionMA();
   			responseObserver.onNext(Empty.getDefaultInstance());
   			responseObserver.onCompleted();
   			
   		}catch(Exception e) {
   			final String mensaje = e.getMessage();
   			final Status status = Status.INTERNAL.withDescription(mensaje);
   			responseObserver.onError(status.asRuntimeException());
   			
   		}
     }
     
     
       public void memoriaLimpiar(PeticionSesion request, StreamObserver<Empty> responseObserver) {
    	 
    	 try {
    			int sessionId = request.getSessionId();
    			OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
   			operaciones.implementacionML();
   			responseObserver.onNext(Empty.getDefaultInstance());
   			responseObserver.onCompleted();
   			
   		}catch(Exception e) {
   			final String mensaje = e.getMessage();
   			final Status status = Status.INTERNAL.withDescription(mensaje);
   			responseObserver.onError(status.asRuntimeException());
   			
   		}
     }
       
       public void memoriaObtener(PeticionSesion request, StreamObserver<Resultado> responseObserver) {
      	 
      	 try {
      		    int sessionId = request.getSessionId();
			    OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
     			final double operando = operaciones.implementacionMO();
     			Resultado.Builder responseBuilder = Resultado.newBuilder();
      			responseBuilder = responseBuilder.setValor(operando);
      			final Resultado resultadoADevolver = responseBuilder.build();
      			
     			responseObserver.onNext(resultadoADevolver);
     			responseObserver.onCompleted();
     			
     		}catch(Exception e) {
     			final String mensaje = e.getMessage();
     			final Status status = Status.INTERNAL.withDescription(mensaje);
     			responseObserver.onError(status.asRuntimeException());
     			
     		}
       }
       
       
      
       
       
       
       public void obtenerUltimoResultado(PeticionSesion request, StreamObserver<Resultado> responseObserver) {
    	   
    	   try {
    		   int sessionId = request.getSessionId();
			    OperacionesCalculadora operaciones = calculadoraDAO.find(sessionId);
    			final double operando = operaciones.implementacionUR();
    			Resultado.Builder responseBuilder = Resultado.newBuilder();
     			responseBuilder = responseBuilder.setValor(operando);
     			final Resultado resultadoADevolver = responseBuilder.build();
     			
    			responseObserver.onNext(resultadoADevolver);
    			responseObserver.onCompleted();
    			
    		}catch(Exception e) {
    			final String mensaje = e.getMessage();
    			final Status status = Status.INTERNAL.withDescription(mensaje);
    			responseObserver.onError(status.asRuntimeException());
    			
    		}
      }
       
       
       }


     
     
     


	
	
	
	
	
	



