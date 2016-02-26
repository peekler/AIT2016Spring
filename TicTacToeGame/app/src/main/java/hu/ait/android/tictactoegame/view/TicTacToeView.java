package hu.ait.android.tictactoegame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.tictactoegame.MainActivity;
import hu.ait.android.tictactoegame.R;
import hu.ait.android.tictactoegame.model.TicTacToeModel;

public class TicTacToeView extends View {

    private Paint paintBackground;
    private Paint paintLine;

    private Bitmap backGround;


    public TicTacToeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBackground = new Paint();
        paintBackground.setColor(Color.BLACK);
        paintBackground.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);

        backGround = BitmapFactory.decodeResource(getResources(),
                R.drawable.background);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0,
                getWidth(), getHeight(),
                paintBackground);

        canvas.drawBitmap(backGround, 0, 0, null);


        drawGameArea(canvas);

        drawPlayers(canvas);
    }

    private void drawGameArea(Canvas canvas) {
        // border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        // two horizontal lines
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3,
                paintLine);
        canvas.drawLine(0, 2 * getHeight() / 3, getWidth(),
                2 * getHeight() / 3, paintLine);

        // two vertical lines
        canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(),
                paintLine);
        canvas.drawLine(2 * getWidth() / 3, 0, 2 * getWidth() / 3,
                getHeight(), paintLine);
    }

    private void drawPlayers(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TicTacToeModel.getInstance().getFieldContent(i, j) ==
                        TicTacToeModel.CIRCLE) {

                    // draw a circle at the center of the field

                    // X coordinate: left side of the square + half width of the square
                    float centerX = i * getWidth() / 3 + getWidth() / 6;
                    float centerY = j * getHeight() / 3 + getHeight() / 6;
                    int radius = getHeight() / 6 - 2;

                    canvas.drawCircle(centerX, centerY, radius, paintLine);

                } else if (TicTacToeModel.getInstance().getFieldContent(i, j)
                        == TicTacToeModel.CROSS) {
                    canvas.drawLine(i * getWidth() / 3, j * getHeight() / 3,
                            (i + 1) * getWidth() / 3,
                            (j + 1) * getHeight() / 3, paintLine);

                    canvas.drawLine((i + 1) * getWidth() / 3, j * getHeight() / 3,
                            i * getWidth() / 3, (j + 1) * getHeight() / 3, paintLine);
                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = ((int) event.getX()) / (getWidth() / 3);
            int tY = ((int) event.getY()) / (getHeight() / 3);

            if (tX < 3 && tY < 3 &&
                    TicTacToeModel.getInstance().getFieldContent(tX,tY) ==
                    TicTacToeModel.EMPTY) {

                TicTacToeModel.getInstance().setFieldContent(
                        tX, tY,
                        TicTacToeModel.getInstance().getNextPlayer());
                TicTacToeModel.getInstance().changeNextPlayer();

                String nextPlayer =
                        TicTacToeModel.getInstance().getNextPlayer() ==
                                TicTacToeModel.CIRCLE ? "O" : "X";

                ((MainActivity)getContext()).showSnackBarMessage(
                        getContext().getString(R.string.text_next_player,
                                nextPlayer, 100));

                invalidate(); // redraws the view -> calls the onDraw indirectly
            }
        }

        return super.onTouchEvent(event);
    }

    public void clearGameField() {
        TicTacToeModel.getInstance().resetModel();
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);

        backGround = Bitmap.createScaledBitmap(backGround, d, d, false);

    }
}
