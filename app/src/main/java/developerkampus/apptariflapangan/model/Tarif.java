package developerkampus.apptariflapangan.model;

/**
 * Created by Wambakun on 10/06/2017.
 */

public class Tarif {
    private String id,hari,jam,harga,hargamember;

    public Tarif(String id,String hari,String jam,String harga,String hargamember){
        this.id=id;
        this.hari=hari;
        this.jam=jam;
        this.harga=harga;
        this.hargamember=hargamember;
    }
    public Tarif(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getHargamember() {
        return hargamember;
    }

    public void setHargamember(String hargamember) {
        this.hargamember = hargamember;
    }
}
