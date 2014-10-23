package net.cozz.danco.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by costd035 on 10/22/14.
 */
public class OptimizedCustomAdapter extends ArrayAdapter<String> {

    private static ViewHolder viewHolder;
    private Context context;

    public OptimizedCustomAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        viewHolder = new ViewHolder();
        this.context = context;
    }

    static class ViewHolder {
        static TextView oddView;
        static TextView evenView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // inflate the view and put it in the holder
            LayoutInflater inflater = LayoutInflater.from(context);

            if (position % 2 == 0) {
                convertView = inflater.inflate(R.layout.even_row_layout, null);
                ViewHolder.evenView = (TextView) convertView;
                convertView.setTag(viewHolder);
            } else {
                convertView = inflater.inflate(R.layout.odd_row_layout, null);
                ViewHolder.oddView = (TextView) convertView;
                convertView.setTag(viewHolder);
            }

        } else {
            convertView = position %2 == 0 ? ViewHolder.evenView : ViewHolder.oddView;
        }

        return convertView;
    }
}
