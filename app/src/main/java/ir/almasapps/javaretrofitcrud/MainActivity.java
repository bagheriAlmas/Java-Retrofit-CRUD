package ir.almasapps.javaretrofitcrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ir.almasapps.javaretrofitcrud.API.Client;
import ir.almasapps.javaretrofitcrud.API.Service;
import ir.almasapps.javaretrofitcrud.Adapter.RecyclerAdapter;
import ir.almasapps.javaretrofitcrud.Model.Note;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnInsert;
    ProgressDialog progressDialog;
    List<Note> notesList ;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btnInsert);
        progressDialog = new ProgressDialog(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));


        btnInsert.setOnClickListener(v ->
                startActivity(new Intent(getBaseContext(),InsertActivity.class))
        );


    }

    private void fetchInformation() {
        Service apiInterface = Client.getApiClient();
        Call<List<Note>> call = apiInterface.getNotes();
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(@NonNull Call<List<Note>> call, @NonNull Response<List<Note>> response) {
                progressDialog.dismiss();
                notesList = response.body();
                recyclerView.setAdapter(new RecyclerAdapter(notesList,getBaseContext()));

            }

            @Override
            public void onFailure(@NonNull Call<List<Note>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Log.e("zzzzzzzzzz",t.toString());
                Toast.makeText(getBaseContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh) {
            progressDialog.setMessage("Fetching Data ... \n Please wait ...");
            progressDialog.show();
            fetchInformation();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressDialog.setMessage("Fetching Data ... \n Please wait ...");
        progressDialog.show();
        fetchInformation();
    }
}
