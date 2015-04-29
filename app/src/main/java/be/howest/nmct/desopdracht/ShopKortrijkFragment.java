package be.howest.nmct.desopdracht;


import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import be.howest.nmct.desopdracht.data.Contract;
import be.howest.nmct.desopdracht.data.ShopKortrijkLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopKortrijkFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;

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


    /*
    private ShopKortrijkFragmentListener shopKortrijkFragmentListener;
    private ShopKortrijkAdapter shopKortrijkAdapter;

    public ShopKortrijkFragment() {
        // Required empty public constructor
    }

    public static ShopKortrijkFragment newInstance(){
        ShopKortrijkFragment shopKortrijkFragment = new ShopKortrijkFragment();
        return shopKortrijkFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_kortrijk, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.shopKortrijkAdapter = new ShopKortrijkAdapter();

        setListAdapter(shopKortrijkAdapter);
    }

    public interface ShopKortrijkFragmentListener{
        //public void showMainFragment
    }

    public class ShopKortrijkAdapter extends ArrayAdapter<Data.>
    */
}
