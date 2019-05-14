package Controlador;
import Modelo.BNodo;
// esta ser� la clase BTree en la que todos los m�todos se realizan en el �rbol 
// se encuentran. Esto llamar� m�todos en BNode.java y los m�todos en 
// esta clase se utilizar� para realizar operaciones en un objeto de �rbol. 


public class BTree{											// Aqu� hay variables disponibles para el �rbol.

	static int orden; 										// orden del arbol
	
	public BNodo root;  									// cada �rbol tiene al menos un nodo ra�z


	public BTree(int orden){								// Aqu� est� el constructor para el �rbol |
		this.orden = orden;
		root = new BNodo(orden, null);}
	
	// este ser� un m�todo para buscar un nodo dado donde 
	// queremos insertar un valor de clave. este m�todo se llama 
	// BNode Buscar donde devuelve un nodo con clave 

	public BNodo Buscar(BNodo root, int key){
		int i = 0; 											// siempre queremos comenzar a buscar el �ndice 0 de nodo.

		while(i < root.contar && key > root.key[i]){		// seguir aumentando
			i++;											// valor actual.
		}

		if(i <= root.contar && key == root.key[i]){  		// obviamente si la clave est� en el nodo
															// vamos a devolver el nodo.
			return root;
		}
		
		if(root.hoja){										// desde que ya hemos comprobado la ra�z
		    												// si es hoja no tenemos nada m�s que revisar
			return null ;
		}
		
		else{												// else si no se trata de dejarlo a trav�s de su hijo
			
			return Buscar(root.getHijo(i),key);
		}
	}

	
	//  Este ser� el m�todo de divisi�n. Se dividir� nodo nosotros 
	//  para insertar si est� lleno.
	
	public void split(BNodo x, int i, BNodo y){
		BNodo z = new BNodo(orden,null);					// nodo extra

		z.hoja = y.hoja;									// establece booleano en igual que y
		z.contar = orden - 1;								// este es el tama�o actualizado
		
		for(int j = 0; j < orden - 1; j++){
			z.key[j] = y.key[j+orden]; 						// copia el final de y en el frente de z
			
		}
		if(!y.hoja){										// si no, tenemos que reasignar nodos secundarios.
		
			for(int k = 0; k < orden; k++){
				z.hijo[k] = y.hijo[k+orden];				 // reafirmando hijo de y
			}
		}

		y.contar = orden - 1; 								// nuevo tama�o de y

		for(int j = x.contar ; j> i ; j--){					// si presionamos la tecla dentro de x tenemos
															// para reorganizar los nodos secundarios
			x.hijo[j+1] = x.hijo[j]; 						// cambia los hijos de x
		}
		
		x.hijo[i+1] = z;									 // reasignar i + 1 hijo de x

		for(int j = x.contar; j> i; j--){
				x.key[j + 1] = x.key[j]; 					// teclas de cambio
			}
		x.key[i] = y.key[orden-1];							// finalmente empujar el valor en la ra�z

		y.key[orden-1 ] = 0; 								// borrar valor donde empujamos desde

		for(int j = 0; j < orden - 1; j++){
			y.key[j + orden] = 0; 							// 'borrar' valores antiguos
		}
		x.contar++;   										// aumentar el conteo de claves en x
	}

	
	// este ser� un m�todo de inserci�n cuando el nodo no est� lleno. 

	public void Insertonocompleto(BNodo x, int key){
		int i = x.contar; 									// i es el n�mero de claves en el nodo x

		if(x.hoja){
			while(i >= 1 && key < x.key[i-1]){				// aqu� encuentra el lugar para poner la clave.
				x.key[i] = x.key[i-1];						// cambiar valores para hacer espacio
				i--;										// decremento
			}
			
			x.key[i] = key;									// finalmente asigna un valor al nodo
			x.contar ++; 									// Incrementar el recuento de claves en este nodo ahora.
		}


		else{
			
			int j = 0;
			while(j < x.contar  && key > x.key[j]){ 		// encontrar el lugar para repetir
															// en el ni�o correcto 		
				j++;
			}

			if(x.hijo[j].contar == orden*2 - 1){
				split(x,j,x.hijo[j]);						// divisi�n de llamadas en el nodo x del ni�o

				if(key > x.key[j]){
					j++;
				}
			}

			Insertonocompleto(x.hijo[j],key);				//recurse
		}
	}
	
	// este ser� el m�todo para insertar en general, llamar� 
	// inserte no lleno si es necesario. 
	
	public void Insertar(BTree t, int key){
		BNodo r = t.root;								//este m�todo encuentra que el nodo se inserta como
		 												//pasa por esto comenzando en el nodo ra�z.
		if(r.contar == 2*orden - 1){					//if es full
			BNodo s = new BNodo(orden,null);			//si est� lleno

			t.root = s;    
	    			       
			s.hoja = false;
														//esto es para inicializar el nodo.
			s.contar = 0;   
	    			       	
			s.hijo[0] = r;

			split(s,0,r);								//ra�z dividida

			Insertonocompleto(s, key); 					//m�todo de inserci�n de llamada
		}
		else
			Insertonocompleto(r,key);					//si no esta lleno solo ins�rtalo
	}

	
	// este ser� el m�todo para imprimir un nodo, o se repetir� cuando el nodo ra�z no sea hoja 
	
	public void Imprimir(BNodo n){
		
		for(int i = 0; i < n.contar; i++){
			
			System.out.print(n.getValue(i)+" " );		// esta parte imprime el nodo ra�z
		}

		if(!n.hoja){									// esto se llama cuando la ra�z no es hoja;
		

			for(int j = 0; j <= n.contar  ; j++){		// en este bucle, repetimos
							  							// para imprimir el �rbol en
				if(n.getHijo(j) != null){ 				// preorder
							  							// va desde la izquierda m�s
				System.out.println();	  				// ni�o a la derecha m�s
				Imprimir(n.getHijo(j));   			    // ni�o.
				}
			}
		}
	}

	
	// este ser� el m�todo para imprimir un nodo 
	
	
	public void BuscarImprimirNodo( BTree T,int x){
		BNodo temp= new BNodo(orden,null);

		temp= Buscar(T.root,x);

		if (temp==null){
			
		System.out.println("La clave no existe en este �rbol");
		}

		else{

		Imprimir(temp);
		}
	}


	// este m�todo eliminar� un valor clave del nodo de hoja 
	// Usaremos el m�todo de b�squeda para atravesar el 
	// �rbol para encontrar el nodo donde se encuentra la clave. Luego, 
	// iterado a trav�s de la matriz [] hasta que lleguemos al nodo y lo haremos 
	// asignar k [i] = k [i + 1] clave de sobrescritura que queremos eliminar y 
	// Manteniendo los puntos en blanco tambi�n. Tenga en cuenta que este es el m�s 
	// simple caso de borrar que hay.

       public void EliminarKey(BTree t, int key){
			       
		BNodo temp = new BNodo(orden,null);					//temp NodoB
			
		temp = Buscar(t.root,key);							// llamada del m�todo de b�squeda en el �rbol por clave

		if(temp.hoja && temp.contar > orden - 1){
			int i = 0;

			while( key > temp.getValue(i)){
				i++;
			}
			for(int j = i; j < 2*orden - 2; j++){
				
				temp.key[j] = temp.getValue(j+1);
			}
			temp.contar --;
		}
		else{
			System.out.println(" Este nodo no es una hoja o tiene menos de 1 teclas de orden");
		}
      }
}
