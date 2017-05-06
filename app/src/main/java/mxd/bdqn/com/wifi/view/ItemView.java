package mxd.bdqn.com.wifi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mxd.bdqn.com.wifi.R;


/**
 * Created by Administrator on 2017/3/13.
 */

public class ItemView extends RelativeLayout {
    private TextView leftText, rightText;
    private FrameLayout leftContainer, rightContainer;
    private View driver;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ImageView lImage, rImage;
        //加载当前组合显示布局文件
        View.inflate(context, R.layout.con_view_myself, this);
        //加载自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        //获取所有取值
        boolean b = a.getBoolean(R.styleable.ItemView_is_driver, false);
        String leftContent = a.getString(R.styleable.ItemView_left_text);
        String rightContent = a.getString(R.styleable.ItemView_right_text);
        int leftColor = a.getColor(R.styleable.ItemView_left_text_color, 0xff000000);
        int rightColor = a.getColor(R.styleable.ItemView_right_text_color, 0xff8f8f8f);
        float leftSize = a.getDimension(R.styleable.ItemView_left_text_size, 16);
        float rightSize = a.getDimension(R.styleable.ItemView_right_text_size, 16);
        Drawable lSrc = a.getDrawable(R.styleable.ItemView_left_img_src);
        Drawable rSrc = a.getDrawable(R.styleable.ItemView_right_img_src);
        //回收资源

        a.recycle();
        //将获取的属性与控件关联

        leftText = (TextView) findViewById(R.id.tv_comm_left_title);
        rightText = (TextView) findViewById(R.id.tv_comm_right_desc);
        leftContainer = (FrameLayout) findViewById(R.id.layout_left_container);
        rightContainer = (FrameLayout) findViewById(R.id.layout_right_container);
        driver = findViewById(R.id.view_driver);

        driver.setVisibility(b ? VISIBLE : GONE);

        leftText.setText(leftContent);
        leftText.setTextSize(leftSize);
        leftText.setTextColor(leftColor);

        rightText.setText(rightContent);
        rightText.setTextSize(rightSize);
        rightText.setTextColor(rightColor);

        if (lSrc != null) {
            lImage = new ImageView(context);
            lImage.setImageDrawable(lSrc);
            // 添加到左边容器显示
            leftContainer.addView(lImage);
        }
        if (rSrc != null) {
            rImage = new ImageView(context);
            rImage.setImageDrawable(rSrc);
            // 添加到右边容器显示
            rightContainer.addView(rImage);
        }

    }

    /**
     * 设置左边容器显示的内容
     *
     * @param v
     */
    public void setLeftView(View v) {
        cleanLeftView();
        leftContainer.addView(v);
    }

    /**
     * 清除左边容器控件中所有的子控件
     */
    public void cleanLeftView() {
        if (leftContainer != null && leftContainer.getChildCount() > 0) {
            leftContainer.removeAllViews();
        }
    }

    /**
     * 设置右边容器显示的内容
     *
     * @param v
     */
    public void setRightView(View v) {
        cleanRightView();
        rightContainer.addView(v);
    }

    /**
     * 清除右边容器控件中所有的子控件
     */
    public void cleanRightView() {
        if (rightContainer != null && rightContainer.getChildCount() > 0) {
            rightContainer.removeAllViews();
        }
    }

    /**
     * 设置是否显示分割线
     *
     * @param visibility
     */
    public void setDriver(boolean visibility) {
        driver.setVisibility(visibility ? VISIBLE : GONE);
    }

    /**
     * 设置左边文字
     *
     * @param text
     */
    public void setLeftText(String text) {
        leftText.setText(text);
    }

    /**
     * 设置右边文字
     *
     * @param text
     */
    public void setRightText(String text) {
        rightText.setText(text);
    }
}
