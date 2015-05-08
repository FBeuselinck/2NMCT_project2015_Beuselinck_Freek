package be.howest.nmct.desopdracht.data;

import android.provider.BaseColumns;

/**
 * Created by Eigenaar on 27/04/2015.
 */
public class Contract {
    public interface ShopColumns extends BaseColumns {
        public static final String COLUMN_SHOP = "bedrijfsnaam";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_POSTCODE = "postcode";
        public static final String COLUMN_DEELGEMEENTE = "deelgemeente";
    }
}
