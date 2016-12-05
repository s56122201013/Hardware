package hardware.Computer_Hardware;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class Hdd extends AppCompatActivity {
    //Explicit
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hdd);
        //Bind widget
        listView = (ListView) findViewById(R.id.listview);
        SynJSON sysJSON = new SynJSON();
        sysJSON.execute();



    }//onCreate

    //Create inner class
    public class SynJSON extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String strURL ="http://www.csclub.ssru.ac.th/s56122201013/adproject/php_get_hdd.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();




            }catch (Exception e){
                Log.d("Cpu","doIn ==>"+ e.toString());
                return null;

            }

        }//doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                String a ="http://www.csclub.ssru.ac.th/s56122201013/add/myfile/";
                Log.d("Cpu","Response ==>"+s);
                JSONArray jsonArray= new JSONArray(s);
                String[] iconString = new  String[jsonArray.length()];
                String[] nameStrings = new String[jsonArray.length()];
                String[] desString = new  String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    iconString[i]= a + jsonObject.getString("pic");
                    nameStrings[i]=jsonObject.getString("des");
                    desString[i]=jsonObject.getString("name");
                }// for
                Adapter Adapter = new Adapter(Hdd.this,
                        iconString,nameStrings,desString);
                listView.setAdapter(Adapter);
            }catch (Exception e){
                Log.d("Cpu","onPost ==>" + e.toString());
            }
        }
    }//onPostExecute

}//mainclass