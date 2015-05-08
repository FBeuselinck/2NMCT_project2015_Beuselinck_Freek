package be.howest.nmct.desopdracht;

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


public class MainActivity extends ActionBarActivity implements ShopKortrijkFragment.ShopKortrijkFragmentListener {

    public static final String EXTRA_SHOP = "be.howest.nmct.desopdracht.EXTRA_SHOP";
    public static final String EXTRA_ADDRESS = "be.howest.nmct.desopdracht.EXTRA_ADDRESS";
    public static final String EXTRA_POSTCODE = "be.howest.nmct.desopdracht.EXTRA_POSTCODE";
    public static final String EXTRA_DEELGEMEENTE = "be.howest.nmct.desopdracht.EXTRA_DEELGEMEENTE";

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
    public void showShopKortrijkDetails(ShopKortrijk shopKortrijk){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ShopKortrijkDetailsFragment shopKortrijkDetailsFragment = ShopKortrijkDetailsFragment.newInstance(shopKortrijk);
        fragmentTransaction.replace(R.id.container, shopKortrijkDetailsFragment).addToBackStack(null)
                .commit();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    /*
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    */
}
