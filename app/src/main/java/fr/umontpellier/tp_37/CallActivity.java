package fr.umontpellier.tp_37;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CallActivity extends Activity {
    TextView textViewNumTelCall;
    Button buttonCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // Initialiser les vues
        textViewNumTelCall = findViewById(R.id.textViewNumTelCall);
        buttonCall = findViewById(R.id.buttonCall);

        // Récupérer le numéro de téléphone passé depuis l'activité ReceiveActivity
        Intent intent = getIntent();
        String numTel = intent.getStringExtra("numTel");

        // Afficher le numéro de téléphone dans le TextView
        textViewNumTelCall.setText(String.format("%s: %s", getString(R.string.numeroTel), numTel));

        // Configurer le bouton "Appeler"
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + numTel));
                startActivity(callIntent);
            }
        });
    }
}
