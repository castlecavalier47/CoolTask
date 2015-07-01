package com.castle.cooltask.utils;

import android.animation.TypeEvaluator;

import com.castle.cooltask.model.Point;

/**
 * Created by apple on 15/6/23.
 */
public class PointEvaluator implements TypeEvaluator {

    boolean b;

    public PointEvaluator(){};

    public PointEvaluator(boolean b){
        this.b = b;
    }

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        float t = fraction;
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        Point controlPoint;
        if(!b){
           controlPoint = new Point(startPoint.getX(),endPoint.getY());
        }else{
            controlPoint = new Point(endPoint.getX(),startPoint.getY());
        }

        float x = (1-t)*(1-t)*startPoint.getX() + 2*t*(1-t)*controlPoint.getX()+t*t*endPoint.getX();
        float y = (1-t)*(1-t)*startPoint.getY() + 2*t*(1-t)*controlPoint.getY()+t*t*endPoint.getY();
        Point point = new Point(x,y);
        return point;
    }
}
