package assignmentfive;

import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }
    
    /**
     * Overloaded search method that will allow us to count number of
     * comparisons in client. Still returns true if word is contained in list and
     * false if it is not.
     *
     * @param e : the element that is being searched for
     * @param count : integer array for number of comparisons
     * @return : true or false for whether the element is in the list
     */
    public boolean search(E e, int[] count){
        TreeNode<E> current = root; // Start from the root
        int i = 0;
        while (current != null) {
            i++;        
            count[0] = i;
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Inner class tree node
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     * 
     * @param e : element that we want the path to
     * @return : ArrayList of elements in path to number, empty if number is not in tree
     */
    public ArrayList<E> path(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            list.add(current.element);
            if (e.compareTo(current.element) > 0) {
                current = current.right;
            }//if
            else if (e.compareTo(current.element) < 0) {
                current = current.left;
            }//else if
            else {
                break;
            }//else
        }//while
        if(current == null)
          list.clear();// element not found so clear the history
        return list;
    }//path
    
    /**
     * Needs access to root so had to make private overloaded method to allow access
     * in this method. Then just call the overloaded method.
     * 
     * @return : the number of leaves in the tree
     */
    public int getNumberOfLeaves(){
        return getNumberOfLeaves(root);
    }//getNumberOfLeavess

    /** 
     * Returns the number of leaf nodes in this tree, returns 0 if tree is empty.
     * 
     * @param root : root of tree (allows access in public method)
     */
    private int getNumberOfLeaves(TreeNode<E> root) {
        //left for you to implement in Lab 9
        if (root == null) {
            return 0;
        }//if
        else if (root.left == null && root.right == null) {
            return 1;
        }//else if
        else {
            return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
        }//else
    }//getNumberOfLeaves
    
    /**
     * Helper method to allow us to get branch for left and right subtree methods.
     * 
     * @param current : current node
     * @param branch : ArrayList of elements in branch
     */
    public void getBranch(TreeNode<E> current, ArrayList<E> branch) {
        if (current == null) {
            return;
        }//if
        branch.add(current.element);
        getBranch(current.left, branch);
        getBranch(current.right, branch);
    }//getBranch

    /** 
     * Returns an ArrayList containing all elements in preorder of the specified 
     * element’s left sub-tree, returns an empty ArrayList if no  such element exists. 
     * 
     * @param e : element started at to get right subtree of
     * @return : ArrayList of right subtree from element
     */
    public ArrayList<E> leftSubTree(E e) {
        ArrayList<E> list = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }//if
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }//else if
            else {
                break;
            }//else
        }//while
        current = current.left;
        getBranch(current, list);
        return list;
    }//leftSubTree

    /** 
     * Returns an ArrayList containing all elements in preorder of the specified 
     * element’s right sub-tree, returns an empty ArrayList if no  such element exists.
     * 
     * @param e : element started at to get right subtree of
     * @return : ArrayList of right subtree from element
     */
    public ArrayList<E> rightSubTree(E e) {
        ArrayList<E> list = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }//if
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }//else if
            else {
                break;
            }//else
        }//while
        current = current.right;
        getBranch(current, list);
        return list;
    }//rightSubTree

    /**
     * Needs access to root so had to make private overloaded method to allow access
     * in this method. Then just call the overloaded method.
     * 
     * @param tree : tree that is being compared
     * @return : true or false if the two trees are the same
     */
    public boolean sameTree(BinarySearchTree tree){
        return sameTree(this.root, tree.root);
    }//sameTree
    
    /**
     * This method compares to trees to see if they are the same. It will compare 
     * each element in each tree. If they are the same it will return true and false 
     * if they are not the same. 
     * 
     * @param root1 : root of the first tree
     * @param root2 : root of the second tree
     * @return true or false if the two trees are the same
     */
    private boolean sameTree(TreeNode<E> root1, TreeNode<E> root2){        
        if(root1 == null && root2 == null)
            return true;
        else if(root1 != null && root2 != null)
            return root1.element.equals(root2.element) && sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right);
        else
            return false;
    }//sameTree

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
    
    public void output() {
        System.out.println("===============================================================");
        System.out.println("TREE OUTPUT:");
        System.out.print("inorder() = \t");
        inorder();
        System.out.print("\npreorder() = \t");
        preorder();
        System.out.print("\npostorder() = \t");
        postorder();
        System.out.println("");
        System.out.println("===============================================================");
    }
}
