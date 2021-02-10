package com.example.pet_calendar_0130;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter  extends SimpleCursorAdapter {
    private Cursor c; // DB에서 가져온 Data를 가리키는 Cursor.
    private int layout; // list의 Layout
    private Context context; // ListView의 context
    private String[] from; // DB 필드 이름
    private int[] to; // DB 필드에 대응되는 component의 id

    public MyCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.c = c;
        this.layout = layout;
        this.context = context;
        this.from = from;
        this.to = to;
    }

    public View getView(int pos, View inView, ViewGroup parent) {
        View v = inView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layout, null);
        }
        c.moveToPosition(pos);

        String struri = c.getString(c.getColumnIndex(from[0]));
        String txt = c.getString(c.getColumnIndex(from[1]));

        ImageView imageView = (ImageView) v.findViewById(to[0]);
        if (struri != null) { imageView.setImageURI(Uri.parse(struri)); }

        TextView textView = (TextView) v.findViewById(to[1]);
        textView.setText(txt);

        return (v);
    }
}