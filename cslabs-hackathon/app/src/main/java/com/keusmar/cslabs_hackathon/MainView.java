package com.keusmar.cslabs_hackathon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import com.keusmar.cslabs_hackathon.Models.Action;

import java.util.ArrayList;


public class MainView extends View
{
    Paint paint = null;
    private Integer rotation = 0;
    private Integer scrollingBg = 0;
    private ArrayList<Integer> animations = new ArrayList();
    private int position = 0;
    private Integer currentPosition = 0;
    private ArrayList<Action> actions;
    
    public MainView(Context context)
    {
        super(context);
        animations.add(R.drawable.b1);
        animations.add(R.drawable.b2);
        animations.add(R.drawable.b3);
        animations.add(R.drawable.b4);
        animations.add(R.drawable.b5);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        drawSky(canvas, x, y);
        drawCloud(canvas, x);

        drawHeartbar(canvas, x);
        drawGoldbar(canvas, x);
        drawEarthbar(canvas, x);

        drawDayLabel(canvas, x);
        drawPlanet(canvas, x, y);
        
        dawCharacter(canvas, x, y);
        drawAskBox(canvas, x, y);
//        drawChoiceBtns(canvas, x, y);
        Action currentAction = getCurrentAction();
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
//        canvas.drawText(currentAction.getContent(), 50, y - 500, paint);
    }



    private void drawDayLabel(Canvas canvas, int x) {
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        paint.setTextSize(80);
        canvas.save();
        canvas.rotate(15, x-300, 80);
        canvas.drawText("#Day 1", x-298, 82, paint);
        paint.setColor(Color.parseColor("#FFD723"));
        canvas.drawText("#Day 1", x-300, 80, paint);
        canvas.restore();
    }

    private void drawChoiceBtns(Canvas canvas, int x, int y) {
        Bitmap validate = BitmapFactory.decodeResource(getResources(), R.drawable.validate);
        canvas.drawBitmap(validate, 200, y-300, paint);
        Bitmap refuse = BitmapFactory.decodeResource(getResources(), R.drawable.refuse);
        canvas.drawBitmap(refuse, x-200-refuse.getWidth(), y-300, paint);
    }

    private void drawAskBox(Canvas canvas, int x, int y) {
        Bitmap askbox = BitmapFactory.decodeResource(getResources(), R.drawable.ask_box);
        float padding = (x - (askbox.getWidth()))/2;
        canvas.drawBitmap(askbox, padding, y - (askbox.getHeight() + 10), paint);
    }

    private void drawPlanet(Canvas canvas, int x, int y) {
        paint.setColor(Color.parseColor("#267F00"));
        canvas.drawCircle(x / 2, y, x, paint);
        drawTree(canvas, x, y);
    }

    private void dawCharacter(Canvas canvas, int x, int y) {
        Bitmap icon = BitmapFactory.decodeResource(getResources(), animations.get(position));
        canvas.drawBitmap(icon, (x/2) - icon.getWidth()/2, (y/2) - icon.getHeight() + 50, paint);
        if (position == animations.size()-1) {
            position = 0;
        }
    }

    private void drawTree(Canvas canvas, int x, int y) {
        Bitmap tree = BitmapFactory.decodeResource(getResources(), R.drawable.tree);
        canvas.save();
        canvas.rotate(-rotation, x/2, y);
        canvas.drawBitmap(tree, (x/2) - tree.getWidth()/2, (y/2) - tree.getHeight() + 55, paint);
        canvas.restore();
    }

    private void drawEarthbar(Canvas canvas, int x) {
        Bitmap earth = BitmapFactory.decodeResource(getResources(), R.drawable.gold);
        Bitmap resizedEarth = Bitmap.createScaledBitmap(
                earth, 50, 50, false);
        canvas.drawBitmap(resizedEarth, 40,300, paint);
        Bitmap bbar = BitmapFactory.decodeResource(getResources(), R.drawable.blue_bar);
        Bitmap resizedBBar = Bitmap.createScaledBitmap(
                bbar, x-200, 50, false);
        canvas.drawBitmap(resizedBBar, 100,300, paint);
    }

    private void drawGoldbar(Canvas canvas, int x) {
        Bitmap gold = BitmapFactory.decodeResource(getResources(), R.drawable.gold);
        Bitmap resizedGold = Bitmap.createScaledBitmap(
                gold, 50, 50, false);
        canvas.drawBitmap(resizedGold, 40,200, paint);
        Bitmap gbar = BitmapFactory.decodeResource(getResources(), R.drawable.green_bar);
        Bitmap resizedGBar = Bitmap.createScaledBitmap(
                gbar, x-200, 50, false);
        canvas.drawBitmap(resizedGBar, 100,200, paint);
    }

    private void drawHeartbar(Canvas canvas, int x) {
        Bitmap heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        Bitmap resizedHeart = Bitmap.createScaledBitmap(
                heart, 50, 50, false);
        canvas.drawBitmap(resizedHeart, 40,100, paint);
     
        Bitmap bar = BitmapFactory.decodeResource(getResources(), R.drawable.bar);
        Bitmap resizedBar = Bitmap.createScaledBitmap(
                bar, x-200, 50, false);
        canvas.drawBitmap(resizedBar, 100,100, paint);
    }

    private void drawCloud(Canvas canvas, int x) {
        Bitmap cloud = BitmapFactory.decodeResource(getResources(), R.drawable.cloud1);
        canvas.drawBitmap(cloud, x-scrollingBg,0, paint);
        if (x-scrollingBg == (-cloud.getWidth())){
            scrollingBg = 0;
        }
    }

    private void drawSky(Canvas canvas, int x, int y) {
        paint.setColor(Color.parseColor("#51B6FF"));
        canvas.drawRect(0, 0,x,y, paint);
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public Action getCurrentAction() {
        return this.actions.get(currentPosition);
    }

    public void refresh() {
        this.rotation+=2;
        scrollingBg++;
        if (scrollingBg % 2 == 0)
            position++;

        this.invalidate();
    }
}
