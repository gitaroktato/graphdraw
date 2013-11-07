package com.example.androidsandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private BinaryTree mTree;
    private RelativeLayout mLayout;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        createTree();
    }

    private void createTree() {
        mTree = new BinaryTree(10);
        mTree.left = new BinaryTree(3);
        mTree.right = new BinaryTree(2);
        mTree.left.left = new BinaryTree(5);
        mTree.left.right = new BinaryTree(7);
        mTree.left.right.left = new BinaryTree(4);
        mTree.left.right.right = new BinaryTree(9);
    }

    @Override
    public void onResume() {
        super.onResume();
        GameView view = new GameView(this, mTree);
        ViewGroup.LayoutParams params =  new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        mLayout.addView(view);
    }
}
