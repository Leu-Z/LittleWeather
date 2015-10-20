package com.leu.littleweather.ui.cityaddui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.GroupMemberBean;

import java.util.List;

/**
 * Created by Leu on 2015/10/18.
 */
public class SortGroupMemberAdapter extends BaseAdapter implements SectionIndexer {
    private List<GroupMemberBean> list = null;
    private Context mContext;

    public SortGroupMemberAdapter(Context mContext, List<GroupMemberBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<GroupMemberBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final GroupMemberBean mContent = list.get(position);
        //缓存机制
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_group_member_item, null);
            viewHolder.tvCity = (TextView) view.findViewById(R.id.city);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.tvPrefecture= (TextView) view.findViewById(R.id.prefecture);
            viewHolder.tvProvince= (TextView) view.findViewById(R.id.province);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // 根据position获取bean的首字母的Char ascii值
        int section = getSectionForPosition(position);

        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            //第一次出现的字母的bean，会显示两行，包括改bean的首字母
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
        //一般都只显示一行。
        viewHolder.tvCity.setText(this.list.get(position).getCity());
        viewHolder.tvProvince.setText(this.list.get(position).getProvince());
        viewHolder.tvPrefecture.setText(this.list.get(position).getPrefecture());

        return view;

    }

    final static class ViewHolder {
        TextView tvLetter;
        TextView tvCity;
        TextView tvPrefecture;
        TextView tvProvince;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     * 例如： 如果该position上面的name是阿妹，首字母就是A，
     * 那么此方法返回的就是'A'字母的ascii值，也就是65， 'B'是66，依次类推
     */
    public int getSectionForPosition(int position) {
        //得到该位置bean，然后得到该bean的首字母
        return list.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     * 比如，返回第一次首字母是A的bean的位置，如阿妹的位置。
     */
    public int getPositionForSection(int section) {
        //循环遍历所有bean，取得第一次与传入字母字符相同的bean的位置。
        for (int i = 0; i < getCount(); i++) {
            //得到bean的首字母
            String sortStr = list.get(i).getSortLetters();
            //转为大写的字符
            char firstChar = sortStr.toUpperCase().charAt(0);
            //如果与当前位置的bean的首字母相同，就返回当前bean的位置
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}