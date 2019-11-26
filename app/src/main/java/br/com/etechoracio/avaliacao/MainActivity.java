package br.com.etechoracio.avaliacao;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editNum1;
    private EditText editNum2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNum1 = findViewById(R.id.editNum1);
        editNum2 = findViewById(R.id.editNum2);





    }

    public  void  onProcessar(View view){
        this.service = new Retrofit.Builder().baseUrl("http://172.16.58.22:8001/api/funcoes/subtracao/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(ProjetoAPIService.class);
        Call<String> call = this.service.executar(editNum1.getText().toString(), editNum2.getText().toString());
        call.enqueue(
                new Callback<String>() {


                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String resultado = response.body();


                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                    Log.e("ProjetoApiService ", "Erro:" + t.getMessage());
                    }
                }
        );

        }


    private ProjetoAPIService service;
}
