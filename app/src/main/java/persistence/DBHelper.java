package persistence;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "MovilStoreSQLiDB.db";
	public static final int DB_VERSION = 1;
	private static String DB_PATH = "/data/data/com.movilstore/databases/";
	
	protected SQLiteDatabase msdb;
	private final Context _context;
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		
		this._context = context;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public synchronized void close() {
		if(this.msdb != null)
			this.msdb.close();
		super.close();
	}
	
	private void createDataBase() throws IOException{
		
		if(!checkDB()){
			this.getWritableDatabase();
			this.copyDataBase();	
		}
		
	}
	
	private boolean checkDB(){
		
		SQLiteDatabase database = null;
		
		try{
			
			String path = DB_PATH + DB_NAME;
			database = SQLiteDatabase.openDatabase(path, 
					null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);
			
		}catch(SQLiteException e){
			Log.e("DBHelper", "Error al checkear la BD: " + e.toString());
		}
		
		if(database != null)
			database.close();
		
		return database != null ? true : false;
		
	}
	
	private void copyDataBase(){
		
		try {
			
			InputStream is = _context.getAssets().open(DB_NAME);
			String filename = DB_PATH + DB_NAME;
			
			OutputStream os = new FileOutputStream(filename);
			
			byte[] buffer = new byte[1024];
			int lenght;
			
			while ((lenght = is.read(buffer)) > 0){
				os.write(buffer, 0, lenght);
			}
			
			os.flush();
			os.close();
			is.close();
			
		} catch (IOException e) {
			Log.e("DBHelper", "Error al copiar la  BD: " + e.toString());
		}
		
	}
	
	public void openDataBase(){
		
		try {
			this.createDataBase();
		} catch (IOException e) {
			Log.e("DBHelper", "Error al crear la DB: " + e.toString());
		}
		
		this.msdb = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
		
	}
	
	
}
