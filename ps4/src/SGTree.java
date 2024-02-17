import com.sun.source.tree.Tree;

/**
 * ScapeGoat Tree class
 *
 * This class contains some of the basic code for implementing a ScapeGoat tree.
 * This version does not include any of the functionality for choosing which node
 * to scapegoat.  It includes only code for inserting a node, and the code for rebuilding
 * a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    /**
     * TreeNode class.
     *
     * This class holds the data for a node in a binary tree.
     *
     * Note: we have made things public here to facilitate problem set grading/testing.
     * In general, making everything public like this is a bad idea!
     *
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;

        TreeNode(int k) {
            key = k;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the specified subtree
     *
     * @param node  the parent node, not to be counted
     * @param child the specified subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node, Child child) {
        // TODO: Implement this
        if (child == Child.LEFT) {
            if (node.left == null) {
                return 0;
            } else {
                int leftCount = countNodes(node.left, Child.LEFT);
                int rightCount = countNodes(node.left, Child.RIGHT);
                return 1 + leftCount + rightCount;
            }
        }

        if (child == Child.RIGHT) {
            if (node.right == null) {
                return 0;
            } else {
                int leftCount = countNodes(node.right, Child.LEFT);
                int rightCount = countNodes(node.right, Child.RIGHT);
                return 1 + leftCount + rightCount;
            }
        }

        return 0;
    }

    /**
     * Builds an array of nodes in the specified subtree
     *
     * @param node  the parent node, not to be included in returned array
     * @param child the specified subtree
     * @return array of nodes
     */

    public TreeNode[] enumerateNodes(TreeNode node, Child child) {
        // TODO: Implement this
        int size = countNodes(node, child);
        TreeNode[] inOrder = new TreeNode[size];

        if (size == 0) {
            return new TreeNode[] {};
        }

        if (child == Child.LEFT && node.left != null) {
            return traverse(node.left, inOrder, 0);
        }

        if (child == Child.RIGHT && node.right != null) {
            return traverse(node.right, inOrder, 0);
        }

        return null;
    }

    public TreeNode[] traverse(TreeNode node, TreeNode[] res, int index) {
        // helper function to traverse the Tree in order
        if (node.left != null) {
            res = traverse(node.left, res, index);
        }

        int tap = countNodes(node, Child.LEFT);
        index = index + tap;
        res[index] = node;

        if (node.right != null) {
            res = traverse(node.right, res, index + 1);
        }

        return res;
    }

    /**
     * Builds a tree from the list of nodes
     * Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */
    public TreeNode buildTree(TreeNode[] nodeList) {
        // TODO: Implement this
        return buildHelper(nodeList, 0, nodeList.length - 1);
    }

    public TreeNode buildHelper(TreeNode[] nodeList, int low, int high) {
        // buildTree helper function
        if (low <= high) {
            int mid = (low + high) / 2;
            TreeNode root = new TreeNode(nodeList[mid].key);
            root.left = buildHelper(nodeList, low, mid - 1);
            root.right = buildHelper(nodeList, mid + 1, high);
            return root;
        }

        return null;
    }

    /**
    * Rebuilds the specified subtree of a node
    * 
    * @param node the part of the subtree to rebuild
    * @param child specifies which child is the root of the subtree to rebuild
    */
    public void rebuild(TreeNode node, Child child) {
        // Error checking: cannot rebuild null tree
        if (node == null) return;
        // First, retrieve a list of all the nodes of the subtree rooted at child
        TreeNode[] nodeList = enumerateNodes(node, child);
        // Then, build a new subtree from that list
        TreeNode newChild = buildTree(nodeList);
        // Finally, replace the specified child with the new subtree
        if (child == Child.LEFT) {
            node.left = newChild;
        } else if (child == Child.RIGHT) {
            node.right = newChild;
        }
    }

    /**
    * Inserts a key into the tree
    *
    * @param key the key to insert
    */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode node = root;

        while (true) {
            if (key <= node.key) {
                if (node.left == null) break;
                node = node.left;
            } else {
                if (node.right == null) break;
                node = node.right;
            }
        }

        if (key <= node.key) {
            node.left = new TreeNode(key);
        } else {
            node.right = new TreeNode(key);
        }
    }


    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree();
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        tree.rebuild(tree.root, Child.RIGHT);
    }
}
