package com.algorelpublic.zambia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.model.SearchResultModel;

import java.util.ArrayList;

/**
 * Created by adilnazir on 16/07/2017.
 */

public class AdapterResult extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<SearchResultModel.Results> mList;

    public AdapterResult(Context context, ArrayList<SearchResultModel.Results> tagsLinkedList) {
        mList = tagsLinkedList;
        mContext = context;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvTitle, tvResult;


        public ItemViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            tvResult = (TextView) v.findViewById(R.id.tvResult);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_result_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder mHolder, final int position) {
        final ItemViewHolder holder = (ItemViewHolder) mHolder;
        try {
            holder.tvTitle.setText("Person " + (position + 1) + " Result");
            holder.tvResult.setText(Html.fromHtml(mList.get(position).search_result));
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

}