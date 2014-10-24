package net.cozz.danco.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by costd035 on 10/22/14.
 */
public class OptimizedCustomAdapter extends ArrayAdapter<String> {

    private static ViewHolder viewHolder;
    private Context context;
    private String[] values;



    public OptimizedCustomAdapter(Context context, String[] objects) {
        super(context, R.layout.odd_row_layout, objects);
        viewHolder = new ViewHolder();
        this.context = context;
        this.values = objects;
    }

    static class ViewHolder {
        static TextView oddView;
        static TextView evenView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = null;
        if (convertView == null) {
            // inflate the view and put it in the holder
            LayoutInflater inflater = LayoutInflater.from(context);

            if (position % 2 == 0) {
                rowView = inflater.inflate(R.layout.even_row_layout, parent);
                rowView.setTag(viewHolder);
            } else {
                rowView = inflater.inflate(R.layout.odd_row_layout, null);
                convertView.setTag(viewHolder);
            }

            TextView textView = (TextView) rowView.findViewById(R.id.stateName);
            View leftBox = rowView.findViewById(R.id.left);
            View right = rowView.findViewById(R.id.right);

        } else {
            convertView = position %2 == 0 ? ViewHolder.evenView : ViewHolder.oddView;
        }

        return convertView;
    }
}
