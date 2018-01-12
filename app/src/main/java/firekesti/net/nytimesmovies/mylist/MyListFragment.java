package firekesti.net.nytimesmovies.mylist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import firekesti.net.nytimesmovies.R;

/**
 * A Fragment for the "My List" screen
 */
public class MyListFragment extends Fragment {

    public static MyListFragment newInstance() {
        MyListFragment fragment = new MyListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_list, container, false);
        RecyclerView myList = view.findViewById(R.id.my_list);
        myList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        myList.setAdapter(new MyListAdapter());
        return view;
    }
}
