public class Main {
    public static void main(String[] args) {
        var trie = new Trie();

        trie.insert("ball");
        trie.insert("baller");
        trie.insert("good");
        trie.insert("bat");
        trie.insert("bad");

        System.out.println(trie.contains("bat"));
        System.out.println(trie.contains("good"));
        System.out.println(trie.contains("strange"));
        System.out.println(trie.findWords("g"));
    }
}
