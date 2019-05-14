/*  Esta es la clase principal para la implementación de B-Tree en Java.
 * Crea un árbol B-Tree con orden de usuario valor N.
 * Luego permite al usuario realizar las siguientes operaciones en el árbol de BTree
 * 1.Agregar más claves al árbol.
 * 2.Imprima todo el árbol en preorden.
 * 3. Eliminar una clave de la hoja
 * 4.Busque una clave e imprima el nodo al que pertenece
 * El menú anterior se coloca en un bucle while para que el usuario tenga una interfaz fácil de probar o familiarizarse con el B-Tree.
 */
package Vista;
import java.util.Scanner;
import Controlador.BTree;


public class BMain 
{

	public static void main(String[] args)
	{
        Scanner input = new Scanner( System.in );
		int n,n2,temp;
		System.out.print(" ¿Que t desea introducir al árbol?  ");
		n=input.nextInt();
		
        while ( n<2){
            System.out.print(" Por favor, introduzca un número entero mayor que 1: ");
			n=input.nextInt();														// El usuario ingresa el orden de Árbol y se asigna a N.
		}
		BTree tree = new BTree(n);													//   Se crea B-Tree Tree con orden N.

																					// Los valores iniciales se agregan al árbol. El usuario e Ingresa cualquier número de valores.
		System.out.print("\n ¿Cuántos valores desea ingresar?:  ");	
        n2 = input.nextInt();

        for ( int i=0;i< n2;i++){
            System.out.print("\nIngrese valor: ");
			System.out.println(i+1);
			temp=input.nextInt();
			tree.Insertar(tree,temp);
		}
		int choice,k;																// Variables utilizadas para controlar el bucle repetido del MENÚ.

        boolean menu;
		menu=true;
			System.out.println("\tM\tE\tN\tU\n");
			System.out.println("1. Introduzca más valores en un árbol");
			System.out.println("2. Imprima todo el árbol en preorden");
			System.out.println("3. Busque una clave e imprima el nodo al que pertenece");
			System.out.println("4. Eliminar una clave de la hoja");
			System.out.println("5. Salir");

		while (menu){																// Este bucle While se ejecuta siempre que el usuario ingrese algo que no sea un 5.
		
			System.out.print("\nPor favor ingrese su elección :");
			choice=input.nextInt();
			if ( choice == 5){		
                System.out.printf("El programa esta saliendo...,\n"
                                  +"Gracias por usar la implementacion de B-tree en Java\n");
			    System.exit(0);
				menu=false;
				break;
			}
            else{
			 	switch(choice)
			 	{
			 		case 1: 														// Si el Usuario ingresa en 1, este caso se ejecuta y
			 																		// su función es ingresar más valores en un árbol.
                        System.out.print("¿Cuántos valores desea ingresar ?:");
						n2=input.nextInt();

                        for ( int i=0;i< n2;i++){
                            System.out.print("\nIngrese valor: ");
							System.out.println(i+1);
							temp=input.nextInt();
							tree.Insertar(tree,temp);
                        }
                        break;

					case 2: 														// Si el usuario ingresa 2, este caso se ejecuta y
                        															// su función es imprimir todo el árbol en formato de pre-pedido.
                        tree.Imprimir(tree.root);
						System.out.println();
						break;

					case 3: 														// Si el usuario ingresa 3, este caso se ejecuta y
																					// Su función es eliminar una clave de la hoja.
                        System.out.println("¿Cuál es la clave que desea buscar:");
						int key2=input.nextInt();
						tree.BuscarImprimirNodo(tree,key2);

						break;
					case 4:														 	// Si el Usuario ingresa en 4, este caso se ejecuta
																					// Su función es buscar una clave e imprimir el nodo al que pertenece
                        System.out.println("Introduzca una clave para ser eliminada:");
						int key=input.nextInt();
						tree.EliminarKey(tree,key);
						System.out.println("Aquí está el árbol impreso en preorden después de eliminar");
						tree.Imprimir(tree.root);
						break;

					case 5: 														// Si el usuario ingresa 5, este caso se ejecuta y
                        															// su función es salir
			
                        System.exit(0);
						break;

						default: 													// Si el Usuario ingresa una opción incorrecta, entonces este caso se ejecuta.
						System.out.println("\n Por favor ingrese una opción válida de 1,2,3 o 4\n");
						break;
			 	} 
			 } 
		} 
	} 
} 