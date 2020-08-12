package com.example.swannavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.ViewHolder> {

    // List to store all the contact details
    private ArrayList<AddressView> addressList;
    private Context mContext;

    // Counstructor for the Class
    public RecyclerAdaptor(ArrayList<AddressView> listdata, Context context) {
        this.addressList = listdata;
        this.mContext = context;
    }
    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AddressView address = addressList.get(position);

        // Set the data to the views here
        holder.setDate(address.TripDate);
        holder.setDestination(address.Destination);
        holder.setOrigin(address.Origin);
        // You can set click listners to indvidual items in the viewholder here
        // make sure you pass down the listner or make the Data members of the viewHolder public

    }

    @Override
    public int getItemCount() {
        return addressList == null ? 0 : addressList.size();
    }
    // This method is called when binding the data to the views being created in RecyclerView

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView Origin;
        private TextView TripDate;
        private TextView Destination;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Origin = itemView.findViewById(R.id.origin);
            Destination = itemView.findViewById(R.id.destination);
            TripDate = itemView.findViewById(R.id.date);
        }

        public void setOrigin(String origin) {
            Origin.setText(origin);
        }


        public void setDestination(String destination) {
            Destination.setText(destination);
        }


        public void setDate(String tripdate) {
            TripDate.setText(tripdate);
        }

    }
}


