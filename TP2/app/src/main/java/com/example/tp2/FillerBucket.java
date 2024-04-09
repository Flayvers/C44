package com.example.tp2;

public class FillerBucket {
    public static void fillBackgroundWithPencilColor(SurfaceDessin surfaceDessin, Eraser eraser) {
        int currentPencilColor = surfaceDessin.getPencilColor();
        surfaceDessin.setBackgroundColor(currentPencilColor);
        System.out.println(currentPencilColor);
        eraser.setBackgroundColor(currentPencilColor);
        System.out.println(eraser.getBackgroundColor());
    }
}
