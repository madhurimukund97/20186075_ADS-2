/**
 * Class for tst.
 *
 * @param      <Value>  The value
 */
public class TST<Value> {
    /**
     * number.
     */
    private int n;
    /**
     * root of TST.
     */
    private Node<Value> root;
    /**
     * Class for node.
     *
     * @param      <Value>  The value
     */
    private static class Node<Value> {
        /**
         * character.
         */
        private char c;
        /**
         * left, middle, and right subtries.
         */
        private Node<Value> left, mid, right;
        /**
         * value associated with string.
         */
        private Value val;
    }
    /**
     * Initializes an empty string symbol table.
     */
    public TST() {
    }
    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }
    /**
     * contains.
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
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(final String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null argument");
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length >= 1");
        }
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }
    /**
     * return subtrie corresponding to given key.
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node<Value> get(final Node<Value> x, final String key,
        final int d) {
        if (x == null) {
            return null;
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException(
            "key must have length >= 1");
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left,  key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid,   key, d+1);
        } else {
            return x;
        }
    }

    /**
     * { function_description }.
     *
     * @param      key   The key
     * @param      val   The value
     */
    public void put(final String key, final Value val) {
        if (key == null) {
            throw new IllegalArgumentException(
                "calls put() with null key");
        }
        if (!contains(key)) {
            n++;
        }
        root = put(root, key, val, 0);
    }
    /**
     * { function_description }.
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      val   The value
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node<Value> put(final Node<Value> x,
        final String key, final Value val, final int d) {
        char c = key.charAt(d);
        Node<Value> x1 = x;
        if (x1 == null) {
            x1 = new Node<Value>();
            x1.c = c;
        }
        if (c < x1.c) {
            x1.left  = put(x1.left,  key, val, d);
        } else if (c > x1.c) {
            x1.right = put(x1.right, key, val, d);
        } else if (d < key.length() - 1) {
            x1.mid   = put(x1.mid,   key, val, d+1);
        } else {
            x1.val   = val;
        }
        return x1;
    }

    /**
     * { function_description }.
     *
     * @param      query  The query
     *
     * @return     { description_of_the_return_value }
     */
    public String longestPrefixOf(final String query) {
        if (query == null) {
            throw new IllegalArgumentException(
                "calls longestPrefixOf() with null argument");
        }
        if (query.length() == 0) {
            return null;
        }
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            }
            else {
                i++;
                if (x.val != null) {
                    length = i;
                }
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }
    /**
     * { function_description }.
     *
     * @param      prefix  The prefix
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException(
                "calls keysWithPrefix() with null argument");
        }
        Queue<String> queue = new Queue<String>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) {
            return queue;
        }
        if (x.val != null) {
            queue.enqueue(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }
    /**
     * all keys in subtrie rooted at x with given prefix.
     *
     * @param      x       { parameter_description }
     * @param      prefix  The prefix
     * @param      queue   The queue
     */
    private void collect(final Node<Value> x,
        final StringBuilder prefix, final Queue<String> queue) {
        if (x == null) {
            return;
        }
        collect(x.left,  prefix, queue);
        if (x.val != null) {
            queue.enqueue(prefix.toString() + x.c);
        }
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }
    /**
     * { function_description }.
     *
     * @param      pattern  The pattern
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keysThatMatch(final String pattern) {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }
    /**
     * { function_description }.
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      i        { parameter_description }
     * @param      pattern  The pattern
     * @param      queue    The queue
     */
    private void collect(final Node<Value> x, final StringBuilder prefix,
        final int i, final String pattern, final Queue<String> queue) {
        if (x == null) {
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) {
            collect(
            x.left, prefix, i, pattern, queue);
        }
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.val != null) {
                queue.enqueue(
                prefix.toString() + x.c);
            }
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i + 1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) {
            collect(
            x.right, prefix, i, pattern, queue);
        }
    }
}
