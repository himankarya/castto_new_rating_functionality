package com.soomapps.screenmirroring.Util;

import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public abstract class Utility {

    public static View.OnTouchListener imgPress(){
        return imgPress(0x77D3E3F3); //DEFAULT color
    }

    public static View.OnTouchListener imgPress(final int color){
        return new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()) {

                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }

                    case MotionEvent.ACTION_UP:
                        v.performClick();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //Clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }

                }

                return true;
            }
        };
    }

}