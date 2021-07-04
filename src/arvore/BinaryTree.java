/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Alan Morais
 */
public class BinaryTree {
    
    private Node root;
    private Node root2;

    public boolean isEmpty(){
        if(root == null){
            System.out.println("ÁRVORE VAZIA!");
            return true;
        }
        System.out.println("ÁRVORE NÃO VAZIA!");
        return false;
    }
    
    public int getAltura(){
        return getAltura(this.root);
    }
    
    private int getAltura(Node root){
        if(root == null){
            return 0;
        }
        int altEsq = getAltura(root.getNoEsquerda());
        int altDir = getAltura(root.getNoDireita());
        if(altEsq > altDir){
            return altEsq + 1;
        } else {
            return altDir + 1;
        }
    }
    
    public int getQtdNode(){
        return getQtdNode(root);
    }
    
    private int getQtdNode(Node root){
        if(root != null){
            int qtdNodeEsq = getQtdNode(root.getNoEsquerda());
            int qtdNodeDireita = getQtdNode(root.getNoDireita());
            return qtdNodeEsq + qtdNodeDireita + 1;
        }else{
            return 0;
        }
    }
    
    public void imprimirArvore(){
        if(this.root == null)
            System.out.println("Árvore vazia");
        else
            imprimirArvore(this.root);
    }
    
    private void imprimirArvore(Node node){
        if(node.getNoEsquerda() != null){
            imprimirArvore(node.getNoEsquerda());
        }
        if (node.getNoDireita() != null){
            imprimirArvore(node.getNoDireita());
        }
        System.out.println("Nó: " + node.getValor());
    }
    
    public void imprimirArvorePordem(){
        if(this.root == null)
            System.out.println("Árvore vazia");
        else
            imprimirArvorePordem(this.root);
    }
    
    private void imprimirArvorePordem(Node node){
        
        System.out.println("Nó: " + node.getValor());
        
        if(node.getNoEsquerda() != null){
            imprimirArvorePordem(node.getNoEsquerda());
        }
        
        
        if (node.getNoDireita() != null){
            imprimirArvorePordem(node.getNoDireita());
        }
    }
    
    public void imprimirArvoreEordem(){
        if(this.root == null)
            System.out.println("Árvore vazia");
        else
            imprimirArvoreEordem(this.root);
    }
    
    private void imprimirArvoreEordem(Node node){
        if(node.getNoEsquerda() != null){
            imprimirArvoreEordem(node.getNoEsquerda());
        }        
        
        System.out.println("Nó: " + node.getValor());
        
        if (node.getNoDireita() != null){
            imprimirArvoreEordem(node.getNoDireita());
        }
    }
    
    
    public void inserir(int valor){
        inserir(this.root, valor);
    }
    
    public void inserir(Node node, int valor) {
        if(this.root == null){
            this.root = new Node(valor);
        } else {
            if (valor < node.getValor()) {
                if (node.getNoEsquerda() != null) { 
                    inserir(node.getNoEsquerda(), valor); 
                } else { 
                    //Se nodo esquerdo vazio insere o novo no aqui 
                    System.out.println("INSERINDO O VALOR " + valor + " A ESQUERDA DE " + node.getValor());
                    node.setNoEsquerda(new Node(valor)); 
                } 
                //Verifica se o valor a ser inserido é maior que o no corrente da árvore, se sim vai para subarvore direita 
            } else if (valor >= node.getValor()) { 
                //Se tiver elemento no no direito continua a busca 
                if (node.getNoDireita() != null) { 
                    inserir(node.getNoDireita(), valor); 
                } else {
                    //Se nodo direito vazio insere o novo no aqui
                    System.out.println("INSERINDO O VALOR " + valor + " A DIREITA DE " + node.getValor());
                    node.setNoDireita(new Node(valor)); 
                } 
            }
        }
    }
    
    public Node remover(int valor) throws Exception{
        return remover(this.root, valor);
    }
    
    private Node remover(Node node, int valor) throws Exception{
        if(this.root == null){
            throw new Exception("ÁRVORE VAZIA!");
        } else {            
            if(valor < node.getValor()){
                node.setNoEsquerda(remover(node.getNoEsquerda(), valor));
            } else if(valor > node.getValor()){
                node.setNoDireita(remover(node.getNoDireita(), valor));
            } else if (node.getNoEsquerda() != null && node.getNoDireita() != null) {
                /*2 filhos*/  
                System.out.println("  REMOVEU NÓ " + node.getValor());
                node.setValor(encontraMinimo(node.getNoDireita()).getValor());
                node.setNoDireita(removeMinimo(node.getNoDireita()));
            } else {  
                System.out.println("  REMOVEU NÓ " + node.getValor());  
                node = (node.getNoEsquerda() != null) ? node.getNoEsquerda() : node.getNoDireita();  
            }  
            return node;
        }
    }
    
