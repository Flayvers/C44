package com.example.tp2;

import android.graphics.Color;

public class Eraser {
    private int backgroundColor;

    public Eraser(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getEraserColor() {
        return backgroundColor;
    }

    public void resetDrawing(SurfaceDessin surfaceDessin) {
        // Réinitialisation du dessin dans la vue personnalisée SurfaceDessin
        surfaceDessin.clearPaths();
        // Redessiner la vue pour refléter les changements
        surfaceDessin.invalidate();
    }
}
