package ir.almasapps.javaretrofitcrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.almasapps.javaretrofitcrud.API.Client;
import ir.almasapps.javaretrofitcrud.API.Service;
import ir.almasapps.javaretrofitcrud.Model.ResponseResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {

    Button btnInsert;
    ProgressDialog progressDialog;
    EditText txtTitle,txtNote;
    String strColor = "#FFEBEE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btnInsert = findViewById(R.id.insert_btnInsert);
        txtTitle = findViewById(R.id.insert_txtTitle);
        txtNote = findViewById(R.id.insert_txtNote);


        progressDialog = new ProgressDialog(this);

        btnInsert.setOnClickListener(v -> {

            insertUser(txtTitle.getText().toString(),txtNote.getText().toString(),strColor);
            progressDialog.setMessage("Inserting ... \n Please wait ...");
            progressDialog.show();
        });
    }

    private void insertUser(String username,String password,String email) {
        Service apiInterface = Client.getApiClient();
        Call<ResponseResult> call = apiInterface.insertUser(username,password,email);
        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(@NonNull Call<ResponseResult> call, @NonNull Response<ResponseResult> response) {
                progressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseResult> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getColor(View view) {
        ColorDrawable viewColor = (ColorDrawable) view.getBackground();
        int colorId = viewColor.getColor();
        strColor= String.format("#%06X", (0xFFFFFF & colorId));
        txtTitle.setBackgroundColor(colorId);
        txtNote.setBackgroundColor(colorId);
    }
}