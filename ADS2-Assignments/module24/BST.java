import java.util.NoSuchElementException;
import java.util.Scanner;
class Stopwatch { 
    private final long start;
    public Stopwatch() {
        start = System.currentTimeMillis();
    } 

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }   
}
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    Stopwatch stop = new Stopwatch();
    private double getTime;
    private double putTime;
    static Double putCounter = 0.0;
    static Double getCounter = 0.0;
    private class Node { 
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    public double getRatio(){
        return putCounter/getCounter;
    }
    public void display() {
        double sum = putCounter + getCounter;
        double putratio = putCounter/sum*100;
        double getratio = getCounter/sum*100;
        System.out.println("No of Put Operations: "+putCounter);
        System.out.println("put ratio: "+putratio);
        System.out.println("No of get Operations: "+getCounter);
        System.out.println("get ratio: "+getratio);
        System.out.println("put/get operation: " + getRatio());
        System.out.println("put/getratio: " + putratio/getratio);
        System.out.println("gettime: "+getTime);
        System.out.println("puttime: "+putTime);
        System.out.println("puttime/gettime: "+getTime/putTime);
    }

    public BST() {
    }

    
    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        // getCounter++;
        getTime = stop.elapsedTime();
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("called get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        getCounter++;
        getTime = stop.elapsedTime();
        if (cmp < 0) {
            return get(x.left, key);
        }
        else if (cmp > 0) {
            return get(x.right, key);
        }
        else {
            return x.val;
        }

    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException(
            "calledput() with a null key");
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        putCounter++;
        putTime = stop.elapsedTime();
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        
        return x;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int num = Integer.parseInt(s.nextLine());
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        for(int i =0;i<num;i++) {
            int temp = Integer.parseInt(s.nextLine());
            if(!bst.contains(temp)) {
                bst.put(temp, 1);
            } else {
                bst.put(temp, bst.get(temp)+1);
            }
        }
        bst.display();
    }
}
