package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getContenu();
    }
        private void getContenu(){
        if (getIntent().hasExtra("NameCode") && getIntent().hasExtra("Photo") && getIntent().hasExtra("Affiliation")
                && getIntent().hasExtra("birthcountry") && getIntent().hasExtra("Team") && getIntent().hasExtra("RealName")
                && getIntent().hasExtra("Birthdate") &&  getIntent().hasExtra("Embleme")/* && getIntent().hasExtra("Height") && getIntent().hasExtra("Weight")*/) {
            {

            String textNameCode = getIntent().getStringExtra("NameCode");
            String textPhoto = getIntent().getStringExtra("Photo");
            String textAffiliation = getIntent().getStringExtra("Affiliation");
            String textbirthcountry = getIntent().getStringExtra("birthcountry");
            String textTeam = getIntent().getStringExtra("Team");
            String textRealName = getIntent().getStringExtra("RealName");
            String textBirthdate = getIntent().getStringExtra("Birthdate");
            String textEmbleme = getIntent().getStringExtra("Embleme");




            Activity(textNameCode, textPhoto, textAffiliation, textbirthcountry, textTeam, textRealName, textBirthdate, textEmbleme/*, textHeight, textWeight*/);
        }
        }
    }
        private void Activity(String textNameCode, String textPhoto, String textAffiliation, String textbirthcountry, String textTeam, String textRealName
        , String textBirthdate, String textEmbleme)
        {
            TextView NameCode = findViewById(R.id.NameCode);
            NameCode.setText("NameCode:\n " +textNameCode);

            TextView Affiliation = findViewById(R.id.Affiliation);
            Affiliation.setText("Affiliation:\n " +textAffiliation);

            TextView birthcountry = findViewById(R.id.birthcountry);
            birthcountry.setText("birthcountry:\n " +textbirthcountry);

            TextView Team = findViewById(R.id.Team);
            Team.setText("Team:\n " +textTeam);

            TextView RealName = findViewById(R.id.Realname);
            RealName.setText("RealName:\n " +textRealName);

            TextView Birthdate = findViewById(R.id.Birthdate);
            Birthdate.setText("Birthdate:\n " +textBirthdate);



            ImageView Embleme = findViewById(R.id.Embleme);
            Picasso.with(this)
                    .load(textEmbleme)
                    .into(Embleme);

            ImageView image = findViewById(R.id.Photo);
            Picasso.with(this)
                    .load(textPhoto)
                    .into(image);


        }
}

