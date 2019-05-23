package com.example.shopthree.View.DangNhap_DangKy.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopthree.Model.DangNhap_DangKy.KiemTraEmail_Model;
import com.example.shopthree.Model.ObjectClass.NhanVien;
import com.example.shopthree.Presenter.DangKy.PresenterLogicDangKy;
import com.example.shopthree.Presenter.TrangChu.XulyMenu.PresenterLogicXulyMenu;
import com.example.shopthree.R;
import com.example.shopthree.View.DangNhap_DangKy.ViewDangKy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnFocusChangeListener {

    PresenterLogicDangKy logicDangKy;
    EditText txt_newusername, txt_newuseremail, txt_newuserpass, txt_newuserpass_again, txt_newsdt;
    Button btndangky;
    SwitchCompat swemaildocquyen;
    TextInputLayout input_newusername, input_newuseremail, input_newuserpass, input_newuserpass_again, input_newsdt, input_noisinh;
    Boolean checkinfo = false;
    KiemTraEmail_Model kiemTraEmail_model;
    Spinner spNgaysinh, spThangsinh, spNamsinh, spGiotinh;
    TextView txtCheckgioitinh;
    AutoCompleteTextView txt_noisinh;

    List<String> dsNgay = new ArrayList<>();
    List<String> dsThang = new ArrayList<>();
    List<String> dsNam = new ArrayList<>();
    List<String> dsGioitinh = new ArrayList<>();
    String[] arrTinh;

    int positionNgay = 0, positionThang = 0, positionNam = 0, positionGiotinh = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmant_dangky_layout, container, false);

        addControl(view);
        addEvents();
        return view;
    }

    private void addEvents() {

        spNgaysinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionNgay = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spThangsinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionThang = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spNamsinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionNam = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spGiotinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionGiotinh = position;
                if(position == 0) {
                    txtCheckgioitinh.setText("*Bạn chưa chọn giới tính");
                }
                else
                {
                    txtCheckgioitinh.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txt_newusername.getText().toString();
                String email = txt_newuseremail.getText().toString();
                String pass = txt_newuserpass.getText().toString();
                String pass_again = txt_newuserpass_again.getText().toString();
                String emaildocquyen = "true";
                String sdt = txt_newsdt.getText().toString();
                String noisinh = txt_noisinh.getText().toString();
                if(!swemaildocquyen.isChecked())
                {
                    emaildocquyen = "false";
                }

                if(checkinfo && positionNgay !=0 && positionThang != 0 && positionNam != 0 && positionGiotinh != 0)
                {
                    String ngaysinh = dsNgay.get(positionNgay) + "/" + dsThang.get(positionThang) + "/" + dsNam.get(positionNam);
                    String gioitinh = dsGioitinh.get(positionGiotinh);

                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenNV(name);
                    nhanVien.setTenDangNhap(email);
                    nhanVien.setMatKhau(pass);
                    nhanVien.setEmaiDocQuyen(emaildocquyen);
                    nhanVien.setMaLoaNV(2);
                    nhanVien.setNgaySinh(ngaysinh);
                    nhanVien.setSodt(sdt);
                    nhanVien.setGioTinh(gioitinh);
                    nhanVien.setDiaChi(noisinh);

                    logicDangKy.ThucHienDangKy(nhanVien);
                }

                else if(positionNgay ==0 || positionThang == 0 || positionNam == 0)
                {
                    Toast.makeText(getContext(), "Bạn chưa nhập đầy đủ năm sinh", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addControl(View view) {
        logicDangKy = new PresenterLogicDangKy(this);
        txt_newusername = view.findViewById(R.id.txt_newusername);
        txt_newuseremail = view.findViewById(R.id.txt_newuseremail);
        txt_newuserpass = view.findViewById(R.id.txt_newuserpass);
        txt_newuserpass_again = view.findViewById(R.id.txt_newuserpass_again);
        txt_newsdt = view.findViewById(R.id.txt_newsdt);
        btndangky = view.findViewById(R.id.btndangky);
        swemaildocquyen = view.findViewById(R.id.swemaildocquyen);
        input_newusername = view.findViewById(R.id.input_newusername);
        input_newuseremail = view.findViewById(R.id.input_newuseremail);
        input_newuserpass = view.findViewById(R.id.input_newuserpass);
        input_newuserpass_again = view.findViewById(R.id.input_newuserpass_again);
        input_newsdt = view.findViewById(R.id.input_newsdt);
        spNgaysinh = view.findViewById(R.id.spNgaysinh);
        spThangsinh = view.findViewById(R.id.spThangsinh);
        spNamsinh = view.findViewById(R.id.spNamsinh);
        spGiotinh = view.findViewById(R.id.spGiotinh);
        txtCheckgioitinh = view.findViewById(R.id.txtCheckgioitinh);
        input_noisinh = view.findViewById(R.id.input_noisinh);
        txt_noisinh = view.findViewById(R.id.txt_noisinh);

        dsNgay.add("Ngày sinh");
        for(int i=1;i<32;i++)
        {
            dsNgay.add(i+"");
        }
        dsThang.add("Tháng sinh");
        for(int i=1;i<13;i++)
        {
            String thang = "";
            if(i<10)
            {
                thang += "0";
            }
            dsThang.add(thang + i);
        }
        dsNam.add("Năm sinh");
        for(int i=1950;i<2020;i++)
        {
            dsNam.add(i + "");
        }
        dsGioitinh.add("Giới tính");
        dsGioitinh.add("Nam");
        dsGioitinh.add("Nữ");
        dsGioitinh.add("Khác");
        dsGioitinh.add("Không muốn nói");

        arrTinh = getResources().getStringArray(R.array.listtinh);

        ArrayAdapter<String> adapterNgay = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dsNgay);
        ArrayAdapter<String> adapterThang = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dsThang);
        ArrayAdapter<String> adapterNam = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dsNam);
        ArrayAdapter<String> adapterGiotinh = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dsGioitinh);
        ArrayAdapter<String> adapterTinh = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrTinh);

        adapterNgay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterThang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterNam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterGiotinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spNgaysinh.setAdapter(adapterNgay);
        spThangsinh.setAdapter(adapterThang);
        spNamsinh.setAdapter(adapterNam);
        spGiotinh.setAdapter(adapterGiotinh);

        txt_noisinh.setAdapter(adapterTinh);

        kiemTraEmail_model = new KiemTraEmail_Model();

        txt_newusername.setOnFocusChangeListener(this);
        txt_newuseremail.setOnFocusChangeListener(this);
        txt_newuserpass_again.setOnFocusChangeListener(this);
    }

    private Boolean kiemtanoisinh(String noisinh)
    {
        for(int i=0;i<arrTinh.length;i++)
        {
            if(noisinh.equals(arrTinh[i]))
                return true;
        }
        return false;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id)
        {
            case R.id.txt_newusername:
                if(!hasFocus)
                {
                    String chuoi = ((EditText) v).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null))
                    {
                        input_newusername.setErrorEnabled(true);
                        input_newusername.setError("Bạn chưa nhập họ tên");
                        checkinfo = false;
                    }
                    else
                    {
                        input_newusername.setErrorEnabled(false);
                        input_newusername.setError("");
                        checkinfo = true;
                    }
                }
                break;
            case R.id.txt_newuseremail:
                if(!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    Boolean checkmail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_newuseremail.setErrorEnabled(true);
                        input_newuseremail.setError("Bạn chưa nhập email");
                        checkinfo = false;
                    } else {
                        if (!checkmail) {
                            input_newuseremail.setErrorEnabled(true);
                            input_newuseremail.setError("Email không hợp lệ");
                            checkinfo = false;
                        } else {
                            boolean checksamemail = kiemTraEmail_model.TrungEmail(txt_newuseremail.getText().toString());
                            if(!checksamemail)
                            {
                                input_newuseremail.setErrorEnabled(true);
                                input_newuseremail.setError("Email đã tồn tại");
                                checkinfo = false;
                            }
                            else {
                                input_newuseremail.setErrorEnabled(false);
                                input_newuseremail.setError("");
                                checkinfo = true;
                            }
                        }
                    }
                }
                break;
            case R.id.txt_newuserpass:
                break;
            case R.id.txt_newuserpass_again:
                if(!hasFocus) {
                    String pass_again = ((EditText) v).getText().toString();
                    String pass = txt_newuserpass.getText().toString();
                    if (!pass.equals(pass_again)) {
                        input_newuserpass_again.setErrorEnabled(true);
                        input_newuserpass_again.setError("Mật khẩu không trùng khớp");
                        checkinfo = false;
                    } else {
                        input_newuserpass_again.setErrorEnabled(false);
                        input_newuserpass_again.setError("");
                        checkinfo = true;
                    }
                }
                break;

            case R.id.txt_newsdt:
                if(!hasFocus)
                {
                    String chuoi = ((EditText) v).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null))
                    {
                        input_newsdt.setErrorEnabled(true);
                        input_newsdt.setError("Bạn chưa nhập số điện thoại");
                        checkinfo = false;
                    }
                    else
                    {
                        input_newsdt.setErrorEnabled(false);
                        input_newsdt.setError("");
                        checkinfo = true;
                    }
                }
                break;

            case R.id.spGiotinh:
                if(!hasFocus)
                {
                    if(positionGiotinh == 0)
                    {
                        txtCheckgioitinh.setText("*Bạn chưa chọn giới tính");
                    }
                    else
                    {
                        txtCheckgioitinh.setText("");
                    }
                }
                break;
        }
    }
}
