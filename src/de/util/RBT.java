package de.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RBT<T> {
    private RBTNode<T> nil = new RBTNode<>(-1, null);
    private RBTNode<T> root;

    public RBT() {
        this.root = new RBTNode<>(this.nil);
        this.root.color = false; // Wurzel immer schwarz
        this.root.left = new RBTNode<>(this.nil);
        this.root.right = new RBTNode<>(this.nil);
        this.root.parent = new RBTNode<>(this.nil);
    }

    public List<T> getValuesAsList(){
        Stack<RBTNode<T>> stack = new Stack<>();
        List<T> result = new ArrayList<>();
        stack.push(this.root);

        while (!stack.empty()) {

            RBTNode<T> temp = stack.peek();
            result.add(temp.val);
            stack.pop();

            if (!temp.right.equals(this.nil)) {
                stack.push(temp.right);
            }
            if (!temp.left.equals(this.nil)) {
                stack.push(temp.left);
            }
        }

        return result;
    }

    public List<Integer> getKeysAsList(){
        Stack<RBTNode<T>> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        stack.push(this.root);

        while (!stack.empty()) {

            RBTNode<T> temp = stack.peek();
            result.add(temp.key);
            stack.pop();

            if (!temp.right.equals(this.nil)) {
                stack.push(temp.right);
            }
            if (!temp.left.equals(this.nil)) {
                stack.push(temp.left);
            }
        }

        return result;
    }

    public RBTNode<T> getRoot() {
        return this.root;
    }

    public void insert(int k, T v) {
        RBTNode<T> x = this.root;
        RBTNode<T> y = new RBTNode<>(this.nil);
        RBTNode<T> z = new RBTNode<>(k, v);
        while (!(x.equals(nil))) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y.equals(nil)) {
            this.root = z;
        } else {
            if (z.key < y.key) {
                y.left = z;
            } else {
                y.right = z;
            }
        }

        z.left = nil;
        z.right = nil;
        z.color = true;
        RB_Repair_Insert(z);
    }

    public T search(int k) {
        return search(this.root, k);
    }

    private T search(RBTNode<T> searchRoot, int k) {
        if (searchRoot.equals(nil)) {
            //System.out.println("ERROR - Node with key " + k + " not found!");
            return null;
        } else if (searchRoot.key == k) {
            return searchRoot.val;
        } else if (k > searchRoot.key) {
            return search(searchRoot.right, k);
        } else {
            return search(searchRoot.left, k);
        }
    }

    public int height() {
        return height(this.root);
    }

    private int height(RBTNode<T> heightRoot) {
        if (heightRoot.equals(nil)) {
            return 0;
        }
        int heightLeftSubtree = 1;
        int heightRightSubtree = 1;
        heightLeftSubtree += height(heightRoot.left);
        heightRightSubtree += height(heightRoot.right);
        return Math.max(heightLeftSubtree, heightRightSubtree);
    }

    public boolean checkRB() {
        if (this.root.equals(nil)) {
            return false;
        } else {
            // Binäre Eigenschaften
            return isValidBST(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE)
                    // 1., 3., 4.
                    && checkColor(this.root)
                    // 2. Die Wurzel ist schwarz.
                    && !this.root.color
                    // 5. Für jeden Knoten enthält jeder Pfad zu einem Blatt die gleiche Anzahl von schwarzen Knoten.
                    && checkBlackheight(this.root);
        }
    }

    // Test der Binären Suchbaumeigenschaften
    private boolean isValidBST(RBTNode<T> z, int min, int max) {
        if (z.equals(nil)) {
            return true;
        }

        if (z.key < min || z.key > max) {
            return false;
        }

        return isValidBST(z.left, min, z.key) && isValidBST(z.right, z.key, max);
    }

    // Test der Eigenschaften 1., 3. und 4.
    private boolean checkColor(RBTNode<T> z) {
        if(!z.equals(nil)) {

            // 1. Jeder Knoten ist rot oder schwarz.
            if(z.color.equals(null)) {
                return false;
            }

            // 4. Bei nur einem Nachfolger ist dies ein rotes Blatt.
            if(z.left.equals(nil) && !z.right.equals(nil)) {
                if(!z.right.color) {
                    return false;
                }
            }
            if(!z.left.equals(nil) && z.right.equals(nil)) {
                if(!z.left.color) {
                    return false;
                }
            }

            // 3. Ist ein Knoten rot, so sind seine Kinder schwarz.
            if(z.color && z.left.color && z.right.color) {
                return false;
            }

            return checkColor(z.left) && checkColor(z.right);
        }
        return true;
    }

    // Test der Schwarzhöhe
    private boolean checkBlackheight(RBTNode<T> z) {
        if(blackheight(this.root) == 0) {
            return false;
        }
        return true;
    }


    /*
     * blackheight-Methode wurde mithilfe des Pseudocodes von
     * https://stackoverflow.com/questions/13848011/how-to-check-the-black-height-of-a-node-for-all-paths-to-its-descendent-leaves (Stand: 11.5.19 15:40 Uhr)
     * erstellt.
     */
    private int blackheight(RBTNode<T> z) {
        if(z.equals(nil)) {
            return 1;
        }
        int leftBlackHeight = blackheight(z.left);
        if (leftBlackHeight == 0) {
            return leftBlackHeight;
        }
        int rightBlackHeight = blackheight(z.right);
        if (rightBlackHeight == 0) {
            return rightBlackHeight;
        }
        // Returnwert 0 bei Fehler
        if (leftBlackHeight != rightBlackHeight) {
            return 0;
        } else {
            if(!z.color) {
                return leftBlackHeight + 1;
            } else {
                return leftBlackHeight;
            }
        }
    }

    // Repair Methoden:
    private void RB_Repair_Insert(RBTNode<T> z) {
        while (!z.equals(this.nil) && z.parent.color == true) {
            if (z.parent.equals(z.parent.parent.left)) {

                // Fall 1
                RBTNode<T> y = z.parent.parent.right;
                if (y.color == true) {
                    z.parent.color = false;
                    y.color = false;
                    z.parent.parent.color = true;
                    z = z.parent.parent;

                    // Fall 2
                } else {
                    if (z.equals(z.parent.right)) {
                        z = z.parent;
                        left_rotate(z);
                    }

                    // Fall 3
                    z.parent.color = false;
                    z.parent.parent.color = true;
                    right_rotate(z.parent.parent);
                }
            } else {

                // Fall 4
                RBTNode<T> y = z.parent.parent.left;
                if (y.color == true) {
                    z.parent.color = false;
                    y.color = false;
                    z.parent.parent.color = true;
                    z = z.parent.parent;

                    // Fall 5
                } else {
                    if (z.equals(z.parent.left)) {
                        z = z.parent;
                        right_rotate(z);
                    }

                    // Fall 6
                    z.parent.color = false;
                    z.parent.parent.color = true;
                    left_rotate(z.parent.parent);
                }
            }
        }
        this.root.color = false;
    }

    private void left_rotate(RBTNode<T> x) {
        if (x.right.equals(nil)) {
            return;
        }

        RBTNode<T> y = x.right;
        x.right = y.left;
        if (!(y.left.equals(nil))) {
            y.left.parent = x;
        }
        y.parent = x.parent;

        if (x.parent.equals(nil)) {
            this.root = y;
        } else {
            if (x.equals(x.parent.left)) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

    private void right_rotate(RBTNode<T> x) {
        if (x.left.equals(nil)) {
            return;
        }

        RBTNode<T> y = x.left;
        x.left = y.right;
        if (!(y.right.equals(nil))) {
            y.right.parent = x;
        }
        y.parent = x.parent;

        if (x.parent.equals(nil)) {
            this.root = y;
        } else {
            if (x.equals(x.parent.right)) {
                x.parent.right = y;
            } else {
                x.parent.left = y;
            }
        }
        y.right = x;
        x.parent = y;
    }

    // Färbt alle Knoten rot
    private void makeTreeInvalid() {
        makeTreeInvalid(this.root);
    }

    private void makeTreeInvalid(RBTNode<T> z) {
        if(!z.equals(nil)) {
            z.left.color = true;
            z.right.color = true;
            makeTreeInvalid(z.left);
            makeTreeInvalid(z.right);
        }
    }
}
