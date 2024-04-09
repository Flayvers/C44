package com.example.tp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class SurfaceDessin extends View {
    private ArrayList<PathColorPair> paths = new ArrayList<>();
    private int currentColor = Color.BLACK;
    private Paint pencil;
    private int pencilWidth = 18;
    private Bitmap bitmap;
    private PointF rectangleStart;
    private PointF circleStart;
    private PointF triangleVertex1;
    private PointF triangleVertex2;
    private boolean pencilActivated = true;

    private Path trianglePath;

    public SurfaceDessin(Context context) {
        super(context);
        init();
    }

    public SurfaceDessin(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Créer un chemin initial pour la couleur par défaut
        Path defaultPath = new Path();
        pencil = new Paint();
        paths.add(new PathColorPair(defaultPath, currentColor, pencilWidth));
        pencil.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Dessiner tous les chemins avec leurs couleurs respectives
        for (PathColorPair pair : paths) {
            canvas.drawPath(pair.path, pair.pencil);
        }
        if (rectangleStart != null) {
            float left = rectangleStart.x;
            float top = rectangleStart.y;
            float right = Rectangle.getCurrentX(); // Défini dans ACTION_MOVE
            float bottom = Rectangle.getCurrentY(); // Défini dans ACTION_MOVE
            pencil.setColor(currentColor);
            pencil.setStyle(Paint.Style.STROKE);
            canvas.drawRect(left, top, right, bottom, pencil);
        }
        if (circleStart != null) {
            float cx = circleStart.x; // Coordonnée x du centre du cercle
            float cy = circleStart.y; // Coordonnée y du centre du cercle
            float radius = Math.max(Math.abs(Circle.getCurrentX() - cx), Math.abs(Circle.getCurrentY() - cy)); // Calcul du rayon

            pencil.setColor(currentColor);
            pencil.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(cx, cy, radius, pencil); // Dessiner le cercle à partir du centre et du rayon
        }
        if (triangleVertex1 != null && triangleVertex2 != null) {
            // Dessiner le segment entre les deux premiers coins du triangle
            canvas.drawLine(triangleVertex1.x, triangleVertex1.y, triangleVertex2.x, triangleVertex2.y, pencil);
            if (trianglePath != null) {
                // Dessiner le chemin du triangle
                canvas.drawPath(trianglePath, pencil);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (Pipette.isPipetteActive()) {
                    getBitmapImage();
                    int color = Pipette.getColorAt(bitmap, (int) touchX, (int) touchY); // Use bitmap for pipette operation
                    // Changer la couleur du crayon avec la couleur récupérée par la pipette
                    setPencilColor(color);
                } else if (Rectangle.isRectangleActive()) {
                    rectangleStart = new PointF(touchX, touchY);
                    return true;
                } else if (Circle.isCircleActive()) {
                    circleStart = new PointF(touchX, touchY); // Définir le point de départ du cercle
                    return true;
                } else if (Triangle.isTriangleActive()) {
                    if (triangleVertex1 == null) {
                        triangleVertex1 = new PointF(touchX, touchY);
                    }
                    return true;
                } else {
                    // Commencer un nouveau chemin avec la couleur actuelle
                    Path newPath = new Path();
                    newPath.moveTo(touchX, touchY);
                    paths.add(new PathColorPair(newPath, currentColor, pencilWidth));
                    return true;
                }
            case MotionEvent.ACTION_MOVE:
                if (pencilActivated) {
                    // Ajouter les points au chemin actuel
                    paths.get(paths.size() - 1).path.lineTo(touchX, touchY);
                } else if (Rectangle.isRectangleActive()) {
                    Rectangle.setCurrentX(touchX);
                    Rectangle.setCurrentY(touchY);
                    invalidate();
                } else if (Circle.isCircleActive()) {
                    Circle.setCurrentX(touchX);
                    Circle.setCurrentY(touchY);
                    invalidate();
                } else if (Triangle.isTriangleActive()) {
                    if (triangleVertex1 != null) {
                        triangleVertex2.set(touchX, touchY); // Déplacer le deuxième sommet du triangle
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Pipette.isPipetteActive()) {
                    // Désactiver la pipette après avoir récupéré la couleur du pixel
                    Pipette.togglePipette();
                    this.setPencilActivated(true);
                } else if (Rectangle.isRectangleActive()) {
                    drawFinalRectangle(touchX, touchY);
                } else if (Circle.isCircleActive()) {
                    drawFinalCircle(touchX, touchY);
                } else if (Triangle.isTriangleActive()) {
                    if (triangleVertex1 != null && triangleVertex2 != null) {
                        // Créer le chemin du triangle
                        trianglePath = new Path();
                        trianglePath.moveTo(triangleVertex1.x, triangleVertex1.y);
                        trianglePath.lineTo(triangleVertex2.x, triangleVertex2.y);
                        trianglePath.lineTo(touchX, touchY);
                        trianglePath.close(); // Fermer le chemin pour former un triangle

                        // Ajouter le triangle à la liste des chemins avec la couleur actuelle
                        paths.add(new PathColorPair(trianglePath, currentColor, pencilWidth));

                        // Réinitialiser les sommets du triangle
                        triangleVertex1 = null;
                        triangleVertex2 = null;

                        invalidate();
                    }
                }
                break;
            default:
                return false;
        }

        // Redessiner la vue
        invalidate();
        return true;
    }

    public void setPencilColor(int color) {
        currentColor = color;
    }

    public int getPencilColor() {
        return currentColor;
    }

    public int getPencilWidth() {
        return pencilWidth;
    }

    public void setPencilWidth(int width) {
        this.pencilWidth = width;
        pencil.setStrokeWidth(width);
        invalidate();
    }

    public void getBitmapImage() {
        this.buildDrawingCache();
        this.bitmap = Bitmap.createBitmap(this.getDrawingCache());
        this.destroyDrawingCache();
    }

    public void clearPaths() {
        paths.clear(); // Efface tous les chemins
        setPencilWidth(10);
        setBackgroundColor(Colors.lightGrayColor);
    }

    // Classe interne pour stocker un chemin et sa couleur associée
    private class PathColorPair {
        Path path;
        Paint pencil;

        PathColorPair(Path path, int color, int width) {
            this.path = path;
            pencil = new Paint();
            pencil.setColor(color);
            pencil.setStrokeWidth(width);
            pencil.setStyle(Paint.Style.STROKE);
        }
    }

    private void drawFinalRectangle(float endX, float endY) {
        if (rectangleStart != null) {
            // Ajoutez un nouveau chemin pour le rectangle final
            Path rectPath = new Path();
            RectF rect = new RectF();
            rect.set(rectangleStart.x, rectangleStart.y, endX, endY);
            //rectPath.addRect(rectangleStart.x, rectangleStart.y, endX, endY, Path.Direction.CW);
            rectPath.addRect(rect, Path.Direction.CW);
            // Ajoutez le rectangle à la liste des chemins avec la couleur actuelle
            paths.add(new PathColorPair(rectPath, currentColor, pencilWidth));

            // Réinitialisez la variable pour le coin supérieur gauche du rectangle
            rectangleStart = null;

            // Redessinez la vue pour afficher le rectangle final
            invalidate();
        }
    }

    private void drawFinalCircle(float endX, float endY) {
        if (circleStart != null) {
            // Calculez le rayon du cercle à partir du point de départ et du point final
            float radius = Math.max(Math.abs(endX - circleStart.x), Math.abs(endY - circleStart.y));

            // Calculez le centre du cercle
            float centerX = (endX + circleStart.x) / 2;
            float centerY = (endY + circleStart.y) / 2;

            // Ajoutez un nouveau chemin pour le cercle final
            Path circlePath = new Path();
            circlePath.addCircle(centerX, centerY, radius, Path.Direction.CW);
            // Ajoutez le cercle à la liste des chemins avec la couleur actuelle
            paths.add(new PathColorPair(circlePath, currentColor, pencilWidth));

            // Réinitialisez la variable pour le point de départ du cercle
            circleStart = null;

            // Redessinez la vue pour afficher le cercle final
            invalidate();
        }
    }

    public void setPencilActivated(boolean activate) {
        this.pencilActivated = activate;
    }

    public boolean isPencilActivated() {
        return this.pencilActivated;
    }
}
