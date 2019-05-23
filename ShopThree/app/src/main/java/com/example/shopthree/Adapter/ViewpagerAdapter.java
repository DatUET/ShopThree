package com.example.shopthree.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shopthree.View.TrangChu.Fragment.FragmentChuongtrinhkhuyenmai;
import com.example.shopthree.View.TrangChu.Fragment.FragmentDientu;
import com.example.shopthree.View.TrangChu.Fragment.FragmentLamdep;
import com.example.shopthree.View.TrangChu.Fragment.FragmentMevabe;
import com.example.shopthree.View.TrangChu.Fragment.FragmentNhacuavadoisong;
import com.example.shopthree.View.TrangChu.Fragment.FragmentNoibat;
import com.example.shopthree.View.TrangChu.Fragment.FragmentThethaovadulich;
import com.example.shopthree.View.TrangChu.Fragment.FragmentThoitrang;
import com.example.shopthree.View.TrangChu.Fragment.FragmentThuonghieu;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listfragment = new ArrayList<>();
    List<String> titlefragment = new ArrayList<>();

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);

        listfragment.add(new FragmentNoibat());
        listfragment.add(new FragmentChuongtrinhkhuyenmai());
        listfragment.add(new FragmentDientu());
        listfragment.add(new FragmentLamdep());
        listfragment.add(new FragmentMevabe());
        listfragment.add(new FragmentNhacuavadoisong());
        listfragment.add(new FragmentThethaovadulich());
        listfragment.add(new FragmentThoitrang());
        listfragment.add(new FragmentThuonghieu());

        titlefragment.add("Nổi bật");
        titlefragment.add("Chương trình khuyến mãi");
        titlefragment.add("Điện tử");
        titlefragment.add("Làm đẹp");
        titlefragment.add("Mạ và bé");
        titlefragment.add("Nhà cửa và đời sống");
        titlefragment.add("Thể thao và du lịch");
        titlefragment.add("Thời trang");
        titlefragment.add("Thương hiệu");

    }

    @Override
    public Fragment getItem(int i) {
        return listfragment.get(i);
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlefragment.get(position);
    }
}
