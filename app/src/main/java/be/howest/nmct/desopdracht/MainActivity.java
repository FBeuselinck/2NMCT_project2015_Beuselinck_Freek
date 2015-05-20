package be.howest.nmct.desopdracht;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import be.howest.nmct.desopdracht.data.ShopKortrijk;

public class MainActivity extends ActionBarActivity implements ShopKortrijkFragment.ShopKortrijkFragmentListener, ShopKortrijkDetailsFragment.MapFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ShopKortrijkFragment())
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if(fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showShopKortrijkDetails(ShopKortrijk shopKortrijk){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ShopKortrijkDetailsFragment shopKortrijkDetailsFragment = ShopKortrijkDetailsFragment.newInstance(shopKortrijk);
        fragmentTransaction.replace(R.id.container, shopKortrijkDetailsFragment).addToBackStack(null)
                .commit();
    }

    @Override
    public void showMap(ShopKortrijk shopKortrijk){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MapFragment mapFragment = MapFragment.newInstance(shopKortrijk);
        fragmentTransaction.replace(R.id.container, mapFragment).addToBackStack(null)
                .commit();
    }
}
