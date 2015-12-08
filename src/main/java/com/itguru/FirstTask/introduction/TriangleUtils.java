package com.itguru.FirstTask.introduction;

public class TriangleUtils {
    /**
     * Задача о треугольнике
     *
     * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
     * могут быть сторонами треугольника и false, если не могут.
     *
     */

    public boolean isTriangle(int a, int b, int c) throws IllegalArgumentException {
        if (a == 0 || b == 0 || c == 0) return false;
        if (a < b + c && b < a + c && c < a + b) return true;
        return false;
    }

    /**
     * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
     * площадь треугольника.
     */

    public double getTriangleArea(int a, int b, int c) throws IllegalArgumentException {
        double p = 0.0, area = 0.0;
        if (isTriangle(a, b, c)) {
            p = (a + b + c) / 2;
            area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        return area;
    }
}