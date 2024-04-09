package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Ecouteur ec;
    private SurfaceDessin surfaceDessin;
    private Eraser eraser;
    private int lastUsedTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int backgroundColor = Colors.lightGrayColor; // Couleur de fond par défaut
        eraser = new Eraser(backgroundColor); // Initialiser l'effaceur avec la couleur de fond

        ec = new Ecouteur();
        LinearLayout drawing = findViewById(R.id.surface);
        surfaceDessin = new SurfaceDessin(this);
        surfaceDessin.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        surfaceDessin.setBackgroundColor(backgroundColor); // Définir la couleur de fond
        drawing.addView(surfaceDessin);
        LinearLayout buttonContainer = findViewById(R.id.buttonContainer);
        lastUsedTools = RessourcesIds.PENCIL_ICON_ID;
        for (int i = 0; i < buttonContainer.getChildCount(); i++) {
            View child = buttonContainer.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setOnClickListener(ec);
            }
        }

        HorizontalScrollView horizontalScrollView = findViewById(R.id.horizontalScrollView);
        LinearLayout parent = findViewById(R.id.p);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof HorizontalScrollView) {
                HorizontalScrollView scrollView = (HorizontalScrollView) child;
                LinearLayout scrollViewChild = (LinearLayout) scrollView.getChildAt(0);
                for (int j = 0; j < scrollViewChild.getChildCount(); j++) {
                    View subChild = scrollViewChild.getChildAt(j);
                    if (subChild instanceof ImageView) {
                        ImageView imageView = (ImageView) subChild;
                        imageView.setOnClickListener(ec);
                    }
                }
            }
        }
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            toggleFeatures(viewId);
            switch (viewId) {
                case RessourcesIds.RED_BUTTON_ID:
                    surfaceDessin.setPencilColor(Colors.redColor);
                    break;
                case RessourcesIds.ORANGE_BUTTON_ID:
                    surfaceDessin.setPencilColor(Colors.orangeColor);
                    break;
                case RessourcesIds.YELLOW_BUTTON_ID:
                    surfaceDessin.setPencilColor(Colors.yellowColor);
                    break;
                case RessourcesIds.GREEN_BUTTON_ID:
                    surfaceDessin.setPencilColor(Colors.greenColor);
                    break;
                case RessourcesIds.BLUE_BUTTON_ID:
                    surfaceDessin.setPencilColor(Colors.blueColor);
                    break;
                case RessourcesIds.PURPLE_BUTTON_ID:
                    surfaceDessin.setPencilColor(Colors.purpleColor);
                    break;
                case RessourcesIds.PENCIL_ICON_ID:
                    lastUsedTools = viewId;
                    surfaceDessin.setPencilColor(Colors.blackColor);
                    surfaceDessin.setPencilActivated(true);
                    break;
                case RessourcesIds.ERASER_ICON_ID:
                    lastUsedTools = RessourcesIds.PENCIL_ICON_ID;
                    surfaceDessin.setPencilColor(eraser.getEraserColor());
                    break;
                case RessourcesIds.FILLER_ICON_ID:
                    FillerBucket.fillBackgroundWithPencilColor(surfaceDessin, eraser);
                    break;
                case RessourcesIds.WIDTH_ICON_ID:
                    PencilWidth.showDialog(MainActivity.this, new PencilWidth.PencilWidthChangeListener() {
                        @Override
                        public void onPencilWidthChanged(int newWidth) {
                            surfaceDessin.setPencilWidth(newWidth);
                        }
                    }, surfaceDessin);
                    break;
                case RessourcesIds.PIPETTE_ICON_ID:
                    // Activer ou désactiver la pipette
                    lastUsedTools = viewId;
                    if (Pipette.isPipetteActive()) {
                        Toast.makeText(MainActivity.this, "Outil pipette activée", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Outil pipette désactivée", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RessourcesIds.RECTANGLE_ICON_ID:
                    lastUsedTools = viewId;
                    if (Rectangle.isRectangleActive()) {
                        Toast.makeText(MainActivity.this, "Outil rectangle activée", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Outil rectangle désactivée", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RessourcesIds.CIRCLE_ICON_ID:
                    lastUsedTools = viewId;
                    if (Circle.isCircleActive()) {
                        Toast.makeText(MainActivity.this, "Outil cercle activée", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Outil cercle désactivée", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RessourcesIds.TRIANGLE_ICON_ID:
                    lastUsedTools = viewId;
                    if (Triangle.isTriangleActive()) {
                        Toast.makeText(MainActivity.this, "Outil triangle activée", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Outil triangle désactivée", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RessourcesIds.RESET_ICON_ID:
                    eraser.resetDrawing(surfaceDessin);
                    Toast.makeText(MainActivity.this, "Dessin réinitialisé", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    System.out.println("Erreur bouton cliquer unexistant");
                    break;
            }
        }

    }
    public void toggleFeatures(int id){
        if (Rectangle.isRectangleActive())
            Rectangle.toggleRectangle();
        if (Circle.isCircleActive())
            Circle.toggleCircle();
        if (Triangle.isTriangleActive())
            Triangle.toggleTriangle();
        if(Pipette.isPipetteActive())
            Pipette.togglePipette();
        if(surfaceDessin.isPencilActivated())
            surfaceDessin.setPencilActivated(false);

        switch (id){
            case RessourcesIds.PENCIL_ICON_ID:
            case RessourcesIds.ERASER_ICON_ID:
                surfaceDessin.setPencilActivated(true);
                break;
            case RessourcesIds.RECTANGLE_ICON_ID:
                Rectangle.toggleRectangle();
                break;
            case RessourcesIds.CIRCLE_ICON_ID:
                Circle.toggleCircle();
                break;
            case RessourcesIds.TRIANGLE_ICON_ID:
                Triangle.toggleTriangle();
                break;
            case RessourcesIds.PIPETTE_ICON_ID:
                Pipette.togglePipette();
                break;
            default:
                toggleFeatures(lastUsedTools);
        }
    }

}
