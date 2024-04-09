// Triangle.java
package com.example.tp2;

public class Triangle {
    private static boolean triangleActive = false;
    private static boolean firstPointSet = false;
    private static float firstPointX;
    private static float firstPointY;
    private static float secondPointX;
    private static float secondPointY;
    private static float thirdPointX;
    private static float thirdPointY;

    // Méthode pour activer ou désactiver le triangle
    public static void toggleTriangle() {
        triangleActive = !triangleActive;
    }

    // Méthode pour vérifier si le triangle est actif
    public static boolean isTriangleActive() {
        return triangleActive;
    }

    // Méthode pour vérifier si le premier point du triangle est défini
    public static boolean isFirstPointSet() {
        return firstPointSet;
    }

    // Méthode pour définir le premier point du triangle
    public static void setFirstPoint(float x, float y) {
        firstPointX = x;
        firstPointY = y;
        firstPointSet = true;
    }

    // Méthode pour obtenir les coordonnées du premier point
    public static float getFirstPointX() {
        return firstPointX;
    }

    public static float getFirstPointY() {
        return firstPointY;
    }

    // Méthode pour obtenir les coordonnées du deuxième point
    public static float getSecondPointX() {
        return secondPointX;
    }

    public static float getSecondPointY() {
        return secondPointY;
    }

    // Méthode pour définir les coordonnées du deuxième point
    public static void setSecondPoint(float x, float y) {
        secondPointX = x;
        secondPointY = y;
    }

    // Méthode pour obtenir les coordonnées du troisième point
    public static float getThirdPointX() {
        return thirdPointX;
    }

    public static float getThirdPointY() {
        return thirdPointY;
    }

    // Méthode pour définir les coordonnées du troisième point
    public static void setThirdPoint(float x, float y) {
        thirdPointX = x;
        thirdPointY = y;
    }
}
