package developerkampus.apptariflapangan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import developerkampus.apptariflapangan.R;
import developerkampus.apptariflapangan.config.InsertTarif;

public class TarifActivity extends AppCompatActivity {
    EditText EtHari,EtJam,EtHarga,EtHargaMember;
    Button BtnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif);
        EtHari  = (EditText)findViewById(R.id.et_hari);
        EtJam = (EditText)findViewById(R.id.et_jam);
        EtHarga = (EditText)findViewById(R.id.et_harga);
        EtHargaMember = (EditText)findViewById(R.id.et_hargamember);
        BtnSimpan = (Button)findViewById(R.id.btn_simpan);

        BtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InsertTarif(TarifActivity.this).execute(
                        EtHari.getText().toString(),EtJam.getText().toString(),EtHarga.getText().toString(),EtHargaMember.getText().toString()
                );
            }
        });
    }
}
