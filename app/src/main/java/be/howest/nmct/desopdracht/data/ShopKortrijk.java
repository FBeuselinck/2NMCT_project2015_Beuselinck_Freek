package be.howest.nmct.desopdracht.data;

import java.io.Serializable;

/**
 * Created by Eigenaar on 8/05/2015.
 */
public class ShopKortrijk implements Serializable {
    private String shop;
    private String address;
    private String postcode;
    private String deelgemeente;

    public ShopKortrijk(){
        super();
    }
    public ShopKortrijk(String shop, String address, String postcode, String deelgemeente){
        super();
        this.shop = shop;
        this.address = address;
        this.postcode = postcode;
        this.deelgemeente = deelgemeente;
    }

    public String getShop() { return shop; }
    public void setShop(String shop) { this.shop = shop; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getDeelgemeente() { return deelgemeente; }
    public void setDeelgemeente(String deelgemeente) { this.deelgemeente = deelgemeente; }
}
