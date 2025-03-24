package Teste_TRIE;

import java.util.Scanner;

// Classe que representa um nó da Trie
class TrieNode {
    TrieNode[] children;// Array de nós filhos (26 letras do alfabeto)
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

// Classe que representa a Trie
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode(); // Inicializa a Trie com um nó raiz vazio
    }

    // Método recursivo para inserir uma palavra na Trie
    private void insertRecursive(TrieNode node, String word, int index) {
        if (index == word.length()) {
            node.isEndOfWord = true;
            return;
        }

        char c = word.charAt(index);
        int charIndex = c - 'a'; // Calcula a posição no array (0-25)

        if (node.children[charIndex] == null) {
            node.children[charIndex] = new TrieNode(); // Cria um novo nó, se necessário
        }

        insertRecursive(node.children[charIndex], word, index + 1); // Chama recursivamente para o próximo caractere
    }

    public void insert(String word) {
        insertRecursive(root, word, 0); // Insere a palavra a partir da raiz
    }

    // Método para buscar uma palavra na Trie
    public boolean search(String word) {
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode node, String word, int index) {
        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char c = word.charAt(index);
        int charIndex = c - 'a';

        if (node.children[charIndex] == null) {
            return false; // Se o nó filho não existe, a palavra não está na Trie
        }

        return searchRecursive(node.children[charIndex], word, index + 1);
    }

    // Método para verificar se uma palavra começa com um prefixo
    public boolean startsWith(String prefix) {
        return startsWithRecursive(root, prefix, 0);
    }

    private boolean startsWithRecursive(TrieNode node, String prefix, int index) {
        if (index == prefix.length()) {
            return true; // Encontrou o prefixo
        }

        char c = prefix.charAt(index);
        int charIndex = c - 'a';

        if (node.children[charIndex] == null) {
            return false; // Se não há caminho, o prefixo não existe
        }

        return startsWithRecursive(node.children[charIndex], prefix, index + 1);
    }

    // Método para remover uma palavra da Trie
    public boolean remove(String word) {
        return removeRecursive(root, word, 0);
    }

    private boolean removeRecursive(TrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            if (!node.isEndOfWord) {
                return false; // A palavra não está marcada como fim de palavra
            }
            node.isEndOfWord = false; // Desmarca como fim de palavra
            return isEmpty(node); // Verifica se o nó pode ser removido
        }

        char c = word.charAt(index);
        int charIndex = c - 'a';

        if (removeRecursive(node.children[charIndex], word, index + 1)) {
            node.children[charIndex] = null; // Remove a referência ao filho
            return !node.isEndOfWord && isEmpty(node); // Remove o nó se não for o fim de outra palavra
        }
        return false;
    }

    // Método para verificar se um nó está vazio (sem filhos)
    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }

    // Método para imprimir a estrutura da Trie
    public void printTrie() {
        printTrieRecursive(root, "", 0);
    }

    private void printTrieRecursive(TrieNode node, String prefix, int level) {
        if (node == null) return;
        
        if (node.isEndOfWord) {
            System.out.println(" ".repeat(level * 2) + "└── " + prefix); // Exibe a palavra completa
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

public class Trie_New {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trie trie = new Trie();

        trie.insert("carro");
        trie.insert("casa");
        trie.insert("cap");

        System.out.println("\nEstrutura da Trie antes da remoção:");
        trie.printTrie();

        System.out.println("\nRemovendo 'carro'...");
        trie.remove("carro");

        System.out.println("\nEstrutura da Trie após a remoção:");
        trie.printTrie();

        sc.close();
    }
}
