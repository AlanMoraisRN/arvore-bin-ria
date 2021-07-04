/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore;

import java.util.Scanner;

/**
 *
 * @author Alan Morais
 */
public class Iniciar {
    
    Scanner entrada = new Scanner (System.in);
    
    public int menuIniciar(){
        int esc;
        Scanner entrada = new Scanner (System.in);
        System.out.println("------------------MENU INICIAR-------------------");
        System.out.println("ESCOLHA UMA OPÇÃO:");
        System.out.print("[1] - ÁRVORE BINÁRIA DE BUSCA:\n"
                + "[0] - SAIR: ");
        esc = entrada.nextInt();
        System.out.println("---------------------------------------------------");
        System.out.println();
        return esc;
    }
    
    public void metodosIniciar() throws Exception{
        
        switch (menuIniciar()){
            
            case 1:
                BinaryTree arvore = new BinaryTree();
                arvore.metodosArvore();
                break;
                
            default:
                break;
        }
    }
    
}
