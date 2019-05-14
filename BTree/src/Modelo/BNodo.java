package Modelo;
    
	// esta es la implementación de la clase de nodo para el árbol equilibrado.

public class BNodo{

	static int t;  							// variable para determinar el orden del arbol
	public int contar; 						// número de claves en el nodo
	public int key[]; 						// matriz de valores clave
	public BNodo hijo[]; 					// matriz de referencias
	public boolean hoja; 					// es nodo una hoja o no
	BNodo padre;  							// padre del nodo actual.

	// este será el constructor por defecto para el nuevo nodo 
	public BNodo(){}
	
	// constructor de valor inicial para nuevo nodo 
	// se llamará desde BTree.java 

	public BNodo(int t, BNodo parent){

		this.t = t;  						// asignar tamaño
		this.padre = parent; 				// asignar padre
		key = new int[2*t - 1];  			// matriz de tamaño adecuado
		hijo = new BNodo[2*t]; 				// matriz de refs tamaño apropiado
		hoja = true;						// todos los nodos son hojas al principio;
		contar = 0; 						// Hasta que añadamos las claves más tarde.
	}

	
	// este es el método para devolver el valor clave en la posición del índice |
	public int getValue(int index){
	
		return key[index];
	}

	
	// este es el método para obtener el hijo del nodo 
	public BNodo getHijo(int index){
		return hijo[index];
	}


}
