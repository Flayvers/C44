// Pipette.java
package com.example.tp2;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Pipette {
    private static boolean pipetteActive = false;

    // Méthode pour activer ou désactiver la pipette
    public static void togglePipette() {
        pipetteActive = !pipetteActive;
    }

    // Méthode pour vérifier si la pipette est active
    public static boolean isPipetteActive() {
        return pipetteActive;
    }

    // Méthode pour récupérer la couleur du pixel aux coordonnées (x, y)
    public static int getColorAt(Bitmap bitmap, int x, int y) {
        if (bitmap != null && x >= 0 && y >= 0 && x < bitmap.getWidth() && y < bitmap.getHeight()) {
            return bitmap.getPixel(x, y);
        } else {
            return Color.BLACK; // Par défaut, retourne noir si les coordonnées sont en dehors du bitmap
        }
    }
}
