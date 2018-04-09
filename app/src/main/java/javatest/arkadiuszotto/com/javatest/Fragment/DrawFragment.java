package javatest.arkadiuszotto.com.javatest.Fragment;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import javatest.arkadiuszotto.com.javatest.DrawingView;
import javatest.arkadiuszotto.com.javatest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawFragment extends Fragment {

    public static int PAINT_SIZE_SMALL = 2;
    public static int PAINT_SIZE_MEDIUM = 4;
    public static int PAINT_SIZE_LARGE = 6;

    DrawingView drawingView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_draw, container, false);

        drawingView = inflate.findViewById(R.id.drawing_view);
        drawingView.setPaint(createNewPaintWithWidth(PAINT_SIZE_SMALL));

        inflate.findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawingView.clear();
            }
        });


        inflate.findViewById(R.id.radio_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawingView.setPaint(createNewPaintWithWidth(PAINT_SIZE_SMALL));
            }
        });

        inflate.findViewById(R.id.radio_medium).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawingView.setPaint(createNewPaintWithWidth(PAINT_SIZE_MEDIUM));
            }
        });

        inflate.findViewById(R.id.radio_large).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawingView.setPaint(createNewPaintWithWidth(PAINT_SIZE_LARGE));
            }
        });
        return inflate;
    }

    private Paint createNewPaintWithWidth(int width) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(width);
        return paint;
    }


}
