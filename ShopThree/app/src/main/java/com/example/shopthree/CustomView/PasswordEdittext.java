package com.example.shopthree.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.shopthree.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordEdittext extends EditText {

    Drawable eye, eyeStrike;
    Boolean visible = false;
    Boolean useTrike = false;
    Boolean useValidate = false;
    Drawable drawable;
    int ALPHA = (int) (255 * .75f);
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern pattern;
    Matcher matcher;

    public PasswordEdittext(Context context) {
        super(context);
        Create(null);
    }

    public PasswordEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        Create(attrs);
    }

    public PasswordEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Create(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEdittext(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Create(attrs);
    }

    private void Create(AttributeSet attrs)
    {
        this.pattern = Pattern.compile(MATCHER_PATTERN);
        if(attrs != null)
        {
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEdittext,0,0);
            this.useTrike = array.getBoolean(R.styleable.PasswordEdittext_useStrike, false);
            this.useValidate = array.getBoolean(R.styleable.PasswordEdittext_useValidate, false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.icon_visibility_black).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.icon_visibility_off_black).mutate();

        if(this.useValidate)
        {
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus)
                    {
                        String newuserpass = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) getParent().getParent();
                        matcher = pattern.matcher(newuserpass);
                        if(!matcher.matches())
                        {
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu không hợp lệ");
                        }
                        else
                        {
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }
                    }
                }
            });
        }
        Setting();
    }

    private void Setting()
    {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] arrDrawable = getCompoundDrawables();
        drawable = useTrike && ! visible ? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(arrDrawable[0], arrDrawable[1], drawable, arrDrawable[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) )
        {
            visible = !visible;
            Setting();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