    private Node removeMinimo(Node node) {  
        if (node == null) {  
            System.out.println("  ERRO ");  
        } else if (node.getNoEsquerda() != null) {  
            node.setNoEsquerda(removeMinimo(node.getNoEsquerda()));  
            return node;  
        } else {  
            return node.getNoDireita();  
        }  
        return null;  
    }  
  
    private Node encontraMinimo(Node node) {  
        if (node != null) {  
            while (node.getNoEsquerda() != null) {  
                node = node.getNoEsquerda();  
            }  
        }  
        System.out.println(node);
        return node;  
    }
    
    private Node encontraMaximo(Node node) {  
        if (node != null) {  
            while (node.getNoDireita() != null) {  
                node = node.getNoDireita();  
            }  
        }  
        System.out.println(node);
        return node;
    }
    
    public boolean Pesquisa (int valor){
        Node aux = this.root;
        while (aux != null){
            if(valor == aux.getValor()){
                System.out.println("VALOR ENCONTRADO "+aux);
                return true;
            }else if (valor < aux.getValor())
                aux = aux.getNoEsquerda();
            else
                aux = aux.getNoDireita();
        }
        System.out.println("VALOR NÃO ENCONTRADO!");
        return false;
    }
    
    public int SomaElementos(Node node){
        if (node != null){
            return (node.getValor()) + SomaElementos(node.getNoDireita()) + SomaElementos(node.getNoEsquerda());
        }else
        return 0;
    }
    
    public int MediaElementos(){
        /*int media, soma, num;
        soma = SomaElementos(root);
        num = getQtdNode(root);
        media = soma / num;
        return media;*/
        return SomaElementos(root)/getQtdNode(root);
    }
    
    public void RemoverArvore(Node node){
        this.root = null;
        System.out.println("ÁRVORE EXCLUÍDA!");
    }
    
    public int calculaNivel(int valor){
        Node aux = root;
        int nivel = 1;
        while(aux != null && aux.getValor() != valor){
            if(valor < aux.getValor()){
                aux = aux.getNoEsquerda();
            }else{
                aux = aux.getNoDireita();
            }
            nivel++;
        }
        return nivel;
    }
    
    public int calculaGrau(Node node, int valor){
        int grau = -1;
        if (node != null) {
            if (node.getValor() == valor) {
                if (node.getNoEsquerda() == null && node.getNoDireita() == null) {
                    return 0;
                } else if (node.getNoEsquerda() != null && node.getNoDireita() != null) {
                    return 2;
                } else {
                    return 1;
                }
            }
            int valorDireita = calculaGrau(node.getNoDireita(), valor);
            int valorEsquerda = calculaGrau(node.getNoEsquerda(), valor);
            if(valorDireita != -1){
                return valorDireita;
            } else if (valorEsquerda != -1){
                return valorEsquerda;
            }
        }
        return grau;
    }
    
    public void ExibeGrau(Node node){
        if (isEmpty()){
            System.out.println();
        }else{
            if (calculaGrau(root,0) != -1){
                System.out.println("GRAU = " + calculaGrau(root,0));
            }else{
                System.out.println("ELEMENTO NÃO ENCONTRADO!");
            }
        }
    }
    
    public void imprimir(Node node, int nivel){
        if (node != null) {
            imprimir(node.getNoDireita(), nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("      ");
            }
            System.out.printf("%6d\n\n", node.getValor());
            imprimir(node.getNoEsquerda(), nivel + 1);
        }
    }
    
    public int somarPares(Node node){
        int soma = 0;
        if (node != null){
            if(node.getValor()%2==0){
                soma = node.getValor();
            }
            return (soma) + somarPares(node.getNoDireita()) + somarPares(node.getNoEsquerda());   
        } else {
            return 0;
        }
    }
    
    public int somarImpares(Node node){
        int soma = 0;
        if (node != null){
            if(node.getValor()%2==1){
                soma = node.getValor();
            }
            return (soma) + somarImpares(node.getNoDireita()) + somarImpares(node.getNoEsquerda());   
        } else {
            return 0;
        }
    }
    
    public void Aleatorio (Node node){
        Random gerar = new Random ();
        for (int i = 0; i < 10; i++){
            inserir(gerar.nextInt(50));
        }
        imprimir(root,0);
    }
    
