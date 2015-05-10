package aboot.tebaklagu.helper;

import java.util.ArrayList;
import java.util.List;

import aboot.tebaklagu.model.Lagu;
import aboot.tebaklagu.model.Nyawa;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LaguAdapter {

	LaguHelper laguHelper;
	Lagu lg;
	Nyawa nyaws;
	public LaguAdapter(Context context){
		laguHelper = new LaguHelper(context);
	}
	
	
	
	public int getPopLevel(int level){
		String countQuery = "SELECT * FROM pop WHERE level =" +level+"";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	public int getRockLevel(int level){
		String countQuery = "SELECT * FROM rock WHERE level =" +level+"";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	public int getDangdutLevel(int level){
		String countQuery = "SELECT * FROM dangdut WHERE level =" +level+"";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	public int getDaerahLevel(int level){
		String countQuery = "SELECT * FROM daerah WHERE level =" +level+"";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	
	
	
	
	
	
	public int getPopLevelstatus(int level){
		String countQuery = "SELECT * FROM pop WHERE level =" +level+" and status = 1";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	public int getRockLevelstatus(int level){
		String countQuery = "SELECT * FROM rock WHERE level =" +level+" and status = 1";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	public int getDangdutLevelstatus(int level){
		String countQuery = "SELECT * FROM dangdut WHERE level =" +level+" and status = 1";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	public int getDaerahLevelstatus(int level){
		String countQuery = "SELECT * FROM daerah WHERE level =" +level+" and status = 1";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	
	
	
	
	
	
	
	public List<Lagu> getSemuaLagu(String cate, int level) {
		List<Lagu> lagus = new ArrayList<Lagu>();

		String selectQuery = "SELECT  * FROM "+cate+" WHERE level = "+level+ " AND status = 0 ORDER BY RANDOM() LIMIT 1 ";

		//Log.e("", selectQuery);

		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Lagu lg = new Lagu();
				lg.setID(c.getInt((c.getColumnIndex(Lagu.KEY_ID))));
				lg.setIDCate(c.getInt(c.getColumnIndex(Lagu.KEY_cate)));
				lg.setMusik(c.getString(c.getColumnIndex(Lagu.KEY_musik)));
				lg.setJudul(c.getString(c.getColumnIndex(Lagu.KEY_judul)));
				lg.setLevel(c.getInt(c.getColumnIndex(Lagu.KEY_level)));
				lg.setStatus(c.getString(c.getColumnIndex(Lagu.KEY_status)));
				
				lagus.add(lg);
			} while (c.moveToNext());
		}

		return lagus;
	}
	public int resetLevel(String kate, int level){
		SQLiteDatabase db = laguHelper.getWritableDatabase();
		// updating row
		lg = new Lagu();
		ContentValues cv = new ContentValues();
		cv.put(Lagu.KEY_status, 0);
		return db.update(kate, cv, Lagu.KEY_level + " = ?",
				new String[] { String.valueOf(level) });
		
	}
	public List<Nyawa> getNyawa(int cate, int level){
		List<Nyawa> nyawas = new ArrayList<Nyawa>();
		String selectQuery = "SELECT  * FROM nyawa WHERE cate = "+cate+ " AND level = "+level+" ";
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do{
				Nyawa nyaws = new Nyawa();
				nyaws.setID(c.getInt(c.getColumnIndex(Nyawa.KEY_ID)));
				nyaws.setCate(c.getInt(c.getColumnIndex(Nyawa.KEY_CATE)));
				nyaws.setLevel(c.getInt(c.getColumnIndex(Nyawa.KEY_LEVEL)));
				nyaws.setJiwa(c.getInt(c.getColumnIndex(Nyawa.KEY_JIWA)));
				nyawas.add(nyaws);
			}while(c.moveToNext());
		}
		return nyawas;
	}
	public int updateNyawa(int id, int jiwa){
		SQLiteDatabase db = laguHelper.getWritableDatabase();
		// updating row
		nyaws = new Nyawa();
		ContentValues cv = new ContentValues();
		cv.put(Nyawa.KEY_JIWA, jiwa);
		return db.update("nyawa", cv, Nyawa.KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
	}
	
	
	
	
	
	
	
	
	public int updateMusik(String cate, int id) {
		SQLiteDatabase db = laguHelper.getWritableDatabase();
		// updating row
		lg = new Lagu();
		ContentValues cv = new ContentValues();
		cv.put(Lagu.KEY_status, 1);
		return db.update(cate, cv, Lagu.KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
	}
	public int ResetKate(String cate) {
		SQLiteDatabase db = laguHelper.getWritableDatabase();
		// updating row
		lg = new Lagu();
		ContentValues cv = new ContentValues();
		cv.put(Lagu.KEY_status, 0);
		return db.update(cate, cv, null, null);
	}
	
	
	
	public void closeDB() {
		SQLiteDatabase db = laguHelper.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
}