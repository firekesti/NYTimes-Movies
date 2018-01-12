package firekesti.net.nytimesmovies.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import firekesti.net.nytimesmovies.MyListStore;
import firekesti.net.nytimesmovies.R;

/**
 * An adapter for a My List view
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<String> ids = new ArrayList<>();

    MyListAdapter() {
        ids.addAll(MyListStore.getInstance().getAllIds());
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, parent, false);
        return new MyListAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final MyListAdapter.ViewHolder holder, int position) {
        holder.bind(ids.get(position));
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
        }


        void bind(String id) {
            title.setText(MyListStore.getInstance().getTitleForId(id));
        }
    }
}
