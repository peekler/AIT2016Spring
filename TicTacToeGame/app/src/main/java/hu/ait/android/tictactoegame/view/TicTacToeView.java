package hu.ait.android.tictactoegame.view;

import android.content.Context;
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

public class TicTacToeView extends View {

    private Paint paintBackground;
    private Paint paintLine;

    private List<PointF> listPoints =
            new ArrayList<PointF>();


    public TicTacToeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBackground = new Paint();
        paintBackground.setColor(Color.BLACK);
        paintBackground.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0,
                getWidth(), getHeight(),
                paintBackground);

        canvas.drawLine(0, 0, getWidth(), getHeight(), paintLine);

        for (PointF listPoint : listPoints) {
            canvas.drawCircle(listPoint.x, listPoint.y,
                    50, paintLine);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            listPoints.add(
                    new PointF(event.getX(), event.getY()));

            invalidate(); // redraws the view -> calls the onDraw indirectly
        }

        return super.onTouchEvent(event);
    }

    public void clearGameField() {
        listPoints.clear();
        invalidate();
    }
}
