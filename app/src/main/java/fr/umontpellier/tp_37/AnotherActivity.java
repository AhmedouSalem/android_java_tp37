package fr.umontpellier.tp_37;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.umontpellier.tp_37.utils.FormValidator;

public class AnotherActivity extends Activity {
    EditText nom;
    EditText prenom;
    EditText age;
    EditText domComp;
    EditText numTel;

    /*
    [nom du champs, identifiant res du champs]
    * */
    String[][] fields = {
            {"Nom", "editNom"},
            {"Prenom", "editPrenom"},
            {"Âge", "editAge"},
            {"Domaine de compétences", "editDomaine"},
            {"Numéro de téléphone", "editNumTel"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainContainer = new LinearLayout(this);
        mainContainer.setId("main".hashCode());
        mainContainer.setOrientation(LinearLayout.VERTICAL);
        mainContainer.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );
        // Toolbar
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle(R.string.title);
        toolbar.setBackgroundColor(getResources().getColor(R.color.principale));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mainContainer.addView(toolbar);
        LinearLayout formContainer = new LinearLayout(this);
        formContainer.setId("formContainer".hashCode());
        formContainer.setPadding(16, 16, 16, 16);
        formContainer.setOrientation(LinearLayout.VERTICAL);
        formContainer.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );
        mainContainer.addView(formContainer);
        setContentView(mainContainer);
        for (String[] field : fields) {
            addField(formContainer, field[0], field[1]);
        }

        // ajouter le button submit
        Button button = new Button(this);
        button.setId("submitButton".hashCode());
        button.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        button.setBackgroundColor(getResources().getColor(R.color.principale));
        button.setTextColor(getResources().getColor(R.color.white));
        button.setText(R.string.envoyer);
        formContainer.addView(button);
        // validation de formulaire
        nom = findViewById("editNom".hashCode());
        prenom = findViewById("editPrenom".hashCode());
        age = findViewById("editAge".hashCode());
        domComp = findViewById("editDomaine".hashCode());
        numTel = findViewById("editNumTel".hashCode());
        button.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(AnotherActivity.this,
                            "Nom: " + nomValue + "\n" +
                                    "Prénom: " + prenomValue + "\n" +
                                    "Âge: " + ageValue + "\n" +
                                    "Domaine: " + domCompValue + "\n" +
                                    "Numéro: " + numTelValue,
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AnotherActivity.this, "Veuillez corriger les erreurs dans le formulaire", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    private void addField(LinearLayout container, String label, String editTextId) {
        LinearLayout fieldLayout = new LinearLayout(this);
        fieldLayout.setOrientation(LinearLayout.VERTICAL);
        fieldLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        fieldLayout.setPadding(0, 16, 0, 16);


        TextView textView = new TextView(this);
        textView.setText(label);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));


        EditText editText = new EditText(this);
        editText.setId(editTextId.hashCode());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 20);
        editText.setLayoutParams(params);

        editText.setPadding(12, 12, 12, 12);

        editText.setBackgroundResource(R.drawable.edittext_selector);

        fieldLayout.addView(textView);
        fieldLayout.addView(editText);


        container.addView(fieldLayout);
    }
}