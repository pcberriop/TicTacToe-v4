package com.example.user.triqui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View {

    public static final int GRID_WIDTH = 6;
    private Bitmap mHumanBitmap;
    private Bitmap mComputerBitmap;

    private Paint mPaint;

    private TicTacToeGame mGame;

    public void setGame(TicTacToeGame game){
        mGame = game;
    }

    public int getBoardCellWidth(){
        return getWidth()/3;
    }

    public int getBoardCellHeight(){
        return getHeight()/3;
    }

    public void initialize() {
        mHumanBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cross);
        mComputerBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.circle);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public BoardView(Context context){
        super(context);
        initialize();
    }

    public BoardView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        initialize();
    }

    public BoardView(Context context, AttributeSet attrs){
        super(context,attrs);
        initialize();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int boardWidth = getWidth();
        int boardHeight = getHeight();

        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(GRID_WIDTH);

        int cellWidth = boardWidth/3;
        canvas.drawLine(cellWidth, 0, cellWidth, boardHeight, mPaint);
        canvas.drawLine(cellWidth*2, 0, cellWidth*2, boardHeight, mPaint);

        int cellHeight = boardHeight/3;
        canvas.drawLine(0, cellHeight, boardHeight,cellHeight, mPaint);
        canvas.drawLine(0, cellHeight*2, boardHeight,cellHeight*2, mPaint);

        for(int i=0; i < TicTacToeGame.BOARD_SIZE; i++){
            int col = i% 3;
            int row = i /3;

            int left = col*cellWidth ; //y1
            int top = row*cellWidth; //x1
            int right = (col +1)*cellWidth; //y2
            int bottom = (row+1)*cellWidth; //x2

            if(mGame != null && mGame.getBoardOccupant(i) == TicTacToeGame.HUMAN_PLAYER){
                canvas.drawBitmap(mHumanBitmap, null, new Rect(left, top, right, bottom), null);
            }
            else if(mGame != null && mGame.getBoardOccupant(i) == TicTacToeGame.COMPUTER_PLAYER){
                canvas.drawBitmap(mComputerBitmap, null, new Rect(left, top, right, bottom), null);
            }
        }

    }
}