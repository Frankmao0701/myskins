package com.mykins.linkin.app.kins;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.util.ResUtils;


public class FastPositionIndicator extends View {

//    public static String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H",
//            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
//            "V", "W", "X", "Y", "Z", "#"};
    private String[] mLetters;

    public void setLetters(String[] letters){
        this.mLetters = letters;
        invalidate();
    }

    private Paint paint = new Paint();
    /**
     * 用于标记哪个位置被选中
     */
    private int choose = -1;
    //该TextView是左边显示的对话框
    private TextView mTextDialog;

    int paddingTop = ConvertUtils.dp2px(getResources(), 128);
    int paddingBottom = ConvertUtils.dp2px(getResources(), 12);

    public void setTextDialog(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    private OnTouchingLetterChangedListener listener;

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener listener) {
        this.listener = listener;
    }

    public FastPositionIndicator(Context context) {
        super(context);
    }

    public FastPositionIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FastPositionIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mLetters == null)return;

        // 获取该自定义View的宽度和高度
        int width = getWidth();
        int height = getHeight() - paddingTop - paddingBottom;

        // 单个字母的高度
        int singleHeight = height / mLetters.length;

        for (int i = 0; i < mLetters.length; i++) {
            paint.setColor(ResUtils.color(R.color.color_787878));
            paint.setTypeface(Typeface.DEFAULT);
            paint.setAntiAlias(true);
            paint.setTextSize(ConvertUtils.sp2px(getResources(), 14));

            // 如果选中的话,改变样式和颜色
//            if (i == choose) {
//                paint.setColor(ResUtils.color(R.color.color_3399ff));
//                paint.setFakeBoldText(true);
//            }

            // 首先确定每个字母的横坐标的位置，横坐标：该自定义View的一半 -（减去） 单个字母宽度的一半
            float xPos = width / 2 - paint.measureText(mLetters[i]) / 2;
            float yPos = singleHeight * (i + 1) + paddingTop;

            canvas.drawText(mLetters[i].toUpperCase(), xPos, yPos, paint);

            // 重置画笔
            paint.reset();
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(mLetters == null)return true;
        float y = event.getY();
        int viewHeight = getHeight();


        int oldChoose = choose;
        // 根据y坐标确定当前哪个字母被选中
        int h = viewHeight - paddingTop - paddingBottom;
        int pos = (int) ((y-paddingTop) / h * mLetters.length);

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                // 当手指抬起时,设置View的背景为白色
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                // 重置为初始状态
                choose = -1;
                // 让View重绘
                invalidate();

                // 将对话框设置为不可见
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }

                break;
            default:
                if(y<paddingTop ||y>(viewHeight-paddingBottom))return true;

                // 设置右边字母View的背景色
                setBackgroundColor(ResUtils.color(R.color.color_30000000));
                if (pos != oldChoose) {
                    // 如果之前选中的和当前的不一样，需要重绘
                    if (pos >= 0 && pos < mLetters.length) {
                        if (listener != null) {
                            //当前字母被选中，需要让ListView去更新显示的位置
                            listener.onTouchingLetterChanged(mLetters[pos]);
                        }
                        //在左边显示选中的字母，该字母放在TextView上，相当于一个dialog
                        if (mTextDialog != null) {
                            mTextDialog.setText(mLetters[pos].toUpperCase()); //让对话框显示响应的字母
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = pos;  //当前位置为选中位置
                    }
                }
                break;
        }

        return true;
    }

    /**
     * 该回调接口用于通知ListView更新状态
     */
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

}