package Teste_TRIE;

import java.util.Scanner;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() { //chamado sempre que um novo nó é chamado. por ser um construct publico, ele pode ser chamado por qualquer outra classe.
        children = new TrieNode[26]; // Para letras de 'a' a 'z'
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root; // ta sendo criado um root(raiz) do tipo TrieNode, ou seja, root vai conter todas as caracteristicas da class.

    public Trie() {
        root = new TrieNode(); // começando o nó
    }

    private void insertRecursive(TrieNode node, String word, int index) { //Criando um nó 
        if (index == word.length()) {
            node.isEndOfWord = true;
            return;
        }

        char c = word.charAt(index); //pega o caracter da palavra na posição (index)
        int charIndex = c - 'a'; // Convertendo 'a' -> 0, 'b' -> 1, ..., 'z' -> 25 (Levando em consideração a tabel ASCII)

        if (node.children[charIndex] == null) {
            node.children[charIndex] = new TrieNode(); //Criação de um novo nó, para a nova letra.
        }
     // caso encontre a letra em especifico, é rodado o codigo abaixo, para a proxima letra passar pelo mesmo procedimento
        insertRecursive(node.children[charIndex], word, index + 1);
    }

    public void insert(String word) { //começo da incerção, ou sejá, começando com a raiz, e no index 0 (primeira letra)
        insertRecursive(root, word, 0);
    }

    public boolean search(String word) { //começo da procura
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode node, String word, int index) { // procurando cada letra na arvore. conforme a palavra colocada no parametro.
        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char c = word.charAt(index); //pega o caracter da palavra na posição (index)
        int charIndex = c - 'a'; // Convertendo 'a' -> 0, 'b' -> 1, ..., 'z' -> 25 (Levando em consideração a tabel ASCII)

        if (node.children[charIndex] == null) {
            return false;
        }

        return searchRecursive(node.children[charIndex], word, index + 1);
    }

    public boolean startsWith(String prefix) {
        return startsWithRecursive(root, prefix, 0);
    }
 
    private boolean startsWithRecursive(TrieNode node, String prefix, int index) { // procura palavras, na arvore, que começa com o prefixo colocado no parametro.
        if (index == prefix.length()) {
            return true;
        }

        char c = prefix.charAt(index); //pega o caracter da palavra na posição (index)
        int charIndex = c - 'a'; // Convertendo 'a' -> 0, 'b' -> 1, ..., 'z' -> 25 (Levando em consideração a tabel ASCII)

        if (node.children[charIndex] == null) {
            return false;
        }

        return startsWithRecursive(node.children[charIndex], prefix, index + 1);
    }

    public void printTrie() {
        printTrieRecursive(root, "", 0);
    }

    private void printTrieRecursive(TrieNode node, String prefix, int level) {
        if (node == null) return;
        
        if (node.isEndOfWord) {
            System.out.println(" ".repeat(level * 2) + "└── " + prefix);
        }
        
        for (char c = 'a'; c <= 'z'; c++) {
            int index = c - 'a';
            if (node.children[index] != null) {
                System.out.println(" ".repeat(level * 2) + "├── " + c);
                printTrieRecursive(node.children[index], prefix + c, level + 1);
            }
        }
    }
}

public class Trie_Visual {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String t;
        Trie trie = new Trie();

        trie.insert("carro");
        trie.insert("casa");
        trie.insert("cap");
        
        
//        do {
//        t = sc.nextLine();
//        trie.insert(t);
//        }while(!t.equals("fim"));

        System.out.println("\nEstrutura da Trie:");
        trie.printTrie();

        System.out.println("\nTestes de busca:");
        System.out.println("carro encontrado? " + trie.search("carro"));  
        System.out.println("capa encontrado? " + trie.search("capa"));   
        System.out.println("Prefixo 'ca' existe? " + trie.startsWith("ca")); 
//        System.out.println("Palavra do usuario "+trie.search(t));
        
        sc.close();
    }
}
