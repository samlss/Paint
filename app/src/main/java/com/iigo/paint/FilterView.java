package com.iigo.paint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/21 0021 17:54
 */
public class FilterView extends View {
    private int imageId = -1;
    private Paint paint;
    private Bitmap bitmap;
    private ColorMatrix cColorMatrix;

    public enum TYPE{
        NORMAL, //正常图片
        NEGATIVE, //底片
        RETRO, //复古
        FAIR, //美颜
        BAW,//黑白(black and white)
        CHANGE, //发色，改变颜色
        CUSTOM,//自定义
    }

    private TYPE currentType = TYPE.NORMAL;

    public FilterView(Context context, int imageId) {
        super(context);

        this.imageId = imageId;
        init(null);
    }

    public FilterView(Context context) {
        super(context);

        init(null);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }


    public FilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    @SuppressLint("NewApi")
    public FilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (attrs == null){
            return;
        }

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FilterView);

        int count = a.getIndexCount();
        for (int i = 0; i < count; i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.FilterView_img_id:
                    imageId = a.getResourceId(attr, 0);
                    break;
            }
        }
        a.recycle();

        if (imageId == -1){
            return;
        }

        try{
            bitmap = BitmapFactory.decodeResource(getResources(), imageId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (bitmap == null){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        //根据图片大小设置当前view的大小
        super.onMeasure(MeasureSpec.makeMeasureSpec(bitmap.getWidth(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(bitmap.getHeight(), MeasureSpec.EXACTLY));

        //若要实现图片的自适应view的大小，可自行实现..
        //...
        //
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap == null){
            return;
        }

        setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        paint.reset();

        ColorMatrix colorMatrix = null;
        RectF rectF = new RectF(0,0, bitmap.getWidth(), bitmap.getHeight());
        paint.reset();

        switch (currentType){
            case NORMAL:
                break;

            case NEGATIVE:
                colorMatrix = new ColorMatrix(new float[]{
                        -1, 0,0,0,255,
                        0,-1,0,0,255,
                        0,0,-1,0,255,
                        0,0,0,1,0,
                });
                break;

            case RETRO:
                colorMatrix = new ColorMatrix(new float[]{
                        1/2f,1/2f,1/2f,0,0,
                        1/3f, 1/3f,1/3f,0,0,
                        1/4f,1/4f,1/4f,0,0,
                        0,0,0,1,0,
                });
                break;

            case FAIR:
                colorMatrix = new ColorMatrix(new float[]{
                        1.25f, 0,0,0,0,
                        0,1.25f,0,0,0,
                        0,0,1.25f,0,0,
                        0,0,0,1.25f,0,
                });
                break;

            case BAW:
                colorMatrix = new ColorMatrix(new float[]{
                        0.213f, 0.715f,0.072f,0,0,
                        0.213f, 0.715f,0.072f,0,0,
                        0.213f, 0.715f,0.072f,0,0,
                        0,0,0,1,0,
                });
                break;

            case CHANGE:
                colorMatrix = new ColorMatrix(new float[]{
                        1, 0, 0, 0, 0,
                        0, 0, 1, 0, 0,
                        0, 1, 0, 0, 0,
                        0, 0, 0, 1, 0,
                });
                break;

            case CUSTOM:
                colorMatrix = cColorMatrix;
                break;

                default: break;
        }

        if (colorMatrix != null){
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        }

        canvas.drawBitmap(bitmap,null, rectF, paint);
    }

    private void performDraw(){
        if (bitmap == null){
            return;
        }

        invalidate();
    }

    /**
     * 显示正常图片
     * */
    public void drawNormal(){
        currentType = TYPE.NORMAL;
        performDraw();
    }

    /**
     * 显示底片效果
     * */
    public void drawNegative(){
        currentType = TYPE.NEGATIVE;
        performDraw();
    }

    /**
     * 显示复古效果
     * */
    public void drawRetro(){
        currentType = TYPE.RETRO;
        performDraw();
    }

    /**
     * 显示美颜效果
     * */
    public void drawFair(){
        currentType = TYPE.FAIR;
        performDraw();
    }

    /**
     * 显示黑白效果
     * */
    public void drawBlackAndWhite(){
        currentType = TYPE.BAW;
        performDraw();
    }

    /**
     * 显示发色效果，这里为红色和绿色交换
     * */
    public void drawChange(){
        currentType = TYPE.CHANGE;
        performDraw();
    }

    /**
     * 显示自定义效果
     *
     * @param colorMatrix 指定的滤镜效果
     * */
    public void drawCustom(ColorMatrix colorMatrix){
        currentType = TYPE.CUSTOM;
        cColorMatrix = colorMatrix;
        performDraw();
    }
}
