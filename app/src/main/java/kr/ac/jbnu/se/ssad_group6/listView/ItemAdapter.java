package kr.ac.jbnu.se.ssad_group6.listView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kr.ac.jbnu.se.ssad_group6.Item;
import kr.ac.jbnu.se.ssad_group6.R;

public class ItemAdapter extends BaseAdapter {
    List<Item> visited;
    View view;

    ItemAdapter(List<Item> todo){
        this.visited = todo;
    }

    private static class ViewHolder {
        TextView date;
        TextView time;
        TextView year;
        TextView place;
    }

    @Override
    public int getCount() {
        return visited.size();
    }

    @Override
    public Object getItem(int position) {
        return visited.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_item, null);

            ItemAdapter.ViewHolder holder = new ItemAdapter.ViewHolder();
            holder.date = (TextView)view.findViewById(R.id.date);
            holder.time = (TextView) view.findViewById(R.id.time);
            holder.year = (TextView)view.findViewById(R.id.year);
            holder.place = (TextView)view.findViewById(R.id.place);

            view.setTag(holder);
        }

        Item addItem = visited.get(position);

        if (addItem != null) {
            ItemAdapter.ViewHolder holder = (ItemAdapter.ViewHolder)view.getTag();
            holder.date.setText(addItem.getMonth());
            holder.time.setText(addItem.getTime());
            holder.year.setText(addItem.getYear());
            holder.place.setText(addItem.getPlace());
        }

        ItemAdapter.ViewHolder holder = (ItemAdapter.ViewHolder)view.getTag();

        return view;
    }

}

