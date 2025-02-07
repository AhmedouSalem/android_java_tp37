package fr.umontpellier.tp_37;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.umontpellier.tp_37.utils.FormValidator;

public class MainActivity extends Activity {
    EditText nom;
    EditText prenom;
    EditText age;
    EditText domComp;
    EditText numTel;

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
                // Validation des champs
                boolean estValide = FormValidator.validerChamps(nom, prenom, age, numTel, domComp);

                if (estValide) {
                    // Récupération des valeurs des champs
                    String nomValue = nom.getText().toString();
                    String prenomValue = prenom.getText().toString();
                    int ageValue = Integer.parseInt(age.getText().toString());
                    String domCompValue = domComp.getText().toString();
                    long numTelValue = Long.parseLong(numTel.getText().toString());

                    // Affichage avec un Toast
                    Toast.makeText(MainActivity.this,
                            "Nom: " + nomValue + "\n" +
                                    "Prénom: " + prenomValue + "\n" +
                                    "Âge: " + ageValue + "\n" +
                                    "Domaine: " + domCompValue + "\n" +
                                    "Numéro: " + numTelValue,
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez corriger les erreurs dans le formulaire", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}