    public int QuantFilhos (Node node){
        if (node != null){    
            if (calculaGrau(node,node.getValor()) == 2){
                return 1 + QuantFilhos(node.getNoEsquerda()) + QuantFilhos(node.getNoDireita());
            }else{
                return QuantFilhos(node.getNoDireita()) + QuantFilhos(node.getNoEsquerda());
            }
        }
        return 0;
    }
    
    public int GrauArvore (Node node){
        Node aux = node;
        while (aux != null){
            if (aux.getNoEsquerda() == null && aux.getNoDireita() == null){
                return 0;
            }else if (aux.getNoDireita() != null && aux.getNoEsquerda() != null){
                return 2;
            }else{
                return 1;
            }
        }
        if (GrauArvore(node) != -1){
            return GrauArvore(aux.getNoDireita());
        }else{
            GrauArvore(aux.getNoEsquerda());
        }
        return 0;
    }
    
    public void ExibeGrauArvore(Node node){
        if (isEmpty()){
            System.out.println();
        }else{
            if (GrauArvore(root) != -1){
                System.out.println("GRAU = " + GrauArvore(node));
            }else{
                System.out.println("ELEMENTO NÃO ENCONTRADO!");
            }
        }
    }
    
    public int ImprimeFolhas(Node node){
        if (node != null){
            if (node.getNoDireita() == null && node.getNoEsquerda() == null){
                System.out.println(node.getValor() + " ");
                return ImprimeFolhas(node.getNoDireita()) + ImprimeFolhas(node.getNoEsquerda());
            }
            else{
                return ImprimeFolhas(node.getNoDireita()) + ImprimeFolhas(node.getNoEsquerda());
            }
        }
        return 0;
    }
    
    public void UmFilho (Node node){
        if (node != null){
            if (calculaGrau(root, node.getValor()) == 1){
                System.out.println(node.getValor()+ " ");
                UmFilho(node.getNoDireita());
                UmFilho(node.getNoEsquerda());
            }else if (calculaGrau(root, node.getValor()) != 1){
                UmFilho(node.getNoDireita());
                UmFilho(node.getNoEsquerda());
            }
        }
    }
    
    public void DoisFilhos(Node node){
        if (node != null){
            if (calculaGrau(root, node.getValor()) == 2){
                System.out.println(node.getValor()+" ");
                DoisFilhos(node.getNoDireita());
                DoisFilhos(node.getNoEsquerda());
            }else if (calculaGrau(root, node.getValor()) != 2){
                DoisFilhos(node.getNoDireita());
                DoisFilhos(node.getNoEsquerda());
            }
        }
    }
    
    public void PesquisaPai (Node node, int valor){
        if (node != null){
            if (valor >= node.getValor()){
                if (node.getNoDireita() != null && node.getNoDireita().getValor() == valor){
                    System.out.println(node.getValor()+" PAI DE "+node.getNoDireita().getValor());
                    PesquisaPai(node.getNoDireita(),valor);
                    
                }else{
                    PesquisaPai(node.getNoDireita(),valor);
                }
            }else{
                if (node.getNoEsquerda() != null && node.getNoEsquerda().getValor() == valor){
                    System.out.println(node.getValor()+" PAI DE "+node.getNoEsquerda().getValor());
                    PesquisaPai(node.getNoEsquerda(),valor);
                }else{
                    PesquisaPai(node.getNoEsquerda(),valor);
                }
            }
        }
    }
    
    public boolean comparar (Node node1, Node node2){
        if ((node1 == null) != (node2 == null)){
            return false;
        }else if (node1 == node2){
            return true;
        }else if (node1.getValor() != node2.getValor()){
            return false;
        }else if (!comparar(node1.getNoEsquerda(), node2.getNoEsquerda())){
            return false;
        }else if (!comparar(node1.getNoDireita(), node2.getNoDireita())){
            return false;
        }
        return true;
    }
    
    public void inserirArvore2(Node node2, int valor){
        if(this.root2 == null){
            this.root2 = new Node(valor);
        } else {
            if (valor < node2.getValor()) {
                if (node2.getNoEsquerda() != null) { 
                    inserirArvore2(node2.getNoEsquerda(), valor); 
                } else { 
                    //Se nodo esquerdo vazio insere o novo no aqui 
                    System.out.println("INSERINDO O VALOR " + valor + " A ESQUERDA DE " + node2.getValor());
                    node2.setNoEsquerda(new Node(valor)); 
                } 
                //Verifica se o valor a ser inserido é maior que o no corrente da árvore, se sim vai para subarvore direita 
            } else if (valor >= node2.getValor()) { 
                //Se tiver elemento no no direito continua a busca 
                if (node2.getNoDireita() != null) { 
                    inserirArvore2(node2.getNoDireita(), valor); 
                } else {
                    //Se nodo direito vazio insere o novo no aqui
                    System.out.println("INSERINDO O VALOR " + valor + " A DIREITA DE " + node2.getValor());
                    node2.setNoDireita(new Node(valor)); 
                } 
            }
        }
    }
    
