package com.unibratec.livia.planets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Livia on 10/10/2015.
 */
public class PlanetDetailsFragment extends Fragment {

    public static final String TAG_DETAILS = "tag_details";
    public static final String EXTRA_PLANET = "planet";

    TextView mTxtName;
    TextView mTxtDiscovery;
    TextView mTxtDiameter;

    Planet mPlanet;

    public static PlanetDetailsFragment newInstancePlanetDetails(Planet planet){
        Bundle params = new Bundle();
        params.putSerializable(EXTRA_PLANET, planet);
        PlanetDetailsFragment fragment = new PlanetDetailsFragment();
        fragment.setArguments(params);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlanet = (Planet) getArguments().getSerializable(EXTRA_PLANET);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_planet_details, container, false);
        mTxtName = (TextView)layout.findViewById(R.id.textView_namePlanet);
        mTxtDiscovery = (TextView)layout.findViewById(R.id.textView_discovery);
        mTxtDiameter = (TextView) layout.findViewById(R.id.textView_diameter);

        if(mPlanet != null){
            mTxtName.setText(mPlanet.name);
            mTxtDiscovery.setText(mPlanet.discovery);
            mTxtDiameter.setText(mPlanet.diameter + "");

        }

        return layout;
    }
}

