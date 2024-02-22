import java.util.zip.CheckedInputStream;

/**
 * ScapeGoat Tree class
 * <p>
 * This class contains some basic code for implementing a ScapeGoat tree. This version does not include any of the
 * functionality for choosing which node to scapegoat. It includes only code for inserting a node, and the code for
 * rebuilding a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    /**
     * TreeNode class.
     * <p>
     * This class holds the data for a node in a binary tree.
     * <p>
     * Note: we have made things public here to facilitate problem set grading/testing. In general, making everything
     * public like this is a bad idea!
     */
    public static class TreeNode {
        int key;
        double weight = 1;
        public TreeNode left = null;
        public TreeNode right = null;

        TreeNode(int k) {
            key = k;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the specified subtree.
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
     * Builds an array of nodes in the specified subtree.
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
            traverse(node.left, inOrder, 0);
        }
        if (child == Child.RIGHT && node.right != null) {
            traverse(node.right, inOrder, 0);
        }
        return inOrder;
    }
    public int traverse(TreeNode node, TreeNode[] res, int index) {
        // helper function to traverse the Tree in order
        if (node == null) {
            return index;
        }
        index = traverse(node.left, res, index);
        res[index] = node;
        index = traverse(node.right, res, index + 1);
        return index ;
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
     * Determines if a node is balanced. If the node is balanced, this should return true. Otherwise, it should return
     * false. A node is unbalanced if either of its children has weight greater than 2/3 of its weight.
     *
     * @param node a node to check balance on
     * @return true if the node is balanced, false otherwise
     */
    public boolean checkBalance(TreeNode node) {
        // TODO: Implement this
        if (node == null) {
            return true;
        }
        double leftWeight = (node.left == null) ? 0 : node.left.weight;
        double rightWeight = (node.right == null) ? 0 : node.right.weight;
        double nodeWeight = node.weight;
        boolean leftCheck = (nodeWeight * 2.0 / 3.0) < leftWeight;
        boolean rightCheck = (nodeWeight * 2.0 / 3.0) < rightWeight;

        if (leftCheck || rightCheck) {
            return false;
        }
        return true;
    }

    /**
     * Rebuilds the specified subtree of a node.
     *
     * @param node  the part of the subtree to rebuild
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
        fixWeights(node, child);
    }

    public void fixWeights(TreeNode u, Child child) {
        if (u == null) {
            return ;
        } else if (child == Child.LEFT) {
            fixWeightsAll(u.left);
        } else if (child == Child.RIGHT) {
            fixWeightsAll(u.right);
        }
    }

    public void fixWeightsAll(TreeNode u) {
        if (u.left == null && u.right == null) {
            u.weight = 1;
        }
        if (u.left != null && u.right != null) {
            fixWeightsAll(u.left);
            fixWeightsAll(u.right);
            u.weight = u.left.weight + u.right.weight + 1;
        }
        if (u.left != null) {
            fixWeightsAll(u.left);
            u.weight = u.left.weight + 1;
        }
        if (u.right != null) {
            fixWeightsAll(u.right);
            u.weight = u.right.weight + 1;
        }
    }

    /**
     * Inserts a key into the tree.
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
            node.weight++;
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

        shiftTree(key, root);

    }

    public void shiftTree(int key, TreeNode node) {

        if (node == null) {
            return ;
        }

        if (key <= node.key) {
            if (checkBalance(node.left)) {
                shiftTree(key, node.left);
            }
            else {
                rebuild(node, Child.LEFT);
            }
        }

        if (key > node.key) {
            if (checkBalance(node.right)) {
                shiftTree(key, node.right);
            }
            else {
                rebuild(node, Child.RIGHT);
            }
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