    public void imprimirArvore2 (Node node2, int nivel2){
        if (node2 != null) {
            imprimirArvore2(node2.getNoDireita(), nivel2 + 1);
            for (int i = 0; i < nivel2; i++) {
                System.out.print("      ");
            }
            System.out.printf("%6d\n\n", node2.getValor());
            imprimirArvore2(node2.getNoEsquerda(), nivel2 + 1);
        }
    }
    
    public boolean Pesquisa2 (int valor){
        Node aux = this.root2;
        while (aux != null){
            if(valor == aux.getValor()){
                System.out.println("VALOR ENCONTRADO "+aux);
                return true;
            }else if (valor < aux.getValor())
                aux = aux.getNoEsquerda();
            else
                aux = aux.getNoDireita();
        }
        System.out.println("VALOR NÃO ENCONTRADO!");
        return false;
    }
    
    public boolean Verifica(int d){
        if (Pesquisa(d) == true){
            if(Pesquisa2(d)== true){
                return true;
            }
        }else if (Pesquisa(d)!= true){
            if(Pesquisa2(d) == true){
                return false;
            }
        }
        return false;
    }
    
    Scanner entrada = new Scanner (System.in);
    
    public int menuArvore(){
        System.out.println("------------------MENU DE OPÇÕES-------------------");
        int op;
        System.out.print("DIGITE UMA OPÇÃO:\n"
                + "[1] - INSERIR:\n"
                + "[2] - REMOVER:\n"
                + "[3] - CALCULAR NÍVEL DO NÓ:\n"
                + "[4] - MAIOR ELEMENTO:\n"
                + "[5] - MENOR ELEMENTO:\n"
                + "[6] - IMPRIMIR PRÉ ORDEM:\n"
                + "[7] - IMPRIMIR EM ORDEM:\n"
                + "[8] - IMPRIMIR PÓS ORDEM:\n"
                + "[9] - ALTURA DA ÁRVORE:\n"
                + "[10] - QUANTIDADE DE ELEMENTOS:\n"
                + "[11] - VERIFICAR SE ESTÁ VAZIA:\n"
                + "[12] - PESQUISAR ELEMENTO:\n"
                + "[13] - SOMAR ELEMENTOS:\n"
                + "[14] - MÉDIA DOS ELEMENTOS:\n"
                + "[15] - EXCLUIR ÁRVORE:\n"
                + "[16] - CALCULAR GRAU DO NÓ:\n"
                + "[17] - IMPRIMIR FORMA HIERÁRQUICA:\n"
                + "[18] - SOMAR ELEMENTOS PARES:\n"
                + "[19] - SOMAR ELEMENTOS ÍMPARES:\n"
                + "[20] - RANDOM:\n"
                + "[21] - NODOS COM DOIS FILHOS:\n"
                + "[22] - GRAU DA ÁRVORE:\n"
                + "[23] - IMPRIMIR FOLHAS:\n"
                + "[24] - IMPRIMIR NÓS COM UM FILHO:\n"
                + "[25] - IMPRIMIR NÓS COM DOIS FILHOS:\n"
                + "[26] - IMPRIMIR PAI DE UM NÓ:\n"
                + "[27] - INSERIR EM ÁRVORE 2:\n"
                + "[28] - IMPRIMIR ÁRVORE 2:\n"
                + "[29] - VERIFICAR IGUALDADE DE ÁRVORES:\n"
                + "[30] - PESQUISAR ELEMENTO EM ÁRVORE 2: \n"
                + "[31] - VERIFICAR ELEMENTOS EM ARVORE 1 E ARVORE 2:\n"
                + "[0] - SAIR: ");
        op = entrada.nextInt();
        System.out.print("---------------------------------------------------");
        System.out.println();
        return op;
    }
        
