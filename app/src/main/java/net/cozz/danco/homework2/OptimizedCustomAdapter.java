package net.cozz.danco.homework2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by costd035 on 10/22/14.
 */
public class OptimizedCustomAdapter extends ArrayAdapter<String> {

    private static ViewHolder viewHolder;
    private Context context;
    private String[] values;


    public OptimizedCustomAdapter(Context context, int resource, String[] values) {
        super(context, resource, values);
        viewHolder = new ViewHolder();
        this.context = context;
        this.values = values;
    }

    public OptimizedCustomAdapter(Context context, String[] values) {
        super(context, R.layout.activity_my, values);
        viewHolder = new ViewHolder();
        this.context = context;
        this.values = values;
    }

    static class ViewHolder {
        static View view;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // inflate the view and put it in the holder
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_my, null);
            viewHolder.view = convertView;
        } else {
            convertView = viewHolder.view;
        }

        Random rand = new Random(position);
        TextView textView = (TextView) convertView.findViewById(R.id.stateName);
        textView.setText(values[position]);

        View leftBox = convertView.findViewById(R.id.left);
        leftBox.setBackgroundColor(rand.nextInt());

        View right = convertView.findViewById(R.id.right);
        right.setBackgroundColor(rand.nextInt());

        if (position % 2 == 0) {
            convertView.setTag(viewHolder);
            convertView.setBackgroundColor(Color.rgb(231, 249, 255));
        } else {
            convertView.setTag(viewHolder);
            convertView.setBackgroundColor(Color.rgb(195, 240, 255));
        }

        return convertView;
    }
}
