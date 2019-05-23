package com.example.shopthree.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.shopthree.R;

public class ClearEdittext extends EditText {

    Drawable scrossx, nonescrossx;
    Boolean visiable = false;
    Drawable drawable;

    public ClearEdittext(Context context) {
        super(context);
        Create();
    }

    public ClearEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        Create();
    }

    public ClearEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Create();
    }

    public ClearEdittext(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Create();
    }

    private void Create()
    {
        scrossx = ContextCompat.getDrawable(getContext(), R.drawable.icon_clear_black).mutate();
        nonescrossx = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();

        Setting();
    }

    private void Setting()
    {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] arrDrawable = getCompoundDrawables();
        drawable = visiable ? scrossx : nonescrossx;
        setCompoundDrawablesWithIntrinsicBounds(arrDrawable[0], arrDrawable[1], drawable, arrDrawable[3]);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_DOWN == event.getAction() && event.getX() >= (getRight() - drawable.getBounds().width()))
        {
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter == 0 && start == 0)
        {
            visiable = false;
            Setting();
        }
        else
        {
            visiable = true;
            Setting();
        }

    }
}
