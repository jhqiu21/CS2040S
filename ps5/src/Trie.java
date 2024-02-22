import java.util.ArrayList;
import java.lang.StringBuilder;

public class Trie {

    // Wildcards
    final char WILDCARD = '.';
    int size = 62;
    TrieNode root;
    private class TrieNode {
        // TODO: Create your TrieNode class here.
        TrieNode[] presentChars = new TrieNode[size];
        char key;
        boolean flag;
        TrieNode() {
            // initialize a node
            for (int i = 0; i < 62; i++) {
                this.presentChars[i] = null;
            }
            this.key = WILDCARD;
            this.flag = false;
        }

        TrieNode(char key) {
            // initialize a node with key
            this.key = key;
            this.flag = false;
        }

        public void setFlagEnd() {
            this.flag = true;
        }

        public boolean isNumber(char key) {
            int index = key;
            return (index >= 48 && index <= 57);
        }

        public boolean isUpper(char key) {
            int index = key;
            return (index >= 65 && index <= 90);
        }

        public boolean isLower(char key) {
            int index = key;
            return (index >= 97 && index <= 122);
        }

        public void setNode(TrieNode node) {
            // set the node with a node has a character key
            int index = node.key;
            // if character key is number
            if (isNumber(node.key)) {
                this.presentChars[index - 48] = node;
            }
            // if character key is from 'A' to 'Z'
            if (isUpper(node.key)) {
                this.presentChars[index - 55] = node;
            }
            // if character key is from 'a' to 'z'
            if (isLower(node.key)) {
                this.presentChars[index - 61] = node;
            }
        }

        public boolean contains(char key) {
            // return true if the children contains character key
            int index = key;
            // if character key is number
            if (isNumber(key)) {
                return this.presentChars[index - 48] != null;
            }
            // if character key is from 'A' to 'Z'
            if (isUpper(key)) {
                return this.presentChars[index - 55] != null;
            }
            // if character key is from 'a' to 'z'
            if (isLower(key)) {
                return this.presentChars[index - 61] != null;
            }
            return false;
        }

        public TrieNode findKeyNode(char key) {
            // Return the node that contain the character key
            int index = key;
            // if character key is number
            if (isNumber(key)) {
                return this.presentChars[index - 48];
            }
            // if character key is from 'A' to 'Z'
            if (isUpper(key)) {
                return this.presentChars[index - 55];
            }
            // if character key is from 'a' to 'z'
            if (isLower(key)) {
                return this.presentChars[index - 61];
            }
            return null;
        }

    }

    public Trie() {
        // TODO: Initialise a trie class here.
        root = new TrieNode();
    }

    /**
     * Inserts string s into the Trie.
     *
     * @param s string to insert into the Trie
     */

    void insert(String s) {
        // TODO
        int len = s.length();
        TrieNode node = root;
        for (int i = 0; i < len; i++) {
            char curr = s.charAt(i);
            if (node.contains(curr)) {
                node = node.findKeyNode(curr);
            } else {
                TrieNode newNode = new TrieNode(curr);
                node.setNode(newNode);
                node = newNode;
            }
        }
        node.setFlagEnd();
    }

    /**
     * Checks whether string s exists inside the Trie or not.
     *
     * @param s string to check for
     * @return whether string s is inside the Trie
     */
    boolean contains(String s) {
        // TODO
        TrieNode node = root;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char curr = s.charAt(i);
            if (node.contains(curr)) {
                node = node.findKeyNode(curr);
            } else {
                return false;
            }
        }
        return node.flag;
    }

    /**
     * Searches for strings with prefix matching the specified pattern sorted by lexicographical order. This inserts the
     * results into the specified ArrayList. Only returns at most the first limit results.
     *
     * @param s       pattern to match prefixes with
     * @param results array to add the results into
     * @param limit   max number of strings to add into results
     */

    /**
     * for prefixSearch there are 3 situation
     * 0. empty string => do nothing and return?
     * 1. there is no wildcard in the string => we need to check the flag before the add it in result
     * 2. this wildcard in the string => recursion like a bfs in the tree
     * 3.
     * */

    void prefixSearch(String s, ArrayList<String> results, int limit) {
        // TODO
        TrieNode node = root;
        StringBuilder str = new StringBuilder();
        if (s.isEmpty()) {
            // if the string is empty, we do nothing and return
            // TODO: need to fix the empty prefix case!
            s = ".";
        }

        if (s.contains(".")) {
            // if the string contains wildcard we will jump to the helper
            // function and generate string with wildcard recursively
            prefixSearchHelper(s, results, str, node, limit);
        } else {
            // if string do not contain any wildcard we need to find the string in the trie
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char curr = s.charAt(i);
                if (node.contains(curr)) {
                    node = node.findKeyNode(curr);
                    str.append(curr);
                } else {
                    // if there is no string in the trie we return
                    return ;
                }
            }
            // Final check before adding
            bigSearch(results, str, limit, node);
        }
    }

    void prefixSearchHelper(String s, ArrayList<String> results, StringBuilder str, TrieNode node, int limit) {
        if (s.isEmpty()) {
            bigSearch(results, str, limit, node);
            return ;
        }

        char curr = s.charAt(0);
        if (curr == WILDCARD) {
            int index = str.length();
            for (int i = 0; i < size; i++) {
                TrieNode currNode = node.presentChars[i];
                if (currNode != null) {
                    str.append(currNode.key);
                    prefixSearchHelper(s.substring(1), results, str, currNode, limit);
                    str.delete(index, str.length());
                }
            }
        }

        if (curr != WILDCARD && node.contains(curr)) {
            str.append(curr);
            node = node.findKeyNode(curr);
            prefixSearchHelper(s.substring(1), results, str, node, limit);
        }
    }

    void bigSearch(ArrayList<String> results, StringBuilder str, int limit, TrieNode node) {

        if (node.flag && results.size() < limit) {
            // if the last node with flag => we add it
            String string = str.toString();
            results.add(string);
        }
        // match with random suffix until it reach the leaf
        TrieNode currNode;
        for (int i = 0; i < size; i++) {
            currNode = node.presentChars[i];
            if (currNode != null) {
                str.append(currNode.key);
                bigSearch(results, str, limit, currNode);
                str.deleteCharAt(str.length() - 1);
            }
        }
    }

    // Simplifies function call by initializing an empty array to store the results.
    // PLEASE DO NOT CHANGE the implementation for this function as it will be used
    // to run the test cases.
    String[] prefixSearch(String s, int limit) {
        ArrayList<String> results = new ArrayList<String>();
        prefixSearch(s, results, limit);
        return results.toArray(new String[0]);
    }


    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("peter");
        t.insert("piper");
        t.insert("picked");
        t.insert("a");
        t.insert("peck");
        t.insert("of");
        t.insert("pickled");
        t.insert("peppers");
        t.insert("pepppito");
        t.insert("pepi");
        t.insert("pik");

        String[] result1 = t.prefixSearch("pe", 10);
        String[] result2 = t.prefixSearch("pe.", 10);
        /*
        for (int i = 0; i < result1.length; i++) {
            System.out.println(result1[i]);
        }*/
        for (int i = 0; i < result1.length; i++) {
            System.out.println(result2[i]);
        }

        // result1 should be:
        // ["peck", "pepi", "peppers", "pepppito", "peter"]
        // result2 should contain the same elements with result1 but may be ordered arbitrarily
    }
}
