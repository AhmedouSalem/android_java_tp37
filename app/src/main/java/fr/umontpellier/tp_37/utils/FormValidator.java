package fr.umontpellier.tp_37.utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class FormValidator {

    // Méthode pour valider les champs
    public static boolean validerChamps(EditText nom, EditText prenom, EditText age, EditText numTel, EditText domaineCompetences) {
        // Validation du nom
        if (TextUtils.isEmpty(nom.getText().toString().trim())) {
            nom.setError("Le nom ne peut pas être vide");
            return false;
        }

        // Validation du prénom
        if (TextUtils.isEmpty(prenom.getText().toString().trim())) {
            prenom.setError("Le prénom ne peut pas être vide");
            return false;
        }

        // Validation de l'âge
        String ageText = age.getText().toString().trim();
        if (TextUtils.isEmpty(ageText)) {
            age.setError("L'âge ne peut pas être vide");
            return false;
        }

        if (!ageText.matches("^\\d+$")) {
            age.setError("L'âge doit être un nombre entier positif");
            return false;
        }
        try {
            int ageValue = Integer.parseInt(ageText);
            if (ageValue < 10 || ageValue > 100) {
                age.setError("L'âge doit être entre 10 et 100");
                return false;
            }
        } catch (NumberFormatException e) {
            age.setError("L'âge doit être un nombre valide");
            return false;
        }

        // Validation du domaine de compétences
        if (TextUtils.isEmpty(domaineCompetences.getText().toString().trim())) {
            domaineCompetences.setError("Le domaine de compétences ne peut pas être vide");
            return false;
        }

        // Validation du numéro de téléphone
        String numTelText = numTel.getText().toString().trim();
        if (TextUtils.isEmpty(numTelText)) {
            numTel.setError("Le numéro de téléphone ne peut pas être vide");
            return false;
        }
        if (!Patterns.PHONE.matcher(numTelText).matches()) {
            numTel.setError("Le numéro de téléphone n'est pas valide");
            return false;
        }

        return true;
    }
}
