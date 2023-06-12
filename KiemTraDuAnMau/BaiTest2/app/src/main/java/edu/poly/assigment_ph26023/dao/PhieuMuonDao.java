package edu.poly.assigment_ph26023.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import edu.poly.assigment_ph26023.dbhelper.DBHelper;
import edu.poly.assigment_ph26023.objects.PhieuMuon;
import edu.poly.assigment_ph26023.objects.Sach;
import edu.poly.assigment_ph26023.objects.ThuThu;

public class PhieuMuonDao {

    private SQLiteDatabase db;

    public PhieuMuonDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<PhieuMuon> getData(String sql, String ...selection) {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selection);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                PhieuMuon phieuMuon = new PhieuMuon();

                phieuMuon.setMaPM(cursor.getInt(0));
                phieuMuon.setMaTT(cursor.getString(1));
                phieuMuon.setMaTV(cursor.getInt(2));
                phieuMuon.setMaSach(cursor.getInt(3));
                phieuMuon.setTienThue(cursor.getInt(4));
                phieuMuon.setNgayThue(cursor.getString(5));
                phieuMuon.setTraSach(cursor.getInt(6));

                list.add(phieuMuon);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public ArrayList<PhieuMuon> getAll() {
        ArrayList<PhieuMuon> list =  new ArrayList<>();
        list = getData("SELECT * FROM PhieuMuon");
        return list;
    }

    public PhieuMuon getOne(String id) {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        list = getData("SELECT * FROM PhieuMuon WHERE maPM = ?", id);

        return list.get(0);
    }

    public long insert(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("maTT", phieuMuon.getMaTT());
        values.put("maTV", phieuMuon.getMaTV());
        values.put("maSach", phieuMuon.getMaSach());
        values.put("tienThue", phieuMuon.getTienThue());
        values.put("ngayThue", phieuMuon.getNgayThue());
        values.put("traSach", phieuMuon.getTraSach());
        return db.insert("PhieuMuon", null, values);
    }

    public int update(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("maTT", phieuMuon.getMaTT());
        values.put("maTV", phieuMuon.getMaTV());
        values.put("maSach", phieuMuon.getMaSach());
        values.put("tienThue", phieuMuon.getTienThue());
        values.put("ngayThue", phieuMuon.getNgayThue());
        values.put("traSach", phieuMuon.getTraSach());
        return db.update("PhieuMuon", values, "maPM = ?", new String[] {phieuMuon.getMaPM() + ""});
    }

    public int delete(String id) {
        return db.delete("PhieuMuon", "maPM = ?", new String[] {id});
    }

    public ArrayList<PhieuMuon> getSearch(String data) {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM PhieuMuon WHERE maTT LIKE '%' || ? || '%'", new String[]{data});
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                PhieuMuon phieuMuon = new PhieuMuon();

                phieuMuon.setMaPM(cursor.getInt(0));
                phieuMuon.setMaTT(cursor.getString(1));
                phieuMuon.setMaTV(cursor.getInt(2));
                phieuMuon.setMaSach(cursor.getInt(3));
                phieuMuon.setTienThue(cursor.getInt(4));
                phieuMuon.setNgayThue(cursor.getString(5));
                phieuMuon.setTraSach(cursor.getInt(6));

                list.add(phieuMuon);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