    public void metodosArvore() throws Exception{

        switch (menuArvore()){

            case 1:
                int valor;
                System.out.print("DIGITE UM VALOR: ");
                valor = entrada.nextInt();
                inserir(valor);
                metodosArvore();
                break;
                
            case 2:
                int num;
                System.out.print("DIGITE O VALOR A SER REMOVIDO: ");
                num = entrada.nextInt();
                remover(num);
                metodosArvore();
                break;
                
            case 3:
                int ni;
                System.out.print("DIGITE O VALOR DO NÓ: ");
                ni = entrada.nextInt();
                System.out.println("NÍVEL DO VALOR "+ni+" IGUAL A: "+calculaNivel(ni));
                metodosArvore();
                break;
                
            case 4:
                encontraMaximo(root);
                metodosArvore();
                break;
                
            case 5:
                encontraMinimo(root);
                metodosArvore();
                break;
                
            case 6:
                imprimirArvorePordem();
                metodosArvore();
                break;
                
            case 7:
                imprimirArvoreEordem();
                metodosArvore();
                break;
                
            case 8:
                imprimirArvore();
                metodosArvore();
                break;
                
            case 9:
                System.out.println("ALTURA = "+getAltura());
                metodosArvore();
                break;
                
            case 10:
                System.out.println("TOTAL DE ELEMENTOS: "+getQtdNode());
                metodosArvore();
                break;
                
            case 11:
                isEmpty();
                metodosArvore();
                break;
                
            case 12:
                int n;
                System.out.println("DIGITE O VALOR: ");
                n = entrada.nextInt();
                Pesquisa(n);
                metodosArvore();
                break;
                
            case 13:
                System.out.println("SOMA = "+SomaElementos(root));
                metodosArvore();
                break;
                
            case 14:
                System.out.println("MÉDIA = "+MediaElementos());
                metodosArvore();
                break;
                
            case 15:
                int op;
                System.out.println("TEM CERTEZA QUE DESEJA EXCLUIR A ÁRVORE?");
                System.out.print("[1] - SIM:\n"
                        + "[2] - NÃO: ");
                op = entrada.nextInt();
                if (op == 1){
                    RemoverArvore(root);
                    metodosArvore();
                    System.out.println();
                }else{
                    metodosArvore();
                }
                break;
                
            case 16:
               int g;
                System.out.print("DIGITE O VALOR: ");
                g = entrada.nextInt();
                System.out.println("GRAU = "+calculaGrau(root,g));
                metodosArvore();
                break;
                
            case 17:
                imprimir(root, 0);
                metodosArvore();
                break;
                
            case 18:
                System.out.println("SOMA IGUAL A: "+somarPares(root));
                metodosArvore();
                break;
                
            case 19:
                System.out.println("SOMA IGUAL A: "+somarImpares(root));
                metodosArvore();
                break;
                
            case 20:
                Aleatorio(root);
                metodosArvore();
                break;
                
            case 21:
                System.out.println("QUANTIDADE DE NÓS COM DOIS FILHOS = "+QuantFilhos(root));
                metodosArvore();
                break;
                
            case 22:
                ExibeGrauArvore(root);
                metodosArvore();
                break;
                
            case 23:
                ImprimeFolhas(root);
                metodosArvore();
                break;
                
            case 24:
                UmFilho(root);
                metodosArvore();
                break;
                
            case 25:
                DoisFilhos(root);
                metodosArvore();
                break;
                
            case 26:
                int pai;
                System.out.print("DIGITE O VALOR: ");
                pai = entrada.nextInt();
                System.out.println();
                PesquisaPai(root2,pai);
                metodosArvore();
                break;
                
            case 27:
                int valorr;
                System.out.print("DIGITE UM VALOR: ");
                valorr = entrada.nextInt();
                inserirArvore2(root2, valorr);
                metodosArvore();
                break;
                
            case 28:
                imprimirArvore2(root2, 0);
                metodosArvore();
                break;
                
            case 29:
                System.out.println("ÁRVORE 1:");
                imprimir(root, 0);
                System.out.println("---------------------------------------------------");
                System.out.println("ÁRVORE 2:");
                imprimirArvore2(root2, 0);
                System.out.println(comparar(root, root2));
                metodosArvore();
                break;
                
            case 30:
                int m;
                System.out.println("DIGITE O VALOR: ");
                m = entrada.nextInt();
                Pesquisa2(m);
                metodosArvore();
                break;
                
            case 31:
                int mk;
                System.out.print("DIGITE O VALOR: ");
                mk = entrada.nextInt();
                Verifica(mk);
                metodosArvore();
                break;
                
            case 0:
                Iniciar voltar = new Iniciar();
                voltar.metodosIniciar();
                break;
        }
    }
}
