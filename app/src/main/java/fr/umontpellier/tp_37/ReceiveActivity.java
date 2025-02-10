package fr.umontpellier.tp_37;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReceiveActivity extends Activity {
    TextView textViewNom, textViewPrenom, textViewAge, textViewDomaine, textViewNumTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_receive);

        // Initialisation des TextView pour afficher les données
        textViewNom = findViewById(R.id.textViewNom);
        textViewPrenom = findViewById(R.id.textViewPrenom);
        textViewAge = findViewById(R.id.textViewAge);
        textViewDomaine = findViewById(R.id.textViewDomaine);
        textViewNumTel = findViewById(R.id.textViewNumTel);

        // Récupérer les données passées par l'Intent
        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        String age = intent.getStringExtra("age");
        String domaineComp = intent.getStringExtra("domaineComp");
        String numTel = intent.getStringExtra("numTel");

        // Afficher les données dans les TextView
        textViewNom.setText(String.format("%s: %s", getString(R.string.nom), nom));
        textViewPrenom.setText(String.format("%s: %s", getString(R.string.prenom), prenom));
        textViewAge.setText(String.format("%s: %s", getString(R.string.age), age));
        textViewDomaine.setText(String.format("%s: %s", getString(R.string.domaineComp), domaineComp));
        textViewNumTel.setText(String.format("%s: %s", getString(R.string.numeroTel), numTel));

        // Recupérer les buttons
        Button btnOk = findViewById(R.id.btnOk);
        Button btnRetour = findViewById(R.id.btnRetour);

        // configurer les buttons
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceiveActivity.this, CallActivity.class);
                intent.putExtra("numTel", numTel);
                startActivity(intent);
            }
        });

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
