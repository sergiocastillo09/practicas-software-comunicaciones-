/* Software de Comunicaciones - Prácticas de la asignatura

 * DTE, ETSIST, UPM
 * Curso 2026/2027
 * Práctica 1, fases 2 y 3
 * Archivo: ServidorCalculadora.java
 */

/* 
 * PREGUNTA 1: ¿Qué ocurre cuando el segundo cliente intenta solicitar operaciones remotas?
 * Respuesta: El servidor atiende la solicitud del segundo cliente de manera simultánea y concurrente 
 * con el primero, procesando la operación y devolviendo el resultado correcto a su interfaz gráfica 
 * sin que ninguna de las dos instancias de cliente sufra bloqueos o interferencias.
 *
 * PREGUNTA 2: ¿Por qué ocurre eso?
 * Respuesta: Esto ocurre gracias a la arquitectura multiproceso y multihilo del modelo de transporte 
 * subyacente de gRPC (basado en Netty), el cual gestiona un pool de hilos capaz de aceptar conexiones 
 * y despachar llamadas a métodos remotos de forma asíncrona e independiente para cada canal de cliente 
 * establecido.
 */

package servidor;

import java.io.IOException;
import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Clase principal para el servidor gRPC de la calculadora de la práctica 1.
 * <p>
 * Esta clase se encarga de inicializar y arrancar el servidor gRPC en el puerto especificado,
 * añadiendo el servicio de la calculadora. Además, gestiona el ciclo de vida del servidor,
 * incluyendo el apagado ordenado cuando la máquina virtual termina su ejecución.
 * @author DMC, DTE, Software de Comunicaciones 2026/2027
 * @version 2.0.0
 */
public class ServidorCalculadora {

	/** Este es el método inicial del servidor.
	 */
	 public static void main(String[] args) throws IOException, InterruptedException {
	       int port = 50051;
	       Server server = ServerBuilder.forPort(port) 
	               .addService(new AdaptadorOperacionesCalculadora())
	               .build()
	               .start();
	       System.out.println("Servidor arrancado, escucha en puerto TCP " + port);
	       Runtime.getRuntime().addShutdownHook(new Thread(() -> { 
	           System.out.println("Terminando el servidor gRPC porque " +
	        		   "la máquina virtual va a terminar su ejecución.");
	           if (server != null) {
	               server.shutdown();
	           }
	           System.err.print("Terminando el servidor...");
	       }));
	       server.awaitTermination(); 
           System.err.println("Terminado.");
	   }
}