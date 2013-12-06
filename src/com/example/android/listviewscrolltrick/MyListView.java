/**
 * Created with IntelliJ IDEA.
 * User: Yanaa
 * Date: 18.09.13
 */

package com.example.android.listviewscrolltrick;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import com.example.listviewscrolltrick.R;

import java.util.ArrayList;

public class MyListView extends ListView {
    private ArrayList<ListItem> items = new ArrayList<ListItem>();

    public MyListView(Context context, MapStickyView mapView) {
        super(context);

        createItems();

        //create style of list
        setBackgroundColor(Color.argb(255, 255, 255, 255));
        setDivider(new ColorDrawable(0x88999999));
        setDividerHeight(1);
        setVerticalScrollBarEnabled(false);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerView = inflater.inflate(R.layout.top_layout, null);
        addHeaderView(headerView);

        setAdapter(new ListAdapter(context, items, mapView));
    }

    private void createItems() {
        items.add(new ListItem("US", "38.883333", "-77.016667"));
        items.add(new ListItem("China", "39.916667", "116.383333"));
        items.add(new ListItem("United Kingdom", "51.5", "-0.116667"));
        items.add(new ListItem("Germany", "52.516667", "13.383333"));
        items.add(new ListItem("Korea", "38.316667", "127.233333"));
        items.add(new ListItem("India", "28.613333", "77.208333"));
        items.add(new ListItem("Russia", "55.75", "37.616667"));
        items.add(new ListItem("France", "48.856667", "2.350833"));
        items.add(new ListItem("Canada", "45.4", "-75.666667"));

        //This itemlist is duplicate of the first one. It's here just to make list longer.
        items.add(new ListItem("US", "38.883333", "-77.016667"));
        items.add(new ListItem("China", "39.916667", "116.383333"));
        items.add(new ListItem("United Kingdom", "51.5", "-0.116667"));
        items.add(new ListItem("Germany", "52.516667", "13.383333"));
        items.add(new ListItem("Korea", "38.316667", "127.233333"));
        items.add(new ListItem("India", "28.613333", "77.208333"));
        items.add(new ListItem("Russia", "55.75", "37.616667"));
        items.add(new ListItem("France", "48.856667", "2.350833"));
        items.add(new ListItem("Canada", "45.4", "-75.666667"));
    }
}
