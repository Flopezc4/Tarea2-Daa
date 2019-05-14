/*  Esta es la clase principal para la implementaci�n de B-Tree en Java.
 * Crea un �rbol B-Tree con orden de usuario valor N.
 * Luego permite al usuario realizar las siguientes operaciones en el �rbol de BTree
 * 1.Agregar m�s claves al �rbol.
 * 2.Imprima todo el �rbol en preorden.
 * 3. Eliminar una clave de la hoja
 * 4.Busque una clave e imprima el nodo al que pertenece
 * El men� anterior se coloca en un bucle while para que el usuario tenga una interfaz f�cil de probar o familiarizarse con el B-Tree.
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
		System.out.print(" �Que t desea introducir al �rbol?  ");
		n=input.nextInt();
		
        while ( n<2){
            System.out.print(" Por favor, introduzca un n�mero entero mayor que 1: ");
			n=input.nextInt();														// El usuario ingresa el orden de �rbol y se asigna a N.
		}
		BTree tree = new BTree(n);													//   Se crea B-Tree Tree con orden N.

																					// Los valores iniciales se agregan al �rbol. El usuario e Ingresa cualquier n�mero de valores.
		System.out.print("\n �Cu�ntos valores desea ingresar?:  ");	
        n2 = input.nextInt();

        for ( int i=0;i< n2;i++){
            System.out.print("\nIngrese valor: ");
			System.out.println(i+1);
			temp=input.nextInt();
			tree.Insertar(tree,temp);
		}
		int choice,k;																// Variables utilizadas para controlar el bucle repetido del MEN�.

        boolean menu;
		menu=true;
			System.out.println("\tM\tE\tN\tU\n");
			System.out.println("1. Introduzca m�s valores en un �rbol");
			System.out.println("2. Imprima todo el �rbol en preorden");
			System.out.println("3. Busque una clave e imprima el nodo al que pertenece");
			System.out.println("4. Eliminar una clave de la hoja");
			System.out.println("5. Salir");

		while (menu){																// Este bucle While se ejecuta siempre que el usuario ingrese algo que no sea un 5.
		
			System.out.print("\nPor favor ingrese su elecci�n :");
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
			 																		// su funci�n es ingresar m�s valores en un �rbol.
                        System.out.print("�Cu�ntos valores desea ingresar ?:");
						n2=input.nextInt();

                        for ( int i=0;i< n2;i++){
                            System.out.print("\nIngrese valor: ");
							System.out.println(i+1);
							temp=input.nextInt();
							tree.Insertar(tree,temp);
                        }
                        break;

					case 2: 														// Si el usuario ingresa 2, este caso se ejecuta y
                        															// su funci�n es imprimir todo el �rbol en formato de pre-pedido.
                        tree.Imprimir(tree.root);
						System.out.println();
						break;

					case 3: 														// Si el usuario ingresa 3, este caso se ejecuta y
																					// Su funci�n es eliminar una clave de la hoja.
                        System.out.println("�Cu�l es la clave que desea buscar:");
						int key2=input.nextInt();
						tree.BuscarImprimirNodo(tree,key2);

						break;
					case 4:														 	// Si el Usuario ingresa en 4, este caso se ejecuta
																					// Su funci�n es buscar una clave e imprimir el nodo al que pertenece
                        System.out.println("Introduzca una clave para ser eliminada:");
						int key=input.nextInt();
						tree.EliminarKey(tree,key);
						System.out.println("Aqu� est� el �rbol impreso en preorden despu�s de eliminar");
						tree.Imprimir(tree.root);
						break;

					case 5: 														// Si el usuario ingresa 5, este caso se ejecuta y
                        															// su funci�n es salir
			
                        System.exit(0);
						break;

						default: 													// Si el Usuario ingresa una opci�n incorrecta, entonces este caso se ejecuta.
						System.out.println("\n Por favor ingrese una opci�n v�lida de 1,2,3 o 4\n");
						break;
			 	} 
			 } 
		} 
	} 
} 