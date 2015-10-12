package com.unibratec.livia.planets;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlanetDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_PLANET = "planet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Planet planet = (Planet)intent.getSerializableExtra(EXTRA_PLANET);

        PlanetDetailsFragment detailsFragment = PlanetDetailsFragment.newInstancePlanetDetails(planet);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.details, detailsFragment, PlanetDetailsFragment.TAG_DETAILS);
        ft.commit();

    }
}
