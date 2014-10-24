package net.cozz.danco.homework2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by costd035 on 10/22/14.
 */
public class OptimizedCustomAdapter extends ArrayAdapter<String> {
    private static final String TAG = "OptimizedCustomAdapter";

    private Context context;
    private String[] values;


    public OptimizedCustomAdapter(Context context, int resource, String[] values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }

    public OptimizedCustomAdapter(Context context, String[] values) {
        super(context, R.layout.row_layout, values);
        this.context = context;
        this.values = values;
    }

    static class ViewHolder {
        public TextView stateName;
        public View leftBox;
        public View rightBox;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the view and put it in the holder
            Log.v(TAG, "inflating view.");
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.row_layout, null);

            viewHolder = new ViewHolder();

            Random rand = new Random(position);
            TextView textView = (TextView) convertView.findViewById(R.id.stateName);
            textView.setText(values[position]);
            Log.v(TAG, "Setting state name to : " + values[position]);
            viewHolder.stateName = textView;

            View leftBox = convertView.findViewById(R.id.leftBox);
            leftBox.setBackgroundColor(rand.nextInt());
            viewHolder.leftBox = leftBox;

            View right = convertView.findViewById(R.id.rightBox);
            right.setBackgroundColor(rand.nextInt());
            viewHolder.rightBox = right;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.rgb(231, 249, 255));
        } else {
            convertView.setBackgroundColor(Color.rgb(195, 240, 255));
        }

        viewHolder.stateName.setText(values[position]);

        return convertView;
    }
}
