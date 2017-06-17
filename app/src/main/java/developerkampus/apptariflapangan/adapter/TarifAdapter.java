package developerkampus.apptariflapangan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import developerkampus.apptariflapangan.R;
import developerkampus.apptariflapangan.model.Tarif;

/**
 * Created by Wambakun on 10/06/2017.
 */

public class TarifAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;
    private List<Tarif> tarifList;

    public TarifAdapter(Activity activity,List<Tarif> tarifList){
        this.activity=activity;
        this.tarifList=tarifList;
    }
    @Override
    public int getCount() {
        return tarifList.size();
    }

    @Override
    public Object getItem(int i) {
        return tarifList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater==null)
            inflater=(LayoutInflater)activity.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if(view==null)
            view =inflater.inflate(R.layout.list_tarif,null);

        TextView TvId = (TextView)view.findViewById(R.id.tv_idtarif);
        TextView TvHari = (TextView)view.findViewById(R.id.tv_hari);
        TextView TvJam = (TextView)view.findViewById(R.id.tv_jam);
        TextView TvHarga = (TextView)view.findViewById(R.id.tv_harga);
        TextView TvHargaMember = (TextView)view.findViewById(R.id.tv_hargamember);

        Tarif t = tarifList.get(i);
        TvId.setText(t.getId());
        TvHari.setText(t.getHari());
        TvJam.setText(t.getHarga());
        TvHarga.setText(t.getHarga());
        TvHargaMember.setText(t.getHargamember());

        return view;
    }
}
