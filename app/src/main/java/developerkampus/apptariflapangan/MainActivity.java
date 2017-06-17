package developerkampus.apptariflapangan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import developerkampus.apptariflapangan.activity.TarifActivity;
import developerkampus.apptariflapangan.adapter.TarifAdapter;
import developerkampus.apptariflapangan.config.Http;
import developerkampus.apptariflapangan.model.Tarif;

public class MainActivity extends AppCompatActivity {
    private List<Tarif> tarifList;
    private ListView listView;
    private TarifAdapter adapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String link = "list_tarif.php";
    FloatingActionButton fabTarif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tarifList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv_tarif);
        adapter = new TarifAdapter(this, tarifList);
        listView.setAdapter(adapter);
        fabTarif = (FloatingActionButton) findViewById(R.id.fab_tarif);
        loaddata();

        fabTarif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TarifActivity.class));
            }
        });

    }

    public void loaddata() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Http.url + link,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                final Tarif tarif = new Tarif();
                                tarif.setId(obj.getString("id_tarif"));
                                tarif.setHari(obj.getString("hari"));
                                tarif.setJam(obj.getString("jam"));
                                tarif.setHarga(obj.getString("harga"));
                                tarif.setHargamember(obj.getString("harga_member"));

                                tarifList.add(tarif);
                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                adapter.notifyDataSetChanged();
                                Log.d(TAG, String.valueOf(tarif));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(jsonArrayRequest);

    }
}
