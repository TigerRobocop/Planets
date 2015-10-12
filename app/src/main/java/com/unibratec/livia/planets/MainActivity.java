package com.unibratec.livia.planets;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements PlanetListFragment.OnPlanetClick, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener
{

    private FragmentManager mFragmentManager;
    private PlanetListFragment mPlanetListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mPlanetListFragment = (PlanetListFragment)mFragmentManager.findFragmentById(R.id.list_planets_fragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);

        searchView.setQueryHint(getString(R.string.hint_search));

        MenuItemCompat.setOnActionExpandListener(searchItem, this);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void planetClick(Planet planet) {
        if(isTablet()){
            PlanetDetailsFragment fragment = PlanetDetailsFragment.newInstancePlanetDetails(planet);

            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.details, fragment, PlanetDetailsFragment.TAG_DETAILS);
            ft.commit();
        }else{
            Intent intent = new Intent(this, PlanetDetailsActivity.class);
            intent.putExtra(PlanetDetailsActivity.EXTRA_PLANET, planet);
            startActivity(intent);
        }
    }

    private boolean isTablet(){
        return getResources().getBoolean(R.bool.tablet);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        mPlanetListFragment.limparBusca();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPlanetListFragment.buscar(newText);
        return false;
    }
}
