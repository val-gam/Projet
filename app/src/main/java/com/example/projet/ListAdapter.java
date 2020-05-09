package com.example.projet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<r6_info> values;
    private ImageView imageView;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v) {
            super(v);

            layout = v;

            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            imageView = v.findViewById(R.id.imageView);
            //txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, r6_info item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<r6_info> myDataset,Context context) {

        values = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        final r6_info current_r6_info = values.get(position);
        holder.txtHeader.setText(current_r6_info.getNameCode());
        Glide.with(context).load(values.get(position).getEmbleme()).into(imageView);
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Info.class);
                intent.putExtra("NameCode", current_r6_info.getNameCode());
                intent.putExtra("Photo", current_r6_info.getPhoto());
                intent.putExtra("Affiliation", current_r6_info.getAffiliation());
                intent.putExtra("birthcountry", current_r6_info.getBirthcountry());
                intent.putExtra("Team", current_r6_info.getTeam());
                intent.putExtra("RealName", current_r6_info.getRealName());
                intent.putExtra("Birthdate", current_r6_info.getBirthdate());
                intent.putExtra("Embleme", current_r6_info.getEmbleme());
                //intent.putExtra("height", current_r6_info.getHeight());
                //intent.putExtra("weight", current_r6_info.getWeight());
                context.startActivity(intent);
                // holder.txtFooter.setText(current_r6_info.getAffiliation());
/*          holder.txtFooter.setText(current_r6_info.getBirthcountry());
            holder.txtFooter.setText(current_r6_info.getBirthdate());
            holder.txtFooter.setText(current_r6_info.getRealName());
            holder.txtFooter.setText(current_r6_info.getHeight());
            holder.txtFooter.setText(current_r6_info.getWeight());
            holder.txtFooter.setText(current_r6_info.getPhoto());*/


                //Detail activity
                // Picasso.with(this).load(imageURL).fit().centerInside().into(imageView);
            }

        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}

