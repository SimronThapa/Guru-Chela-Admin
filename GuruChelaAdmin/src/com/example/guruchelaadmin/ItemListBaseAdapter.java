package com.example.guruchelaadmin;

import java.util.ArrayList;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ItemListBaseAdapter extends BaseAdapter {

	private static ArrayList<ItemDetails> itemDetailsrrayList;
	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.list_row, null);
			holder = new ViewHolder();
			holder.txt_itemName1 = (TextView) convertView
					.findViewById(R.id.tvcollege);
			holder.txt_itemName = (TextView) convertView
					.findViewById(R.id.tvaddress);
			holder.txt_itemDescription = (TextView) convertView
					.findViewById(R.id.tvphone);
			holder.txt_itemDescription1 = (TextView) convertView
					.findViewById(R.id.tvwebsite);
			holder.txt_itemDescription2 = (TextView) convertView
					.findViewById(R.id.tvemail);
			holder.txt_itemDescription3 = (TextView) convertView
					.findViewById(R.id.tvbox);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txt_itemName1.setText(itemDetailsrrayList.get(position)
				.getCollege());
		holder.txt_itemName.setText(itemDetailsrrayList.get(position)
				.getAddress());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position)
				.getPhone());
		holder.txt_itemDescription1.setText(itemDetailsrrayList.get(position)
				.getWebsite());
		holder.txt_itemDescription2.setText(itemDetailsrrayList.get(position)
				.getEmail());
		holder.txt_itemDescription3.setText(itemDetailsrrayList.get(position)
				.getBox());

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemName1;
		TextView txt_itemDescription;
		TextView txt_itemDescription1;
		TextView txt_itemDescription2;
		TextView txt_itemDescription3;
	}
}
