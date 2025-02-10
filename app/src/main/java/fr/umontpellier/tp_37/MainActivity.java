package fr.umontpellier.tp_37;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.umontpellier.tp_37.utils.FormValidator;

public class MainActivity extends Activity {
    EditText nom, prenom, age, domComp, numTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des champs EditText
        nom = findViewById(R.id.editNom);
        prenom = findViewById(R.id.editPrenom);
        age = findViewById(R.id.editAge);
        domComp = findViewById(R.id.editDomaine);
        numTel = findViewById(R.id.editNumTel);

        // Récupération du bouton "Envoyer"
        Button submitButton = findViewById(R.id.submitButton);

        // On click sur le bouton "Envoyer"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAlertDialog();
            }
        });
    }

    public void handleAlertDialog() {
        // Validation des champs
        boolean estValide = FormValidator.validerChamps(nom, prenom, age, numTel, domComp);
        if (estValide) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.confirmationTitle)
                    .setMessage(R.string.confirmationMessage)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Créer un itnent pour lancer l'activité ReceiveActivity
                            Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
                            intent.putExtra("nom", nom.getText().toString());
                            intent.putExtra("prenom", prenom.getText().toString());
                            intent.putExtra("age", age.getText().toString());
                            intent.putExtra("domaineComp", domComp.getText().toString());
                            intent.putExtra("numTel", numTel.getText().toString());
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Validation annulée", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        } else {
            Toast.makeText(MainActivity.this, "Veuillez corriger les erreurs dans le formulaire", Toast.LENGTH_SHORT).show();
        }
    }

}