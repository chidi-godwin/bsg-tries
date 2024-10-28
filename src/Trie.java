import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    private class Node {
        private char value;
        private boolean isEndofWord;

        private HashMap<Character, Node> children  = new HashMap<>();

        public Node(char value) {
            this.value = value;
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }

        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }
    }

    private final Node root = new Node(' ');

    public void insert(String word) {
        var current = root;
        for (var ch: word.toCharArray()) {
            if (!current.children.containsKey(ch)){
                current.children.put(ch, new Node(ch));
            }
            current = current.children.get(ch);
        }
        current.isEndofWord = true;
    }

    public boolean contains(String word) {
        var current = root;
        for (var ch: word.toCharArray()) {
            if (!current.children.containsKey(ch))
                return false;
            current = current.children.get(ch);
        }

        return current.isEndofWord;
    }

    public void remove(String word) {
        remove(root, word, 0);
    }

    // ball
    private void remove(Node root, String word, int index) {
        // '', ball, 0
        // <b>, ball, 1
        // <a>, ball, 2 ->
        // <l>, ball, 3 ->
        // <l>, ball, 4 x
        if (index == word.length()) {
            root.isEndofWord = false;
            return;
        }

        var ch = word.charAt(index); // b, a
        var child = root.getChild(ch);
        if (child == null) return;

        remove(child, word, index + 1);

        if (!child.children.isEmpty() && !child.isEndofWord)
            root.children.remove(child.value);
    }

    private Node findLastNode(String prefix) {
        var current = root;

        for (var ch: prefix.toCharArray()) {
            var child = current.children.get(ch);
            if (child == null)
                return null;
            current = child;
        }

        return current;
    }

    private void findWords(String prefix, Node root, List<String> words) {
        if (root == null)
            return;

        if (root.isEndofWord)
            words.add(prefix);

        for (var child: root.getChildren()) {
            findWords(prefix + child.value, child, words);
        }
    }

    public List<String> findWords(String prefix) {
        List<String> words = new ArrayList<>();
        var lastNode = findLastNode(prefix);
        if (lastNode == null)
            return null;

        findWords(prefix, lastNode, words);

        return words;
    }
}
