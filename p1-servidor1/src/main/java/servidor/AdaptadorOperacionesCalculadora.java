/* Software de Comunicaciones - Prácticas de la asignatura
 * DTE, ETSIST, UPM
 * Curso 2026/2027
 * Práctica 1, fase 2
 * Archivo: AdaptadorOperacionesCalculadora.java
 */

package servidor;

import com.google.protobuf.Empty;

import calculadora.OperacionesCalculadora;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import stubCalculadora.Calculadora.Operandos;
import stubCalculadora.Calculadora.Resultado;
import stubCalculadora.ServicioCalculadoraGrpc.ServicioCalculadoraImplBase;

public class AdaptadorOperacionesCalculadora extends ServicioCalculadoraImplBase {
	
	
	private OperacionesCalculadora operaciones;
	
	public AdaptadorOperacionesCalculadora() {
		
		operaciones = new OperacionesCalculadora();
		
	}
	
	
	
	
	public void sumar(Operandos request, StreamObserver<Resultado> responseObserver) {
		
		try {
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
     
     
     
     public void memoriaAniadir(Empty request, StreamObserver<Empty> responseObserver) {
    	 
    	 try {
   			
   			operaciones.implementacionMA();
   			responseObserver.onNext(Empty.getDefaultInstance());
   			responseObserver.onCompleted();
   			
   		}catch(Exception e) {
   			final String mensaje = e.getMessage();
   			final Status status = Status.INTERNAL.withDescription(mensaje);
   			responseObserver.onError(status.asRuntimeException());
   			
   		}
     }
     
     
       public void memoriaLimpiar(Empty request, StreamObserver<Empty> responseObserver) {
    	 
    	 try {
   			
   			operaciones.implementacionML();
   			responseObserver.onNext(Empty.getDefaultInstance());
   			responseObserver.onCompleted();
   			
   		}catch(Exception e) {
   			final String mensaje = e.getMessage();
   			final Status status = Status.INTERNAL.withDescription(mensaje);
   			responseObserver.onError(status.asRuntimeException());
   			
   		}
     }
       
       public void memoriaObtener(Empty request, StreamObserver<Resultado> responseObserver) {
      	 
      	 try {
     			
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
       
       
      
       
       
       
       public void obtenerUltimoResultado(Empty request, StreamObserver<Resultado> responseObserver) {
    	   
    	   try {
    			
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


     
     
     


	
	
	
	
	
	



