package com.example.tapetrove.Activity.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tapetrove.R;

import java.util.HashMap;
import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<HashMap<String, Object>> comments;

    public CommentAdapter(Context context, List<HashMap<String, Object>> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_comment, parent, false);
        }

        HashMap<String, Object> comment = comments.get(position);

        TextView usernameTextView = convertView.findViewById(R.id.username);
        TextView commentTextView = convertView.findViewById(R.id.comment);
        TextView ratingTextView = convertView.findViewById(R.id.rating);

        usernameTextView.setText((String) comment.get("username"));
        commentTextView.setText((String) comment.get("comment"));
        ratingTextView.setText("★ " + comment.get("rating"));
        return convertView;
    }
}

