package com.unibratec.livia.planets;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Livia on 10/10/2015.
 */
public class PlanetListFragment extends ListFragment {

    List<Planet> mListPlanets;
    ArrayAdapter<Planet> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListPlanets = loadPlanets();

        limparBusca();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Activity activity = getActivity();
        if (activity instanceof OnPlanetClick) {
            Planet planet = (Planet) l.getItemAtPosition(position);
            ((OnPlanetClick) activity).planetClick(planet);
        }
    }

    public interface OnPlanetClick {
        void planetClick(Planet planet);
    }

    public void limparBusca() {
        mAdapter = new ArrayAdapter<Planet>(getActivity(), android.R.layout.simple_list_item_1, mListPlanets) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                Planet p = (Planet) getItem(position);

                String planetName = getActivity().getString(p.name);

                //#008080
                if (p.name == R.string.planet_name_earth) {
                    textView.setTextColor(Color.parseColor("#008080"));
                }

                textView.setText(planetName);
                return textView;
            }
        };

        setListAdapter(mAdapter);
    }

    public void buscar(String s) {
        if (s == null || s.trim().equals("")) {
            limparBusca();
            return;
        }

        List<Planet> retorno = new ArrayList<Planet>(mListPlanets);
        for (int i = retorno.size() - 1; i >= 0; i--) {
            Planet planet = retorno.get(i);
            String planetName = getActivity().getString(planet.name);

            if (!planetName.toUpperCase().contains(s.toUpperCase())) {
                retorno.remove(planet);
            }

            mAdapter = new ArrayAdapter<Planet>(getActivity(), android.R.layout.simple_list_item_1, retorno) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView textView = (TextView) super.getView(position, convertView, parent);
                    Planet p = (Planet) getItem(position);
                    String planetName = getActivity().getString(p.name);
                    //#008080
                    if (p.name == R.string.planet_name_earth) {
                        textView.setTextColor(Color.parseColor("#008080"));
                    }
                    textView.setText(planetName);
                    return textView;
                }
            };

            setListAdapter(mAdapter);
        }
    }

    public void adicionar(Planet planet) {
        mListPlanets.add(planet);
        mAdapter.notifyDataSetChanged();
    }

    private List<Planet> loadPlanets() {
        List<Planet> retorno = new ArrayList<Planet>();

        retorno.add(new Planet(R.string.planet_name_mercury,
                R.string.discovery_default,
                R.string.named_for_mercury,
                3031,
                88,
                58.6));

        retorno.add(new Planet(R.string.planet_name_venus,
                R.string.discovery_default,
                R.string.named_for_venus,
                7521,
                255,
                241));

        retorno.add(new Planet(R.string.planet_name_earth,
                R.string.discovery_default,
                R.string.named_for_earth,
                7926,
                365.24,
                23.56));

        retorno.add(new Planet(R.string.planet_name_mars,
                R.string.discovery_default,
                R.string.named_for_mars,
                4217,
                687,
                24.37));

        retorno.add(new Planet(R.string.planet_name_jupiter,
                R.string.discovery_default,
                R.string.named_for_jupiter,
                88730,
                11.9,
                9.8));

        retorno.add(new Planet(R.string.planet_name_saturn,
                R.string.discovery_default,
                R.string.named_for_saturn,
                74900,
                29.5,
                10.5));

        retorno.add(new Planet(R.string.planet_name_uranus,
                R.string.discovery_uranus,
                R.string.named_for_uranus,
                31736,
                84,
                18));

        retorno.add(new Planet(R.string.planet_name_neptune,
                R.string.discovery_neptune,
                R.string.named_for_neptune,
                30775,
                165,
                19));

        retorno.add(new Planet(R.string.planet_name_pluto,
                R.string.discovery_pluto,
                R.string.named_for_pluto,
                1430,
                248,
                6.4));


        return retorno;
    }
}
