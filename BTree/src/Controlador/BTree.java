package Controlador;
import Modelo.BNodo;
// esta será la clase BTree en la que todos los métodos se realizan en el árbol 
// se encuentran. Esto llamará métodos en BNode.java y los métodos en 
// esta clase se utilizará para realizar operaciones en un objeto de árbol. 


public class BTree{											// Aquí hay variables disponibles para el árbol.

	static int orden; 										// orden del arbol
	
	public BNodo root;  									// cada árbol tiene al menos un nodo raíz


	public BTree(int orden){								// Aquí está el constructor para el árbol |
		this.orden = orden;
		root = new BNodo(orden, null);}
	
	// este será un método para buscar un nodo dado donde 
	// queremos insertar un valor de clave. este método se llama 
	// BNode Buscar donde devuelve un nodo con clave 

	public BNodo Buscar(BNodo root, int key){
		int i = 0; 											// siempre queremos comenzar a buscar el índice 0 de nodo.

		while(i < root.contar && key > root.key[i]){		// seguir aumentando
			i++;											// valor actual.
		}

		if(i <= root.contar && key == root.key[i]){  		// obviamente si la clave está en el nodo
															// vamos a devolver el nodo.
			return root;
		}
		
		if(root.hoja){										// desde que ya hemos comprobado la raíz
		    												// si es hoja no tenemos nada más que revisar
			return null ;
		}
		
		else{												// else si no se trata de dejarlo a través de su hijo
			
			return Buscar(root.getHijo(i),key);
		}
	}

	
	//  Este será el método de división. Se dividirá nodo nosotros 
	//  para insertar si está lleno.
	
	public void split(BNodo x, int i, BNodo y){
		BNodo z = new BNodo(orden,null);					// nodo extra

		z.hoja = y.hoja;									// establece booleano en igual que y
		z.contar = orden - 1;								// este es el tamaño actualizado
		
		for(int j = 0; j < orden - 1; j++){
			z.key[j] = y.key[j+orden]; 						// copia el final de y en el frente de z
			
		}
		if(!y.hoja){										// si no, tenemos que reasignar nodos secundarios.
		
			for(int k = 0; k < orden; k++){
				z.hijo[k] = y.hijo[k+orden];				 // reafirmando hijo de y
			}
		}

		y.contar = orden - 1; 								// nuevo tamaño de y

		for(int j = x.contar ; j> i ; j--){					// si presionamos la tecla dentro de x tenemos
															// para reorganizar los nodos secundarios
			x.hijo[j+1] = x.hijo[j]; 						// cambia los hijos de x
		}
		
		x.hijo[i+1] = z;									 // reasignar i + 1 hijo de x

		for(int j = x.contar; j> i; j--){
				x.key[j + 1] = x.key[j]; 					// teclas de cambio
			}
		x.key[i] = y.key[orden-1];							// finalmente empujar el valor en la raíz

		y.key[orden-1 ] = 0; 								// borrar valor donde empujamos desde

		for(int j = 0; j < orden - 1; j++){
			y.key[j + orden] = 0; 							// 'borrar' valores antiguos
		}
		x.contar++;   										// aumentar el conteo de claves en x
	}

	
	// este será un método de inserción cuando el nodo no esté lleno. 

	public void Insertonocompleto(BNodo x, int key){
		int i = x.contar; 									// i es el número de claves en el nodo x

		if(x.hoja){
			while(i >= 1 && key < x.key[i-1]){				// aquí encuentra el lugar para poner la clave.
				x.key[i] = x.key[i-1];						// cambiar valores para hacer espacio
				i--;										// decremento
			}
			
			x.key[i] = key;									// finalmente asigna un valor al nodo
			x.contar ++; 									// Incrementar el recuento de claves en este nodo ahora.
		}


		else{
			
			int j = 0;
			while(j < x.contar  && key > x.key[j]){ 		// encontrar el lugar para repetir
															// en el niño correcto 		
				j++;
			}

			if(x.hijo[j].contar == orden*2 - 1){
				split(x,j,x.hijo[j]);						// división de llamadas en el nodo x del niño

				if(key > x.key[j]){
					j++;
				}
			}

			Insertonocompleto(x.hijo[j],key);				//recurse
		}
	}
	
	// este será el método para insertar en general, llamará 
	// inserte no lleno si es necesario. 
	
	public void Insertar(BTree t, int key){
		BNodo r = t.root;								//este método encuentra que el nodo se inserta como
		 												//pasa por esto comenzando en el nodo raíz.
		if(r.contar == 2*orden - 1){					//if es full
			BNodo s = new BNodo(orden,null);			//si está lleno

			t.root = s;    
	    			       
			s.hoja = false;
														//esto es para inicializar el nodo.
			s.contar = 0;   
	    			       	
			s.hijo[0] = r;

			split(s,0,r);								//raíz dividida

			Insertonocompleto(s, key); 					//método de inserción de llamada
		}
		else
			Insertonocompleto(r,key);					//si no esta lleno solo insértalo
	}

	
	// este será el método para imprimir un nodo, o se repetirá cuando el nodo raíz no sea hoja 
	
	public void Imprimir(BNodo n){
		
		for(int i = 0; i < n.contar; i++){
			
			System.out.print(n.getValue(i)+" " );		// esta parte imprime el nodo raíz
		}

		if(!n.hoja){									// esto se llama cuando la raíz no es hoja;
		

			for(int j = 0; j <= n.contar  ; j++){		// en este bucle, repetimos
							  							// para imprimir el árbol en
				if(n.getHijo(j) != null){ 				// preorder
							  							// va desde la izquierda más
				System.out.println();	  				// niño a la derecha más
				Imprimir(n.getHijo(j));   			    // niño.
				}
			}
		}
	}

	
	// este será el método para imprimir un nodo 
	
	
	public void BuscarImprimirNodo( BTree T,int x){
		BNodo temp= new BNodo(orden,null);

		temp= Buscar(T.root,x);

		if (temp==null){
			
		System.out.println("La clave no existe en este árbol");
		}

		else{

		Imprimir(temp);
		}
	}


	// este método eliminará un valor clave del nodo de hoja 
	// Usaremos el método de búsqueda para atravesar el 
	// árbol para encontrar el nodo donde se encuentra la clave. Luego, 
	// iterado a través de la matriz [] hasta que lleguemos al nodo y lo haremos 
	// asignar k [i] = k [i + 1] clave de sobrescritura que queremos eliminar y 
	// Manteniendo los puntos en blanco también. Tenga en cuenta que este es el más 
	// simple caso de borrar que hay.

       public void EliminarKey(BTree t, int key){
			       
		BNodo temp = new BNodo(orden,null);					//temp NodoB
			
		temp = Buscar(t.root,key);							// llamada del método de búsqueda en el árbol por clave

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
