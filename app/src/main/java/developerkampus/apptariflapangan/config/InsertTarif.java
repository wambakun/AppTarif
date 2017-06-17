package developerkampus.apptariflapangan.config;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import developerkampus.apptariflapangan.MainActivity;

/**
 * Created by Wambakun on 10/06/2017.
 */

public class InsertTarif extends AsyncTask<String,Void,String> {
    MainActivity R = new MainActivity();
    private Context context;
    private String link = Http.url;

    public InsertTarif(Context context){
        this.context=context;
    }
    @Override
    protected String doInBackground(String... strings) {
        String Hari = strings[0];
        String Jam = strings[1];
        String Harga = strings[2];
        String HargaMember = strings[3];

        String data;
        BufferedReader bufferedReader;
        String result;
        try{
            data = "?hari="+ URLEncoder.encode(Hari,"UTF-8");
            data += "&jam="+URLEncoder.encode(Jam,"UTF-8");
            data += "&harga="+URLEncoder.encode(Harga,"UTF-8");
            data += "&harga_member="+URLEncoder.encode(HargaMember,"UTF-8");

            link = link +"insert_harga.php"+data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result=bufferedReader.readLine();
            return  result;

        }catch (Exception e){
            return new String("Exception"+e.getMessage());
        }
    }
    @Override
    protected void onPostExecute(String result){
        String jsonStr = result;

        if(jsonStr!=null){
            try{
                JSONObject jsonObject = new JSONObject(jsonStr);
                String query_result = jsonObject.getString("result");
                if(query_result.equals("berhasil")){
                    Toast.makeText(context,"Data Tarif Berhasil Disimpan",Toast.LENGTH_LONG).show();
                    ((Activity)(context)).finish();
                }else if(query_result.equals("gagal")){
                    Toast.makeText(context,query_result,Toast.LENGTH_LONG).show();
                }else{
                    Log.e("result",query_result);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else{
            Log.e("Error ","Couldn't get any JSON data");
        }
    }
}
