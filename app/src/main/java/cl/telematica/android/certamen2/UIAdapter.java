package cl.telematica.android.certamen2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by italiano Leo on 18-12-2015.
 */
public class UIAdapter extends RecyclerView.Adapter<UIAdapter.ViewHolder> {

    private List<value> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mIdView;
        public TextView mjokeView;

        public ViewHolder(View v) {
            super(v);
            mIdView = (TextView) v.findViewById(R.id.ID_value);
            mjokeView = (TextView) v.findViewById(R.id.joke_value);
        }
    }

    public UIAdapter(List<value> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public UIAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        value value_obj = mDataset.get(position);

        holder.mIdView.setText("ID: "+value_obj.getId().toString());
        holder.mjokeView.setText(value_obj.getJoke());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
