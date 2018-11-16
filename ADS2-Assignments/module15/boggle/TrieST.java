/**
 * Class for trie st.
 *
 * @param      <Value>  The value
 */
public class TrieST<Value> {
    /**
     * extended ASCII.
     */
    private static final int R = 256;
    /**
     * root of trie.
     */
    private Node root;
    /**
     * number of keys in trie.
     */
    private int n;
    /**
     * Class for node.
     */
    private static class Node {
        /**
         * Object.
         */
        private Object val;
        /**
         * Node array.
         */
        private Node[] next = new Node[R];
    }
    /**
     * Initializes an empty string symbol table.
     */
    public TrieST() {
    }
    /**
     * get method.
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public Value get(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
            "argument to get() is null");
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }
    /**
     * contains a key.
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
            "argument to contains() is null");
        }
        return get(key) != null;
    }
    /**
     * get a key.
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node get(final Node x, final String key,
        final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }
    /**
     * put a key.
     *
     * @param      key   The key
     * @param      val   The value
     */
    public void put(final String key, final Value val) {
        if (key == null) {
            throw new IllegalArgumentException(
            "first argument to put() is null");
        }
        if (val == null) {
            delete(key);
        } else {
            root = put(root, key, val, 0);
        }
    }
    /**
     * put a value and integer.
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      val   The value
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node put(final Node x, final String key,
        final Value val, final int d) {
        Node x1 = x;
        if (x1 == null) {
            x1 = new Node();
        }
        if (d == key.length()) {
            if (x1.val == null) {
                n++;
            }
            x1.val = val;
            return x1;
        }
        char c = key.charAt(d);
        x1.next[c] = put(x.next[c], key, val, d+1);
        return x1;
    }
    /**
     * Integer size.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return n;
    }
    /**
     * Determines if it has prefix.
     *
     * @param      key   The key
     *
     * @return     True if has prefix, False otherwise.
     */
    public boolean hasPrefix(String key) {
        Node node = get(root, key, 0);
        return node != null;
    }
    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    /**
     * key iterable.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
    /**
     * keyswith prefix.
     *
     * @param      prefix  The prefix
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }
    /**
     * collect.
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      results  The results
     */
    private void collect(final Node x, final StringBuilder prefix,
        final Queue<String> results) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            results.enqueue(prefix.toString());
        }
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    /**
     * keysthat match.
     *
     * @param      pattern  The pattern
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keysThatMatch(final String pattern) {
        Queue<String> results = new Queue<String>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }
    /**
     * collect.
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      pattern  The pattern
     * @param      results  The results
     */
    private void collect(final Node x, final StringBuilder prefix,
        final String pattern, final Queue<String> results) {
        if (x == null) {
            return;
        }
        int d = prefix.length();
        if (d == pattern.length() && x.val != null) {
            results.enqueue(prefix.toString());
        }
        if (d == pattern.length()) {
            return;
        }
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    /**
     * longestprefix of.
     *
     * @param      query  The query
     *
     * @return     { description_of_the_return_value }
     */
    public String longestPrefixOf(final String query) {
        if (query == null) {
            throw new IllegalArgumentException(
                "argument to longestPrefixOf() is null");
        }
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) {
            return null;
        } else {
            return query.substring(0, length);
        }
    }
    /**
     * LONGESTPREFIX OF.
     *
     * @param      x       { parameter_description }
     * @param      query   The query
     * @param      d       { parameter_description }
     * @param      length  The length
     *
     * @return     { description_of_the_return_value }
     */
    private int longestPrefixOf(final Node x,
        final String query, final int d, final int length) {
        int length1 = length;
        if (x == null) {
            return length1;
        }
        if (x.val != null) {
            length1 = d;
        }
        if (d == query.length()) {
            return length1;
        }
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length1);
    }
    /**
     * DELETE.
     *
     * @param      key   The key
     */
    public void delete(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
            "argument to delete() is null");
        }
        root = delete(root, key, 0);
    }
    /**
     * delete node with string key.
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node delete(final Node x,
        final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.val != null) {
                n--;
            }
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.val != null) {
            return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }
}
