package com.mykins.linkin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.mykins.linkin.util.ConvertUtils;

/**
 * Grid
 * Created by Frank on 2018/11/2.
 */
//public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
//
//
//    private Drawable mDivider;
//    private int horizontalDividerWidth;//横向间距大小  此值大于0时取此值否则取 mDivider.getIntrinsicWidth()
//    private int verticalDividerHeight;//竖向间距大小   此值大于0时取此值否则取 mDivider.getIntrinsicHeight()
//    private boolean hasHeaderDivider;
//
//    public GridDividerItemDecoration(Context context) {
//        this(context, 0, 0);
//    }
//
//    /**
//     * @param context                context
//     * @param horizontalDividerWidth 横向间距大小
//     * @param verticalDividerHeight  竖向间距大小
//     */
//    public GridDividerItemDecoration(Context context, int horizontalDividerWidth, int verticalDividerHeight) {
//        ShapeDrawable drawable = new ShapeDrawable();
//        drawable.setIntrinsicWidth(ConvertUtils.dp2px(context, 7));
//        drawable.setIntrinsicHeight(ConvertUtils.dp2px(context, 10));
//        drawable.setAlpha(0);
//        mDivider = drawable;
//        this.horizontalDividerWidth = horizontalDividerWidth;
//        this.verticalDividerHeight = verticalDividerHeight;
//    }
//
//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        drawHorizontal(c, parent);
//        drawVertical(c, parent);
//    }
//
//    private int getSpanCount(RecyclerView parent) {
//        // 列数
//        int spanCount = -1;
//        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager) {
//            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
//        }
//        return spanCount;
//    }
//
//    public void drawHorizontal(Canvas c, RecyclerView parent) {
//        int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//            final int left = child.getLeft() - params.leftMargin;
//            final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
//            final int top = child.getBottom() + params.bottomMargin;
//            final int bottom = top + mDivider.getIntrinsicHeight();
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(c);
//        }
//    }
//
//    public void drawVertical(Canvas c, RecyclerView parent) {
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//            final int top = child.getTop() - params.topMargin;
//            final int bottom = child.getBottom() + params.bottomMargin;
//            final int left = child.getRight() + params.rightMargin;
//            final int right = left + mDivider.getIntrinsicWidth();
//
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(c);
//        }
//    }
//
//    private boolean isFirstRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
//        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager) {
//            if (pos < spanCount)
//                return true;
//        }
//        return false;
//    }
//
//    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount) {
//        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager) {
//            if ((pos + 1) % spanCount == 0) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
//        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager) {
//            childCount = childCount - childCount % spanCount;
//            if (pos >= childCount)
//                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//
//        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
//        int spanCount = getSpanCount(parent);
//        int childCount = parent.getAdapter().getItemCount();
//        int width = this.horizontalDividerWidth > 0 ? this.horizontalDividerWidth : mDivider.getIntrinsicWidth();
//        int height = this.verticalDividerHeight > 0 ? this.verticalDividerHeight : mDivider.getIntrinsicHeight();
//
//        if (isFirstRaw(parent, itemPosition, spanCount, childCount) && hasHeaderDivider) {
//            if (isLastColumn(parent, itemPosition, spanCount, childCount)) {
//                outRect.set(0, height, 0, height);
//            } else {
//                outRect.set(0, height, width, height);
//            }
//        } else if (isLastRaw(parent, itemPosition, spanCount, childCount)) {// 如果是最后一行，则不需要绘制底部
//            outRect.set(0, 0, width, 0);
//        } else if (isLastColumn(parent, itemPosition, spanCount, childCount)) {// 如果是最后一列，则不需要绘制右边
//            outRect.set(0, 0, 0, height);
//        } else {
//            outRect.set(0, 0, width, height);
//        }
//    }
//
//    public void setHorizontalDividerWidth(int width) {
//        this.horizontalDividerWidth = width;
//    }
//
//    public void setHorizontalDividerHeight(int height) {
//        this.verticalDividerHeight = height;
//    }
//
//    public void hasHeaderDivider(boolean hasHeaderDivider) {
//        this.hasHeaderDivider = hasHeaderDivider;
//    }

/**
 * Gridview
 * 四周都是分割线的ItemDecoration,因为网上都是RecyclerView的边框部分没有分割线,主要代码在于用isfirstRow去判断
 * Created by _SOLID
 * Date:2016/10/8
 * Time:16:50
 * Desc:
 */


public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private int mDividerWidth;

    public GridDividerItemDecoration(int height, @ColorInt int color) {
        mDividerWidth = height;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();

        boolean isLastRow = isLastRow(parent, itemPosition, spanCount, childCount);
        boolean isfirstRow = isfirstRow(parent, itemPosition, spanCount, childCount);

        int top;
        int left;
        int right;
        int bottom;
        int eachWidth = (spanCount - 1) * mDividerWidth / spanCount;
        int dl = mDividerWidth - eachWidth;

        left = itemPosition % spanCount * dl;
        right = eachWidth - left;
        bottom = mDividerWidth;
        //Log.e("zzz", "itemPosition:" + itemPosition + " |left:" + left + " right:" + right + " bottom:" + bottom);
//        if (isLastRow) {
//            bottom = 0;
//        }
        if (isfirstRow) {
            top = (spanCount - 1) * mDividerWidth / spanCount;
        } else {
            top = 0;
        }
        outRect.set(left, top, right, bottom);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        draw(c, parent);
    }

    //绘制横向 item 分割线
    private void draw(Canvas canvas, RecyclerView parent) {
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            //画水平分隔线
            int left = child.getLeft();
            int right = child.getRight();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDividerWidth;
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
            //画垂直分割线
            top = child.getTop();
            bottom = child.getBottom() + mDividerWidth;
            left = child.getRight() + layoutParams.rightMargin;
            right = left + mDividerWidth;
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount,
                                 int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0) {// 如果是最后一列，则不需要绘制右边
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRow(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            // childCount = childCount - childCount % spanCount;
            int lines = childCount % spanCount == 0 ? childCount / spanCount : childCount / spanCount + 1;
            return lines == pos / spanCount + 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isfirstRow(RecyclerView parent, int pos, int spanCount,
                               int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            // childCount = childCount - childCount % spanCount;
            int lines = childCount % spanCount == 0 ? childCount / spanCount : childCount / spanCount + 1;
            //如是第一行则返回true
            if ((pos / spanCount + 1) == 1) {
                return true;
            } else {
                return false;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

}
