package com.qianfeng.cashbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 蔡灿武 on 2016/9/28.
 */

public class MyAdapter extends BaseAdapter{

    private Context context;
    private List<AccountBook> list;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<AccountBook> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=inflater.inflate(R.layout.lv_item,viewGroup,false);
            holder=new ViewHolder();
            holder.time = (TextView) view.findViewById(R.id.time);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.content = (TextView) view.findViewById(R.id.content);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        AccountBook book = list.get(i);
        holder.time.setText(book.getYear()+"-"+book.getMonth()+"-"+book.getDay());
        holder.title.setText(book.getTitle());
        holder.content.setText(book.getContent());

        return view;
    }
    class ViewHolder{
        TextView time,title,content;
    }
}