package ir.almasapps.javaretrofitcrud.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("note")
    @Expose
    private String note;


    @SerializedName("color")
    @Expose
    private String color;


    @SerializedName("date")
    @Expose
    private String date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Note(String id, String title, String note, String color, String date) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.color = color;
        this.date = date;
    }
}
