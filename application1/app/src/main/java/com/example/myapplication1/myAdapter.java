package com.example.myapplication1;
import android.content.Context;
import android.graphics.Paint;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myAdapter extends BaseAdapter {
    private Context mContext;
    //private List<String> mList = new ArrayList<>();

    List<Map<String, Object>> mList=new ArrayList<Map<String,Object>>();
    private Map<Integer,Boolean> map=new HashMap<>();
    public myAdapter(Context context, List<Map<String, Object>> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item,null);
            viewHolder.titleTextView = (TextView) view.findViewById(R.id.title);
            viewHolder.timeTextView = (TextView) view.findViewById(R.id.time);
            viewHolder.mButton = (ImageButton) view.findViewById(R.id.imageButton);
            viewHolder.checkBox=(CheckBox) view.findViewById(R.id.checkBox);
            view.setTag(viewHolder);
        } else {
           viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.titleTextView.setText(mList.get(i).get("title").toString());
        viewHolder.timeTextView.setText(mList.get(i).get("time").toString());
        final CheckBox checkBox=(CheckBox)view.findViewById(R.id.checkBox);
        final TextView titleText=(TextView) view.findViewById(R.id.title);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    map.put(i,true);
                    titleText.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
                    Log.e("hello","why"+i);
                }else {
                    map.remove(i);

                }
            }
        });
        if(map!=null&&map.containsKey(i)){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        viewHolder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemDeleteListener.onDeleteClick(i);
            }
        });

//        viewHolder.checkBox.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                mOnItemCheckListener.onCheckClick(i);
//            }
//        });
        return view;
    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }


//    public interface onItemCheckListener {
//        void onCheckClick(int i);
//    }
//
//    private onItemCheckListener mOnItemCheckListener;
//
//    public void setOnItemCheckClickListener(onItemCheckListener mOnItemCheckListener) {
//        this.mOnItemCheckListener = mOnItemCheckListener;
//    }

    class ViewHolder {
        TextView titleTextView;
        TextView timeTextView;
        ImageButton mButton;
        CheckBox checkBox;
    }

}