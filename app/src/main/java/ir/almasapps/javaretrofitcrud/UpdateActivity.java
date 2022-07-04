package ir.almasapps.javaretrofitcrud;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.almasapps.javaretrofitcrud.API.Client;
import ir.almasapps.javaretrofitcrud.API.Service;
import ir.almasapps.javaretrofitcrud.Model.Note;
import ir.almasapps.javaretrofitcrud.Model.ResponseResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateActivity extends AppCompatActivity {
    private Service apiInterface;
    ProgressDialog progressDialog;
    EditText txtTitle,txtNote;
    Button btnUpdate,btnDelete;
    Note note;
    String strId,strColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        progressDialog = new ProgressDialog(this);
        txtTitle = findViewById(R.id.update_txtTitle);
        txtNote = findViewById(R.id.update_txtNote);
        btnUpdate = findViewById(R.id.update_btnUpdate);
        btnDelete = findViewById(R.id.update_btnDelete);

        note = new Note(
                getIntent().getStringExtra("itemId"),
                getIntent().getStringExtra("itemTitle"),
                getIntent().getStringExtra("itemNote"),
                getIntent().getStringExtra("itemColor"),"");
        strColor = getIntent().getStringExtra("itemColor");

        strId=note.getId();
        txtTitle.setText(note.getTitle());
        txtNote.setText(note.getNote());
        txtTitle.setBackgroundColor(Color.parseColor(strColor));
        txtNote.setBackgroundColor(Color.parseColor(strColor));


        btnUpdate.setOnClickListener( v -> {
            updateUser(strId,txtTitle.getText().toString(),txtNote.getText().toString(),strColor);
            progressDialog.setMessage("Updating ... \n Please wait ...");
            progressDialog.show();
        });
        btnDelete.setOnClickListener( v -> {
            deleteUser(note.getId());
            progressDialog.setMessage("Deleting ... \n Please wait ...");
            progressDialog.show();
        });

    }

    private void updateUser(String id,String title,String note,String color) {
        apiInterface = Client.getApiClient();
        Call<ResponseResult> call = apiInterface.updateUser(id,title,note,color);
        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(@NonNull Call<ResponseResult> call, @NonNull Response<ResponseResult> response) {
                progressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseResult> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Log.e("zzzzzzzzzz",t.toString());
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteUser(String id) {
        apiInterface = Client.getApiClient();
        Call<ResponseResult> call = apiInterface.deletetUser(id);
        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(@NonNull Call<ResponseResult> call, @NonNull Response<ResponseResult> response) {
                progressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseResult> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Log.e("zzzzzzzzzz",t.toString());
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getColor(View view) {
        ColorDrawable viewColor = (ColorDrawable) view.getBackground();
        int colorId = viewColor.getColor();
        strColor = String.format("#%06X", (0xFFFFFF & colorId));
        txtTitle.setBackgroundColor(colorId);
        txtNote.setBackgroundColor(colorId);
    }
}