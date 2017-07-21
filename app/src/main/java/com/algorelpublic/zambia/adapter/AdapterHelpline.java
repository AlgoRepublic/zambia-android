package com.algorelpublic.zambia.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.model.HelpLineModel;
import com.algorelpublic.zambia.model.SearchResultModel;

import java.util.ArrayList;

/**
 * Created by android on 7/19/17.
 */

public class AdapterHelpline extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<HelpLineModel.Helpline> mList;

    public AdapterHelpline(Context context, ArrayList<HelpLineModel.Helpline> tagsLinkedList) {
        mList = tagsLinkedList;
        mContext = context;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private TextView tvContactNumber, tvTitle, tvAddress;


        public ItemViewHolder(View view) {
            super(view);
            tvContactNumber = (TextView) view.findViewById(R.id.tvContactNumber);
            tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_headline, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder mHolder, final int position) {
        final ItemViewHolder holder = (ItemViewHolder) mHolder;
        try {
            holder.tvContactNumber.setText(mList.get(0).contactNo);
            holder.tvTitle.setText(mList.get(0).title);
            holder.tvAddress.setText(mList.get(0).address + ", " + mList.get(0).city
                    + ", " + mList.get(0).country);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

}