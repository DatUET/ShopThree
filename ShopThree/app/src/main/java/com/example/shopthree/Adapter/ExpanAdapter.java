package com.example.shopthree.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopthree.Model.ObjectClass.Loaisanpham;
import com.example.shopthree.Model.TrangChu.XulyMenu.JsonMenu;
import com.example.shopthree.R;
import com.example.shopthree.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;

import java.util.List;

import static com.example.shopthree.R.drawable.custom_background;

public class ExpanAdapter extends BaseExpandableListAdapter {

    Context context;
    List<Loaisanpham> loaisanphams;
    ViewHolder viewHolder;

    public ExpanAdapter(Context context, List<Loaisanpham> loaisanphams) {
        this.context = context;
        this.loaisanphams = loaisanphams;

        JsonMenu jsonMenu = new JsonMenu();
        for(int i=0;i<loaisanphams.size();i++)
        {
            int maloai = loaisanphams.get(i).getMALOAISP();
            loaisanphams.get(i).setListCon(jsonMenu.getloaisptheomaloai(maloai));
        }
    }

    @Override
    public int getGroupCount() {
        return loaisanphams.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        if(loaisanphams.get(groupPosition).getListCon().size() !=0) {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return loaisanphams.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return loaisanphams.get(groupPosition).getListCon().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return loaisanphams.get(groupPosition).getMALOAISP();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return loaisanphams.get(groupPosition).getListCon().get(childPosition).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolder
    {
        TextView txttenloaisp;
        ImageView imgExpanded;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_group_cha_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txttenloaisp = view.findViewById(R.id.txttenloaiSP);
            viewHolder.imgExpanded = view.findViewById(R.id.imgisexpanded);

            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txttenloaisp.setText(loaisanphams.get(groupPosition).getTENLOAISP());

        int countChild = loaisanphams.get(groupPosition).getListCon().size();
        if(countChild > 0)
        {
            viewHolder.imgExpanded.setVisibility(View.VISIBLE);
        }
        else
        {
            viewHolder.imgExpanded.setVisibility(View.INVISIBLE);
        }

        if(isExpanded)
        {
            viewHolder.imgExpanded.setImageResource(R.drawable.icon_remove_black);
            view.setBackgroundResource(custom_background);
        }
        else
        {
            viewHolder.imgExpanded.setImageResource(R.drawable.icon_add_black);
            view.setBackgroundColor(Color.WHITE);
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HienThiSanPhamTheoDanhMucActivity hienThiSanPhamTheoDanhMucActivity = new HienThiSanPhamTheoDanhMucActivity();

                Bundle bundle = new Bundle();

                bundle.putInt("MALOAI", loaisanphams.get(groupPosition).getMALOAISP());
                bundle.putBoolean("CHECK", false);
                bundle.putString("TENLOAI", loaisanphams.get(groupPosition).getTENLOAISP());

                hienThiSanPhamTheoDanhMucActivity.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangchuActivity");
                fragmentTransaction.replace(R.id.themFragment, hienThiSanPhamTheoDanhMucActivity);
                fragmentTransaction.commit();

                return false;
            }
        });

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpanAdapter secondExpanAdapter = new ExpanAdapter(context, loaisanphams.get(groupPosition).getListCon());
        secondExpanable.setAdapter(secondExpanAdapter);
        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();
        return secondExpanable;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



    public class SecondExpanable extends ExpandableListView {

        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
