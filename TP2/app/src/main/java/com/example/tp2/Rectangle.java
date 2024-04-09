package com.example.tp2;

public class Rectangle {
    private static boolean rectangleActive = false;
    private static float currentX;
    private static float currentY;

    // Méthode pour activer ou désactiver la pipette
    public static void toggleRectangle() {rectangleActive = !rectangleActive;}

    // Méthode pour vérifier si la pipette est active
    public static boolean isRectangleActive() {
        return rectangleActive;
    }

    public static float getCurrentX() {
        return currentX;
    }

    public static float getCurrentY() {
        return currentY;
    }

    public static void setCurrentX(float X) {
        currentX = X;
    }

    public static void setCurrentY(float Y) {
        currentY = Y;
    }
}
