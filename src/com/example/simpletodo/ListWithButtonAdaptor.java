package com.example.simpletodo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListWithButtonAdaptor extends ArrayAdapter<String> {
	private Context context;
	private int resource;
	private List<String> data;

	static class ViewHolder{
		TextView textView;
		Button button;
	}
	public ListWithButtonAdaptor(Context context, int resource,
			List<String> data) {
		super(context, resource, data);
		this.context = context;
		this.resource = resource;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public String getItem(int position) {
		return data.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null){
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = vi.inflate(resource, null);
			
			holder = new ViewHolder();
			holder.textView = (TextView) convertView.findViewById(R.id.rowView);
			holder.button = (Button) convertView.findViewById(R.id.rowButton);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.textView.setText(data.get(position));
		holder.button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// remove
			}
		});
		return convertView;
	}

}
