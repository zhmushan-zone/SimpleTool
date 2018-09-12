package io.github.zhmushan.simpletool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MotionEventCompat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;

public class MyCoordinatorLayout extends CoordinatorLayout {

    private int mTouchSlop;
    protected VelocityTracker mVelocityTracker;
    private float mLastMotionX;
    private float mLastMotionY;

    private float mInitialMotionY, mInitialMotionX;

    protected int mActivePointerId = INVALID_POINTER;
    private static final int INVALID_POINTER = -1;

    private boolean mIsUnableToDrag = false;

    private boolean mIsBeingDragged = false;
    boolean mScrollToEnd = false;
    private boolean mQuickReturn = false;


    public MyCoordinatorLayout(@NonNull Context context) {
        super(context);
    }

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();

        if (action == MotionEvent.ACTION_CANCEL
                || action == MotionEvent.ACTION_UP
                || (action != MotionEvent.ACTION_DOWN && mIsUnableToDrag)) {
            return false;
        }

        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                int index = MotionEventCompat.getActionIndex(ev);
                mActivePointerId = MotionEventCompat.getPointerId(ev, index);
                if (mActivePointerId == INVALID_POINTER)
                    break;
                mLastMotionX = mInitialMotionX = MotionEventCompat.getX(ev, index);
                mInitialMotionY = mLastMotionY = MotionEventCompat.getY(ev, index);

                mIsBeingDragged = false;
                mIsUnableToDrag = false;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mActivePointerId == INVALID_POINTER) {
                    break;
                }
                determineDrag(ev);
                break;
        }

        if (!mIsBeingDragged) {
            if (mVelocityTracker == null) {
                mVelocityTracker = VelocityTracker.obtain();
            }
            mVelocityTracker.addMovement(ev);
        }
        return mIsBeingDragged || mQuickReturn;

    }

    private int getPointerIndex(MotionEvent ev, int id) {
        int activePointerIndex = MotionEventCompat.findPointerIndex(ev, id);
        if (activePointerIndex == -1)
            mActivePointerId = INVALID_POINTER;
        return activePointerIndex;
    }

    private void determineDrag(MotionEvent ev) {
        final int activePointerId = mActivePointerId;
        final int pointerIndex = getPointerIndex(ev, activePointerId);
        if (activePointerId == INVALID_POINTER || pointerIndex == INVALID_POINTER)
            return;
        final float x = MotionEventCompat.getX(ev, pointerIndex);
        final float dx = x - mLastMotionX;
        final float xDiff = Math.abs(dx);
        final float y = MotionEventCompat.getY(ev, pointerIndex);
        final float dy = y - mLastMotionY;
        final float yDiff = Math.abs(dy);
        if (yDiff > mTouchSlop && yDiff > xDiff) {
            startDrag();
            mLastMotionX = x;
            mLastMotionY = y;
        } else if (xDiff > mTouchSlop) {
            mIsUnableToDrag = true;
        }
    }

    private void startDrag() {
        mIsBeingDragged = true;
        mQuickReturn = false;
        mScrollToEnd = false;
    }



}
