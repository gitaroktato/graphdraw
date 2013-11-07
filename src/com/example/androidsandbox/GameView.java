package com.example.androidsandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author oresztesz
 *         Date: 11/7/13
 *         Time: 7:47 AM
 */
public class GameView extends View {


    private final BinaryTree mTree;
    private final BinaryTreeParser mParser;
    private final Paint mNodePaint;
    private final Paint mLinePaint;
    private static final float MARGIN = 5f;
    private static final float RADIUS = 20f;
    private static final String LOG_TAG = "GameView";
    private final Paint mValuePaint;
    private float mHeightStep;
    private Canvas mCanvas;

    public GameView(Context context, BinaryTree tree) {
        super(context);
        // Business data
        mTree = tree;
        mParser = new BinaryTreeParser(tree);
        mParser.parse();
        // Graphic data
        mNodePaint = new Paint();
        mNodePaint.setColor(Color.rgb(0, 255, 255));
        mNodePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.rgb(255, 255, 255));
        mLinePaint.setStyle(Paint.Style.STROKE);

        mValuePaint = new Paint();
        mValuePaint.setColor(Color.rgb(0, 0, 0));
        mValuePaint.setStyle(Paint.Style.STROKE);
        mValuePaint.setTextSize(RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
        mCanvas = canvas;
        Log.d(LOG_TAG, "Depth: " + mParser.mDepth);
        // Distance between nodes can be calculated simply from depth;
        mHeightStep = getHeightWithoutMargin() / (mParser.mDepth + 1);
        Log.d(LOG_TAG, "mHeightStep: " + mHeightStep);
        if (mTree == null) {
            return;
        }
        drawLevel(mTree, 1, MARGIN + getWidthStepAtLevel(1));
    }

    private float getHeightWithoutMargin() {
        return getHeight() - MARGIN * 2;
    }

    private float getWidthWithoutMargin() {
        return getWidth() - MARGIN * 2;
    }

    private float getWidthStepAtLevel(int atLevel) {
        return getWidthWithoutMargin() / (mParser.maxElementsAtLevel(atLevel) + 1);
    }

    private void drawLevel(BinaryTree root, int atLevel, float px) {
        Log.d(getClass().getSimpleName(), "Level: " + atLevel);
        float widthStep = getWidthStepAtLevel(atLevel);
        Log.d(getClass().getSimpleName(), "widthStep: " + widthStep);
        float py = MARGIN + mHeightStep * atLevel;
        float nexty = MARGIN + mHeightStep * (atLevel + 1);
        if (root.left != null) {
            float leftx = px - getWidthStepAtLevel(atLevel + 1) / 2;
            mCanvas.drawLine(px, py, leftx, nexty, mLinePaint);
            drawLevel(root.left, atLevel + 1, leftx);
        }
        if (root.right != null) {
            float rightx = px + getWidthStepAtLevel(atLevel + 1) / 2;
            mCanvas.drawLine(px, py, rightx, nexty, mLinePaint);
            drawLevel(root.right, atLevel + 1, rightx);
        }
        Log.d(getClass().getSimpleName(), "node at: (" + px + ", " + py + ")");
        mCanvas.drawCircle(px, py, RADIUS, mNodePaint);
        mCanvas.drawText(Integer.toString(root.value), px - RADIUS/2, py + RADIUS/2, mValuePaint);
    }
}
