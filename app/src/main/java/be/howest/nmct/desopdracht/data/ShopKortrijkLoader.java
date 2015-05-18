package be.howest.nmct.desopdracht.data;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.JsonReader;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Eigenaar on 27/04/2015.
 */
public class ShopKortrijkLoader extends AsyncTaskLoader<Cursor> {

    private Cursor cursor;
    private final String[] columnNames = new String[]{
            BaseColumns._ID,
            Contract.ShopColumns.COLUMN_SHOP,
            Contract.ShopColumns.COLUMN_ADDRESS,
            Contract.ShopColumns.COLUMN_POSTCODE,
            Contract.ShopColumns.COLUMN_DEELGEMEENTE,
            Contract.ShopColumns.COLUMN_LONGITUDE,
            Contract.ShopColumns.COLUMN_LATITUDE
    };
    private static Object lock = new Object();
    private final String url = "http://data.kortrijk.be/middenstand/winkels_markten.json";

    public ShopKortrijkLoader(Context context){ super(context); }

    @Override
    protected void onStartLoading(){
        if(cursor != null)
            deliverResult(cursor);

        if(takeContentChanged() || cursor == null)
            forceLoad();
    }

    @Override
    public Cursor loadInBackground(){
        if(cursor == null)
            loadCursor();

        return cursor;
    }

    private void loadCursor(){
        synchronized (lock){
            if(cursor != null)
                return;

            MatrixCursor matrixCursor = new MatrixCursor(columnNames);
            InputStream inputStream = null;
            JsonReader jsonReader = null;

            try{
                inputStream = new URL(url).openStream();
                jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

                int id = 1;

                jsonReader.beginObject();
                jsonReader.nextName();
                jsonReader.beginArray();
                while(jsonReader.hasNext()){
                    jsonReader.beginObject();

                    String shop = "";
                    String address = "";
                    String postcode = "";
                    String deelgemeente = "";
                    String longitude = "";
                    String latitude = "";

                    while(jsonReader.hasNext()){
                        String name = jsonReader.nextName();

                        if(name.equals(Contract.ShopColumns.COLUMN_SHOP)){
                            jsonReader.beginObject();
                            jsonReader.nextName();
                            shop = jsonReader.nextString();
                            jsonReader.endObject();
                        }
                        else if(name.equals(Contract.ShopColumns.COLUMN_ADDRESS)){
                            jsonReader.beginObject();
                            jsonReader.nextName();
                            address = jsonReader.nextString();
                            jsonReader.endObject();
                        }
                        else if(name.equals(Contract.ShopColumns.COLUMN_POSTCODE)){
                            jsonReader.beginObject();
                            jsonReader.nextName();
                            postcode = jsonReader.nextString();
                            jsonReader.endObject();
                        }
                        else if(name.equals(Contract.ShopColumns.COLUMN_DEELGEMEENTE)){
                            jsonReader.beginObject();
                            jsonReader.nextName();
                            deelgemeente = jsonReader.nextString();
                            jsonReader.endObject();
                        }
                        else if(name.equals(Contract.ShopColumns.COLUMN_LONGITUDE)){
                            jsonReader.beginObject();
                            jsonReader.nextName();
                            longitude = jsonReader.nextString();
                            jsonReader.endObject();
                        }
                        else if(name.equals(Contract.ShopColumns.COLUMN_LATITUDE)){
                            jsonReader.beginObject();
                            jsonReader.nextName();
                            latitude = jsonReader.nextString();
                            jsonReader.endObject();
                        }
                        else
                            jsonReader.skipValue();
                    }

                    MatrixCursor.RowBuilder row = matrixCursor.newRow();
                    row.add(id);
                    row.add(shop);
                    row.add(address);
                    row.add(postcode);
                    row.add(deelgemeente);
                    row.add(longitude);
                    row.add(latitude);
                    id++;

                    jsonReader.endObject();
                }
                jsonReader.endArray();
                jsonReader.endObject();

                cursor = matrixCursor;
            }

            catch(Exception ex){
                Log.d("Exception occured: ", ex.getMessage());
            }

            finally{
                try{
                    jsonReader.close();
                }

                catch(Exception ex){
                    Log.d("Exception occured: ", ex.getMessage());
                }

                try{
                    inputStream.close();
                }

                catch(Exception ex){
                    Log.d("Exception occured: ", ex.getMessage());
                }
            }
        }
    }
}
