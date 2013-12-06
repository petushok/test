package com.example.android.listviewscrolltrick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.listviewscrolltrick.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Yanaa
 * Date: 19.09.13
 */
public class ListAdapter extends ArrayAdapter<ListItem> {
    private ArrayList<ListItem> items;
    private LayoutInflater vi;
    private MapStickyView mapView;

    public ListAdapter(Context _context, ArrayList<ListItem> _items, MapStickyView _mapView) {
        super(_context, 0, _items);
        items = _items;
        mapView = _mapView;
        vi = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ListItem i = items.get(position);
        if (i != null) {

            //create style of item
            v = vi.inflate(R.layout.my_list_item_view, null);
            final TextView country = (TextView) v.findViewById(R.id.tvCountry);
            final TextView longitude = (TextView) v.findViewById(R.id.tvLong);
            final TextView latitude = (TextView) v.findViewById(R.id.tvLat);
            if (country != null)
                country.setText(i.country);
            if (longitude != null)
                longitude.setText(i.longitude);
            if (latitude != null)
                latitude.setText(i.latitude);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println();
                    float _lat = Float.valueOf(((TextView) v.findViewById(R.id.tvLat)).getText().toString());
                    float _long = Float.valueOf(((TextView) v.findViewById(R.id.tvLong)).getText().toString());
                    mapView.setMarker(_lat, _long);
                }
            });
        }

        return v;
    }
}
