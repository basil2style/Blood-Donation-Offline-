package com.makeinfo.giveblood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.makeinfo.giveblood.model.User;

import java.util.List;

/**
 * Created by Basil on 8/9/2015.
 */
public class adapter extends ArrayAdapter<User> {

    Context context;
    private List<User> userList;
    public adapter(Context context, int resource,List<User> users) {
        super(context, resource,users);
        this.context = context;
        this.userList = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item,parent,false);
        final User user = userList.get(position);
        TextView  name = (TextView) view.findViewById(R.id.nameTV);
        TextView mobile = (TextView) view.findViewById(R.id.mobTV);
        name.setText(user.name);
        mobile.setText(user.contactNo);

      view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(Intent.ACTION_DIAL);
              intent.setData(Uri.parse("tel:"+user.contactNo));
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              context.startActivity(intent);

          }
      });

        return view;

    }
}
