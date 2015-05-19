package be.howest.nmct.desopdracht;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import be.howest.nmct.desopdracht.data.ShopKortrijk;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopKortrijkDetailsFragment extends Fragment {

    private static final String SHOPKORTRIJK_KEY = "shopkortrijk_key";
    private ShopKortrijk shopKortrijk;

    private TextView textViewShop;
    private TextView textViewStreet;
    private TextView textViewTown;
    private Button btnShowMap;

    private MapFragmentListener mapFragmentListener;

    public ShopKortrijkDetailsFragment() {
        // Required empty public constructor
    }

    public static ShopKortrijkDetailsFragment newInstance(ShopKortrijk shopKortrijk){
        ShopKortrijkDetailsFragment shopKortrijkDetailsFragment = new ShopKortrijkDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(SHOPKORTRIJK_KEY, shopKortrijk);
        shopKortrijkDetailsFragment.setArguments(bundle);

        return shopKortrijkDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(getActivity() != null){
            shopKortrijk = (ShopKortrijk) getArguments().getSerializable(SHOPKORTRIJK_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop_kortrijk_details, container, false);

        this.textViewShop = (TextView) v.findViewById(R.id.textViewShop);
        this.textViewStreet = (TextView) v.findViewById(R.id.textViewStreet);
        this.textViewTown = (TextView) v.findViewById(R.id.textViewTown);

        showShopKortrijkDetails();

        btnShowMap = (Button) v.findViewById(R.id.btnShowMap);
        btnShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragmentListener.showMap(shopKortrijk);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try
        {
            this.mapFragmentListener = (MapFragmentListener) activity;
        }
        catch(ClassCastException ex)
        {
            throw new ClassCastException(activity.toString() + " must implement MapFragmentListener");
        }
    }

    private void showShopKortrijkDetails(){
        this.textViewShop.setText(shopKortrijk.getShop());
        this.textViewStreet.setText(shopKortrijk.getAddress());
        this.textViewTown.setText(shopKortrijk.getPostcode() + " " + shopKortrijk.getDeelgemeente());
    }

    public interface MapFragmentListener{
        public void showMap(ShopKortrijk shopKortrijk);
    }
}
