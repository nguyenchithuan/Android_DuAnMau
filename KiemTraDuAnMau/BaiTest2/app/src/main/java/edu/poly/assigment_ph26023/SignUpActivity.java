package edu.poly.assigment_ph26023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.poly.assigment_ph26023.custom_toast.CustomToast;
import edu.poly.assigment_ph26023.dao.ThuThuDao;
import edu.poly.assigment_ph26023.objects.ThuThu;

public class SignUpActivity extends AppCompatActivity {
    private EditText edAddUser, edAddName, edAddPass, edAddRePass;
    private TextView tvBugAddUser, tvBugAddName, tvBugAddPass, tvBugAddRePass;
    private Button btnSave, btnCancel;
    private ThuThuDao dao;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edAddUser = findViewById(R.id.ed_add_user);
        edAddName = findViewById(R.id.ed_add_name);
        edAddPass = findViewById(R.id.ed_add_pass);
        edAddRePass = findViewById(R.id.ed_add_re_pass);
        tvBugAddUser = findViewById(R.id.tv_bug_add_user);
        tvBugAddName = findViewById(R.id.tv_bug_add_name);
        tvBugAddPass = findViewById(R.id.tv_bug_add_pass);
        tvBugAddRePass = findViewById(R.id.tv_bug_add_re_pass);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        dao = new ThuThuDao(this);
        pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
                clearBugForm();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        clearBugForm();
    }

    private void addUser() {
        if(validate()) {
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(edAddUser.getText().toString().trim());
            thuThu.setHoTenTT(edAddName.getText().toString().trim());
            thuThu.setMatKhau(edAddPass.getText().toString().trim());
            if(dao.insert(thuThu) > 0) { // nếu nhập trùng id thì xẩy ra lỗi, và khi đó không trả về index > 0
                CustomToast.showMessage(this, "Tạo tài khoản thành công");
                CustomToast.showMessage(this, "Chào mừng đến màn hình trính");
                writePref();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", edAddUser.getText().toString().trim());
                startActivity(intent);
            } else {
                CustomToast.showMessage(this,"Tạo tài khoản thất bại");
            }
        }

    }

    private void writePref() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("USERNAME", edAddUser.getText().toString().trim());
        editor.putString("PASSWORD", edAddPass.getText().toString().trim());
        editor.putBoolean("STATUS", true);
        editor.commit();
    }

    public boolean validate() {
        String strAddUser = edAddUser.getText().toString().trim();
        String strAddName = edAddName.getText().toString().trim();
        String strAddPass = edAddPass.getText().toString().trim();
        String strAddRePass = edAddRePass.getText().toString().trim();

        clearBugForm();

        if(strAddUser.isEmpty()) {
            tvBugAddUser.setText("Mời nhập dữ liệu");
            return false;
        } else if(strAddName.isEmpty()) {
            tvBugAddName.setText("Mời nhập dữ liệu");
            return false;
        } else if(strAddPass.isEmpty()) {
            tvBugAddPass.setText("Mời nhập dữ liệu");
            return false;
        } else if(strAddRePass.isEmpty()) {
            tvBugAddRePass.setText("Mời nhập dữ liệu");
            return false;
        } else if(strAddUser.length() < 5) {
            tvBugAddUser.setText("Tối thiểu 5 ký tự");
            return false;
        } else if(!strAddPass.equals(strAddRePass)) {
            tvBugAddRePass.setText("Mật khẩu không trùng khớp");
            return false;
        } else if(dao.checkUserName(strAddUser) > 0) { // mã đã tồn tại
            tvBugAddUser.setText("Tài khoản đã tồn tại");
            return false;
        }

        return true;
    }

    private void clearBugForm() {
        tvBugAddUser.setText("");
        tvBugAddName.setText("");
        tvBugAddPass.setText("");
        tvBugAddRePass.setText("");
    }

    private void clearForm() {
        edAddUser.setText("");
        edAddName.setText("");
        edAddPass.setText("");
        edAddRePass.setText("");
    }
}