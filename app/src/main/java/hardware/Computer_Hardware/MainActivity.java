package hardware.Computer_Hardware;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hardware.Computer_Hardware.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private UserTABLE objUserTABLE;
    private TABLE objTABLE;

    private MySQLite mySQLite;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind widget
        userEditText = (EditText) findViewById(R.id.user);
        passwordEditText = (EditText) findViewById(R.id.password);

        //Connected SQLite
        connectedSQLite();



        synAndDelete();

        mySQLite = new MySQLite(this);
    }   //onCreate

    public void clickSignInMain(View view){

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //check space
        if (userString.equals("") || passwordString.equals("")){
            //have space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกข้อมูลให้ครบ");
        } else {
            //on space
            CheckUser();
        }
    }   //clickSignInMain

    private void CheckUser(){
        try{
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MySQLiteOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User = " + "'" + userString + "'", null);
            cursor.moveToFirst();
            String[] resultString = new String[cursor.getColumnCount()];
            for (int i=0;i<cursor.getColumnCount();i++){
                resultString[i] = cursor.getString(i);
            }
            cursor.close();

            //check password
            if (passwordString.equals(resultString[2])){
                Toast.makeText(this, "ยินดีต้อนรับ" + resultString[3], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, hardware.class);
                intent.putExtra("Result", resultString);
                startActivity(intent);
            } else {
                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(this, "รหัสผ่านไม่ถูกต้อง", "กรุณาใส่รหัสผ่านอีกครั้ง");
            }   //check password
        } catch (Exception e){
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ไม่มี user นี้", "ไม่มี" + userString + "ในฐานข้อมูลสมาชิก");
        }
    } //CheckUser

    private void synAndDelete(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MySQLiteOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        sqLiteDatabase.delete(MySQLite.user_table, null, null);
        MySynJSON mySynJSON = new MySynJSON();
        mySynJSON.execute();
    }   //SynAndDelete

    //Create Inner Class for Connected JSON
    public class MySynJSON extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids){
            try{
                String strURL = "http://www.csclub.ssru.ac.th/s56122201013/adproject/php_get_userTABLE.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (Exception e){
                Log.d("Hardware", "doInBack ==> " + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            Log.d("Hardware", "strJSON ==> " + s);
            try{
                JSONArray jsonArray = new JSONArray(s);
                for (int i=0; i<jsonArray.length(); i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strUser = jsonObject.getString(MySQLite.column_user);
                    String strPassword = jsonObject.getString(MySQLite.column_password);
                    String strName = jsonObject.getString(MySQLite.column_name);
                    mySQLite.addNewUser(strUser, strPassword, strName);
                }   //for

                }catch (Exception e){
                Log.d("Hardware","onPost ==> " + e.toString());
            }
        }   //onPostExecute
    }   //MySynJSON



    private void connectedSQLite(){
        objUserTABLE = new UserTABLE(this);
        objTABLE = new TABLE(this);

    }   //connectedSQLite

    public void clickSignUpMain(View view){
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    } //clickSignUpMain

}   //Main Class
