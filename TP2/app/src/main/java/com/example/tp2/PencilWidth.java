package com.example.tp2;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class PencilWidth {
    public interface PencilWidthChangeListener {
        void onPencilWidthChanged(int newWidth);
    }

    public static void showDialog(final Context context, final PencilWidthChangeListener listener, final SurfaceDessin surfaceDessin) {
        // Déclare la variable dialog comme final
        final AlertDialog[] dialog = {null};

        // Crée un AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Crée la vue pour la boîte de dialogue
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_pencil_width, null);

        // Trouve les vues dans la mise en page de la boîte de dialogue
        final SeekBar seekBar = dialogView.findViewById(R.id.seekBar);
        final TextView valueTextView = dialogView.findViewById(R.id.valueTextView);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button confirmButton = dialogView.findViewById(R.id.confirmButton);

        // Définit la valeur maximale pour le SeekBar
        seekBar.setMax(98);

        // Définit la progression initiale et le texte pour valueTextView
        int initialWidth = surfaceDessin.getPencilWidth(); // Supposant que la méthode getCurrentWidth existe dans SurfaceDessin
        seekBar.setProgress(initialWidth - 1);
        valueTextView.setText("Largeur: " + initialWidth);

        // Définit OnClickListener pour le SeekBar pour mettre à jour valueTextView
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueTextView.setText("Largeur: " + (progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Définit OnClickListener pour le bouton d'annulation pour fermer la boîte de dialogue
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog[0].dismiss();
            }
        });

        // Définit OnClickListener pour le bouton de confirmation pour fermer la boîte de dialogue et passer la largeur sélectionnée au listener
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedWidth = seekBar.getProgress() + 1;
                listener.onPencilWidthChanged(selectedWidth);
                dialog[0].dismiss();
            }
        });

        // Définit la vue pour la boîte de dialogue et la crée
        builder.setView(dialogView);
        dialog[0] = builder.create();

        // Affiche la boîte de dialogue
        dialog[0].show();
    }


}
