package Teste_TRIE;

import java.util.Scanner;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    private void insertRecursive(TrieNode node, String word, int index) {
        if (index == word.length()) {
            node.isEndOfWord = true;
            return;
        }

        char c = word.charAt(index);
        int charIndex = c - 'a';

        if (node.children[charIndex] == null) {
            node.children[charIndex] = new TrieNode();
        }

        insertRecursive(node.children[charIndex], word, index + 1);
    }

    public void insert(String word) {
        insertRecursive(root, word, 0);
    }

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
            return false;
        }

        return searchRecursive(node.children[charIndex], word, index + 1);
    }

    public boolean startsWith(String prefix) {
        return startsWithRecursive(root, prefix, 0);
    }

    private boolean startsWithRecursive(TrieNode node, String prefix, int index) {
        if (index == prefix.length()) {
            return true;
        }

        char c = prefix.charAt(index);
        int charIndex = c - 'a';

        if (node.children[charIndex] == null) {
            return false;
        }

        return startsWithRecursive(node.children[charIndex], prefix, index + 1);
    }

    public boolean remove(String word) {
        return removeRecursive(root, word, 0);
    }

    private boolean removeRecursive(TrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            if (!node.isEndOfWord) {
                return false;
            }
            node.isEndOfWord = false;
            return isEmpty(node);
        }

        char c = word.charAt(index);
        int charIndex = c - 'a';

        if (removeRecursive(node.children[charIndex], word, index + 1)) {
            node.children[charIndex] = null;
            return !node.isEndOfWord && isEmpty(node);
        }
        return false;
    }

    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
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
