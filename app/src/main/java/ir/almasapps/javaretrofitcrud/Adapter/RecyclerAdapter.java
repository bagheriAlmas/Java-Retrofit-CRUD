package ir.almasapps.javaretrofitcrud.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ir.almasapps.javaretrofitcrud.Model.Note;
import ir.almasapps.javaretrofitcrud.R;
import ir.almasapps.javaretrofitcrud.UpdateActivity;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private final List<Note> list;
    Context context;
    public RecyclerAdapter(List<Note> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note data = list.get(position);
        holder.txtTitle.setText(data.getTitle());
        holder.txtNote.setText(data.getNote());
        holder.txtDate.setText(data.getDate());
        holder.layout.setBackgroundColor(Color.parseColor(data.getColor()));

        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("itemId",data.getId());
            intent.putExtra("itemTitle",data.getTitle());
            intent.putExtra("itemNote",data.getNote());
            intent.putExtra("itemColor",data.getColor());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle,txtNote,txtDate;
        public RelativeLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = itemView.findViewById(R.id.list_item_txtTitle);
            this.txtNote = itemView.findViewById(R.id.list_item_txtNote);
            this.txtDate = itemView.findViewById(R.id.list_item_txtDate);

            layout = itemView.findViewById(R.id.list_item_layout);
        }
    }
}