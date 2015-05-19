package be.howest.nmct.desopdracht;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import be.howest.nmct.desopdracht.data.ShopKortrijk;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    private static final String SHOPKORTRIJK_KEY = "shopkortrijk_key";
    private ShopKortrijk shopKortrijk;
    private static View view;
    private GoogleMap googleMap;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(ShopKortrijk shopKortrijk){
        MapFragment mapFragment = new MapFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(SHOPKORTRIJK_KEY, shopKortrijk);
        mapFragment.setArguments(bundle);

        return mapFragment;
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
        if(view != null){
            ViewGroup parent = (ViewGroup) view.getParent();
            if(parent != null)
                parent.removeView(view);
        }

        try{
            view = inflater.inflate(R.layout.fragment_map, container, false);
        }
        catch(InflateException ex){
            Log.d("An error occured: ", ex.getMessage());
        }

        this.googleMap = ((com.google.android.gms.maps.MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
        placeMarker();

        return view;
    }

    private void placeMarker()
    {
        // Create marker.
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(this.shopKortrijk.getLatitude(), this.shopKortrijk.getLongitude()))
                .title(this.shopKortrijk.getShop())
                .flat(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        // Add marker to map.
        googleMap.addMarker(markerOptions);

        //Setting the camera position to the marker.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(this.shopKortrijk.getLatitude(), this.shopKortrijk.getLongitude()))
                .zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
