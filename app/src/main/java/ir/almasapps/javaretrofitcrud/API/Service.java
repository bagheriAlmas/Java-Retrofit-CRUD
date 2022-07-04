package ir.almasapps.javaretrofitcrud.API;

import java.util.List;

import ir.almasapps.javaretrofitcrud.Model.Note;
import ir.almasapps.javaretrofitcrud.Model.ResponseResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    // Get All Data
    @GET("json.php")
    Call<List<Note>> getNotes();

    @GET("insert.php")
    Call<ResponseResult> insertUser(@Query("title") String title,
                                    @Query("note") String note,
                                    @Query("color") String color);

    @GET("update.php")
    Call<ResponseResult> updateUser(@Query("id") String id,
                                    @Query("title") String title,
                                    @Query("note") String note,
                                    @Query("color") String color);

    @GET("delete.php")
    Call<ResponseResult> deletetUser(@Query("id") String id);

}