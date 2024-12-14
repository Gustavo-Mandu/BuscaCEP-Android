package com.mandu.buscacepapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep;
    EditText txtCep;
    TextView tvw1Resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBuscarCep = findViewById(R.id.btnBuscaCep);
        txtCep = findViewById(R.id.txtCep);
        tvw1Resposta = findViewById(R.id.tvw1Resposta);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCep.length() == 8) {
                    try {
                        CEP retorno = new HTTPService(txtCep.getText().toString().trim()).execute().get();
                        if (retorno.getComplemento().isEmpty()) {
                            retorno.setComplemento("Vazio.");
                            tvw1Resposta.setText(retorno.toString());
                        } else tvw1Resposta.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Insira um CEP válido.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}