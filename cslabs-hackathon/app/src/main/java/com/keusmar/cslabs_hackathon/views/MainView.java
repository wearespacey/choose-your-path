package com.keusmar.cslabs_hackathon.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import com.keusmar.cslabs_hackathon.Activities.GameOver;
import com.keusmar.cslabs_hackathon.Models.CategorieEnum;
import com.keusmar.cslabs_hackathon.Models.Character.CharacterColor;
import com.keusmar.cslabs_hackathon.Models.Action;
import com.keusmar.cslabs_hackathon.Models.Impact;
import com.keusmar.cslabs_hackathon.R;

import java.util.ArrayList;
import java.util.Random;


public class MainView extends View
{
    public final float MAX_STAT = 100;
    Paint paint = null;
    private Integer rotation = 0;
    private Integer scrollingBg = 0;
    private ArrayList<Integer> animations = new ArrayList();
    private int position = 0;
    private Integer currentPosition = 0;
    private ArrayList<Action> actions;
    private float ecologyStatus = 100;
    private float ecologyRatio = 100;
    private float economyStatus = 100;
    private float comfortStatus = 100;
    private float temperatureStatus = 50;
    private Integer day_number = 1;
    private int alphaValue = 0;
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();;
    private ArrayList<Bitmap> deadbitmaps = new ArrayList<>();;

    ArrayList<Integer> forest = new ArrayList<>();
    ArrayList<Integer> deadforest = new ArrayList<>();

    public MainView(Context context, CharacterColor color)
    {
        super(context);

        forest.add(R.drawable.tree);
        forest.add(R.drawable.tree2);
        forest.add(R.drawable.grass2);
        forest.add(R.drawable.grass4);
        forest.add(R.drawable.grass6);

        deadforest.add(R.drawable.grass1);
        deadforest.add(R.drawable.grass3);
        deadforest.add(R.drawable.tree01);
        deadforest.add(R.drawable.tree29);
        deadforest.add(R.drawable.tree30);

        for (int i = 0; i < 15; i++) {
            Random rand = new Random();
            Integer nb = rand.nextInt(forest.size()-1 - 0 + 1) + 0;
            Bitmap item = BitmapFactory.decodeResource(getResources(), forest.get(nb));
            Bitmap item2 = BitmapFactory.decodeResource(getResources(), deadforest.get(nb));
            bitmaps.add(item);
            deadbitmaps.add(item2);
        }

        switch (color){
            case ALIEN_BEIGE:
                animations.add(R.drawable.b1);
                animations.add(R.drawable.b2);
                animations.add(R.drawable.b3);
                animations.add(R.drawable.b4);
                animations.add(R.drawable.b5);
            break;
            case ALIEN_BLUE:
                animations.add(R.drawable.bu1);
                animations.add(R.drawable.bu2);
                animations.add(R.drawable.bu3);
                animations.add(R.drawable.bu4);
                animations.add(R.drawable.bu5);
            break;
            case ALIEN_PINK:
                animations.add(R.drawable.r1);
                animations.add(R.drawable.r2);
                animations.add(R.drawable.r3);
                animations.add(R.drawable.r4);
                animations.add(R.drawable.r5);
            break;
            case ALIEN_GREEN:
                animations.add(R.drawable.g1);
                animations.add(R.drawable.g2);
                animations.add(R.drawable.g3);
                animations.add(R.drawable.g4);
                animations.add(R.drawable.g5);
            break;
            case ALIEN_YELLOW:
                animations.add(R.drawable.y1);
                animations.add(R.drawable.y2);
                animations.add(R.drawable.y3);
                animations.add(R.drawable.y4);
                animations.add(R.drawable.y5);
            break;
        }

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
        drawThermometer(canvas, x, y);
        Action currentAction = getCurrentAction();
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
    }



    private void drawDayLabel(Canvas canvas, int x) {
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        paint.setTextSize(80);
        canvas.save();
        canvas.rotate(15, x-300, 80);
        canvas.drawText("#Day " + day_number, x-298, 82, paint);
        paint.setColor(Color.parseColor("#FFD723"));
        canvas.drawText("#Day " + day_number, x-300, 80, paint);
        canvas.restore();
    }

    public void incrementDayNumber(){day_number++;}

    private void drawThermometer(Canvas canvas, int x, int y) {
        Bitmap thermometer = BitmapFactory.decodeResource(getResources(), R.drawable.thermometre);
        canvas.drawBitmap(thermometer, x-thermometer.getWidth()-30, 450, paint);
        Bitmap barRed = BitmapFactory.decodeResource(getResources(), R.drawable.barred);
        temperatureStatus = (float) ((100 - ecologyStatus)*2);
        Bitmap resizedBarred = Bitmap.createScaledBitmap(
                barRed, 50, (int)(50+temperatureStatus), false);
        canvas.drawBitmap(resizedBarred, x-thermometer.getWidth()-5, 326+thermometer.getHeight()-((int)(temperatureStatus)), paint);
    }

    private void drawChoiceBtns(Canvas canvas, int x, int y) {
        Bitmap validate = BitmapFactory.decodeResource(getResources(), R.drawable.validate);
        canvas.drawBitmap(validate, 200, y-300, paint);
        Bitmap refuse = BitmapFactory.decodeResource(getResources(), R.drawable.refuse);
        canvas.drawBitmap(refuse, x-100-refuse.getWidth(), y-300, paint);
    }

