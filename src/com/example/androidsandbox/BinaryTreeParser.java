package com.example.androidsandbox;

/**
 * @author oresztesz
 *         Date: 11/7/13
 *         Time: 8:55 AM
 */
public class BinaryTreeParser {

    public BinaryTree mRoot;
    public int mDepth;

    public BinaryTreeParser(BinaryTree root) {
        mRoot = root;
    }

    public void parse() {
        mDepth = 0;
        parseFromNode(mRoot, 1);
    }

    private void parseFromNode(BinaryTree root, int currentDepth) {
        if (root == null) {
            return;
        }
        if (mDepth < currentDepth) {
            mDepth = currentDepth;
        }
        parseFromNode(root.left, currentDepth+1);
        parseFromNode(root.right, currentDepth+1);
    }

    public int maxElementsAtLevel(int level) {
        return 1 << (level - 1);
    }

}
