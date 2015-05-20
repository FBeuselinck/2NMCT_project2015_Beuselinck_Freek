package be.howest.nmct.desopdracht;


import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import be.howest.nmct.desopdracht.data.Contract;
import be.howest.nmct.desopdracht.data.ShopKortrijk;
import be.howest.nmct.desopdracht.data.ShopKortrijkLoader;

public class ShopKortrijkFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;
    private ShopKortrijkFragmentListener shopKortrijkFragmentListener;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        String[] columns = new String[]{
                Contract.ShopColumns.COLUMN_SHOP,
                Contract.ShopColumns.COLUMN_ADDRESS
        };

        int[] viewIds = new int[]{
            R.id.textViewShop,
            R.id.textViewAddress
        };

        this.adapter = new SimpleCursorAdapter(getActivity(), R.layout.row_shop_kortrijk, null, columns, viewIds);
        setListAdapter(this.adapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){
        return new ShopKortrijkLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor){
        this.adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader){
        this.adapter.swapCursor(null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try
        {
            this.shopKortrijkFragmentListener = (ShopKortrijkFragmentListener) activity;
        }
        catch(ClassCastException ex)
        {
            throw new ClassCastException(activity.toString() + " must implement ShopKortrijkFragmentListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Cursor cursor = this.adapter.getCursor();
        cursor.moveToPosition(position);

        ShopKortrijk shopKortrijk = new ShopKortrijk();
        shopKortrijk.setShop(cursor.getString(cursor.getColumnIndex(Contract.ShopColumns.COLUMN_SHOP)));
        shopKortrijk.setAddress(cursor.getString(cursor.getColumnIndex(Contract.ShopColumns.COLUMN_ADDRESS)));
        shopKortrijk.setPostcode(cursor.getString(cursor.getColumnIndex(Contract.ShopColumns.COLUMN_POSTCODE)));
        shopKortrijk.setDeelgemeente(cursor.getString(cursor.getColumnIndex(Contract.ShopColumns.COLUMN_DEELGEMEENTE)));

        float longitude = Float.parseFloat(cursor.getString(cursor.getColumnIndex(Contract.ShopColumns.COLUMN_LONGITUDE)));
        float latitude = Float.parseFloat(cursor.getString(cursor.getColumnIndex(Contract.ShopColumns.COLUMN_LATITUDE)));

        shopKortrijk.setLongitude(longitude);
        shopKortrijk.setLatitude(latitude);

        this.shopKortrijkFragmentListener.showShopKortrijkDetails(shopKortrijk);
    }

    public interface ShopKortrijkFragmentListener{
        public void showShopKortrijkDetails(ShopKortrijk shopKortrijk);
    }
}