    private void drawAskBox(Canvas canvas, int x, int y) {
        Bitmap askbox = BitmapFactory.decodeResource(getResources(), R.drawable.ask_box);
        float padding = (x - (askbox.getWidth()))/2;
        canvas.drawBitmap(askbox, padding, y - (askbox.getHeight() + 10), paint);
        Bitmap background;
        Action action = getCurrentAction();
        switch (getCurrentAction().getCategory()) {
            case BIODIVERSITY:
                background = BitmapFactory.decodeResource(getResources(), R.drawable.bee);
                canvas.drawBitmap(background, padding, y - (askbox.getHeight() + 10), paint);
                break;
            case FOOD:
                background = BitmapFactory.decodeResource(getResources(), R.drawable.pineapple);
                canvas.drawBitmap(background, padding, y - (askbox.getHeight() + 10), paint);
                break;
            case TRANSPORT:
                background = BitmapFactory.decodeResource(getResources(), R.drawable.car);
                canvas.drawBitmap(background, padding, y - (askbox.getHeight() + 10), paint);
                break;
            case CLOTHE:
                background = BitmapFactory.decodeResource(getResources(), R.drawable.shirt);
                canvas.drawBitmap(background, padding, y - (askbox.getHeight() + 10), paint);
                break;
            default:
                break;
        }
    }

    private void drawPlanet(Canvas canvas, int x, int y) {
        paint.setColor(Color.parseColor("#267F00"));
        canvas.drawCircle(x / 2, y, x, paint);
        paint.setColor(Color.parseColor("#5B5546"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(this.getAlphaValue());
        canvas.drawCircle(x / 2, y, x, paint);
        paint.setAlpha(255);
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
        if (ecologyStatus > 50) {
            for (int i = 0; i < bitmaps.size(); i++) {
                canvas.save();
                Bitmap bitmap = bitmaps.get(i);
                canvas.rotate(-rotation -(45*i) , x/2, y);
                canvas.drawBitmap(bitmap, (x/2) - bitmap.getWidth()/2, (y/2) - bitmap.getHeight() + 55, paint);
                canvas.restore();
            }
        } else {
            for (int i = 0; i < bitmaps.size(); i++) {
                canvas.save();
                Bitmap bitmap = deadbitmaps.get(i);
                canvas.rotate(-rotation -(45*i) , x/2, y);
                canvas.drawBitmap(bitmap, (x/2) - bitmap.getWidth()/2, (y/2) - bitmap.getHeight() + 55, paint);
                canvas.restore();
            }
        }

    }

    private void drawEarthbar(Canvas canvas, int x) {
        Bitmap earth = BitmapFactory.decodeResource(getResources(), R.drawable.aarth);
        Bitmap resizedEarth = Bitmap.createScaledBitmap(
                earth, 50, 50, false);
        canvas.drawBitmap(resizedEarth, 40,300, paint);

        int w = (int)((getWidth()-200)*(ecologyStatus/100));
        Bitmap bbar = BitmapFactory.decodeResource(getResources(), R.drawable.blue_bar);
        Bitmap resizedBBar = Bitmap.createScaledBitmap(bbar, w > 0 ? w:1, 50, false);
        canvas.drawBitmap(resizedBBar, 100,300, paint);
    }

    private void drawGoldbar(Canvas canvas, int deviceWidth) {
        Bitmap gold = BitmapFactory.decodeResource(getResources(), R.drawable.gold);
        Bitmap resizedGold = Bitmap.createScaledBitmap(
                gold, 50, 50, false);
        canvas.drawBitmap(resizedGold, 40,200, paint);

        int w = (int)((deviceWidth-200)*(economyStatus/100));
        Bitmap gbar = BitmapFactory.decodeResource(getResources(), R.drawable.green_bar);
        Bitmap resizedGBar = Bitmap.createScaledBitmap(gbar, w > 0 ? w:1, 50, false);
        canvas.drawBitmap(resizedGBar, 100,200, paint);
    }

    private void drawHeartbar(Canvas canvas, int deviceWidth) {
        Bitmap heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        Bitmap resizedHeart = Bitmap.createScaledBitmap(
                heart, 50, 50, false);
        canvas.drawBitmap(resizedHeart, 40,100, paint);

        int w = (int)((deviceWidth-200)*(comfortStatus/100));
        Bitmap bar = BitmapFactory.decodeResource(getResources(), R.drawable.bar);
        Bitmap resizedBar = Bitmap.createScaledBitmap(bar, w > 0 ? w:1, 50, false);
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
        paint.setColor(Color.parseColor("#606060"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(this.getAlphaValue());
        canvas.drawRect(0, 0,x,y, paint);
        paint.setAlpha(255);
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public Action getCurrentAction() {
        return this.actions.get(currentPosition);
    }

    public void refresh() {
        this.rotation+=2;
        scrollingBg+=4;
        if (scrollingBg % 2 == 0)
            position++;

        this.invalidate();
    }

    public void updateStatus(Action action, boolean decision) {

        for (Impact impact : action.getImpacts()) {
            float stat = 0;
            float points = decision ? impact.getPointsYes() : impact.getPointsNo();
            switch (impact.getCaracteristic()) {
                case "Ecology":
                    stat = ecologyStatus + points;
                    ecologyStatus = stat < MAX_STAT ? (stat < 0 ? 0 : stat) : MAX_STAT;
                    break;
                case "Economy":
                    stat = economyStatus + points;
                    economyStatus = stat < MAX_STAT ? (stat < 0 ? 0 : stat) : MAX_STAT;
                    break;
                case "Comfort":
                    stat = comfortStatus + points;
                    comfortStatus = stat < MAX_STAT ? (stat < 0 ? 0 : stat) : MAX_STAT;
                    break;
                default:
                    break;
            }
        }

        this.invalidate();
        if(ecologyStatus <= 0 || economyStatus <= 0 || comfortStatus <= 0) {
            Intent intent = new Intent(getContext(), GameOver.class);
            getContext().startActivity(intent);
        }
    }

    private int getAlphaValue() {
        return (int)(255 - ((255/100) * ecologyStatus));
    }
}
