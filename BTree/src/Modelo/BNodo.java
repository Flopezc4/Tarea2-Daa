package Modelo;
    
	// esta es la implementaci�n de la clase de nodo para el �rbol equilibrado.

public class BNodo{

	static int t;  							// variable para determinar el orden del arbol
	public int contar; 						// n�mero de claves en el nodo
	public int key[]; 						// matriz de valores clave
	public BNodo hijo[]; 					// matriz de referencias
	public boolean hoja; 					// es nodo una hoja o no
	BNodo padre;  							// padre del nodo actual.

	// este ser� el constructor por defecto para el nuevo nodo 
	public BNodo(){}
	
	// constructor de valor inicial para nuevo nodo 
	// se llamar� desde BTree.java 

	public BNodo(int t, BNodo parent){

		this.t = t;  						// asignar tama�o
		this.padre = parent; 				// asignar padre
		key = new int[2*t - 1];  			// matriz de tama�o adecuado
		hijo = new BNodo[2*t]; 				// matriz de refs tama�o apropiado
		hoja = true;						// todos los nodos son hojas al principio;
		contar = 0; 						// Hasta que a�adamos las claves m�s tarde.
	}

	
	// este es el m�todo para devolver el valor clave en la posici�n del �ndice |
	public int getValue(int index){
	
		return key[index];
	}

	
	// este es el m�todo para obtener el hijo del nodo 
	public BNodo getHijo(int index){
		return hijo[index];
	}


}
