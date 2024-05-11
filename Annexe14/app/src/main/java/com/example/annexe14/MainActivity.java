package com.example.annexe14;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView token1, token2, token3, token4; // Références aux jetons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des ImageView
        token1 = findViewById(R.id.token1);
        token2 = findViewById(R.id.token2);
        token3 = findViewById(R.id.token3);
        token4 = findViewById(R.id.token4);

        // Ajout des écouteurs pour le glisser-déposer
        token1.setOnTouchListener(new MyTouchListener());
        token2.setOnTouchListener(new MyTouchListener());
        token3.setOnTouchListener(new MyTouchListener());
        token4.setOnTouchListener(new MyTouchListener());

        // Ajout des écouteurs pour les conteneurs de colonnes
        findViewById(R.id.column1).setOnDragListener(new MyDragListener());
        findViewById(R.id.column2).setOnDragListener(new MyDragListener());
        findViewById(R.id.column3).setOnDragListener(new MyDragListener());
        findViewById(R.id.column4).setOnDragListener(new MyDragListener());
    }

    private final class MyTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                // Démarrer le glisser-déposer
                ClipData clipData = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(clipData, shadowBuilder, view, 0);

                // Rendre l'ImageView invisible pendant le drag
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    private class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Rien à faire ici
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    // Changement de l'arrière-plan en sélectionné
                    v.setBackground(getResources().getDrawable(R.drawable.background_contenant_selectionne));
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    // Retour à l'arrière-plan normal
                    v.setBackground(getResources().getDrawable(R.drawable.background_contenant));
                    break;
                case DragEvent.ACTION_DROP:
                    // Réception de l'élément glissé
                    View draggedView = (View) event.getLocalState();

                    // Retirer l'élément de son ancien parent
                    ViewGroup oldParent = (ViewGroup) draggedView.getParent();
                    oldParent.removeView(draggedView);

                    // Ajouter l'élément au nouveau parent
                    LinearLayout newParent = (LinearLayout) v;
                    newParent.addView(draggedView);
                    draggedView.setVisibility(View.VISIBLE);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    // Retour à l'arrière-plan normal
                    v.setBackground(getResources().getDrawable(R.drawable.background_contenant));
                    break;
            }
            return true;
        }
    }
}
