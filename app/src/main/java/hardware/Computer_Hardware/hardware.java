package hardware.Computer_Hardware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Nontakarn on 3/12/2559.
 */

public class hardware extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardware);
    }

    public void Onclickimg1(View view){
        ImageButton img1 = (ImageButton)findViewById(R.id.img1);
        Intent intent  = new Intent(hardware.this, Cpu.class);
        startActivity(intent );
    }
    public void Onclickimg2(View view) {
        ImageButton img2 = (ImageButton) findViewById(R.id.img2);
        Intent intent = new Intent(hardware.this, Mainbroad.class);
        startActivity(intent);
    }
    public void Onclickimg3(View view) {
        ImageButton img3 = (ImageButton) findViewById(R.id.img3);
        Intent intent = new Intent(hardware.this, Ram.class);
        startActivity(intent);
    }
    public void Onclickimg4(View view) {
        ImageButton img4 = (ImageButton) findViewById(R.id.img4);
        Intent intent = new Intent(hardware.this, VGA.class);
        startActivity(intent);
    }
    public void Onclickimg5(View view) {
        ImageButton img5 = (ImageButton) findViewById(R.id.img5);
        Intent intent = new Intent(hardware.this, Hdd.class);
        startActivity(intent);
    }
    public void Onclickimg6(View view) {
        ImageButton img6 = (ImageButton) findViewById(R.id.img6);
        Intent intent = new Intent(hardware.this, Psu.class);
        startActivity(intent);
    }
    public void Onclickimg7(View view) {
        ImageButton img7 = (ImageButton) findViewById(R.id.img7);
        Intent intent = new Intent(hardware.this, Monitor.class);
        startActivity(intent);
    }
    public void Onclickimg8(View view) {
        ImageButton img8 = (ImageButton) findViewById(R.id.img8);
        Intent intent = new Intent(hardware.this, Mouse.class);
        startActivity(intent);
    }
    public void Onclickimg9(View view) {
        ImageButton img9 = (ImageButton) findViewById(R.id.img9);
        Intent intent = new Intent(hardware.this, Keybroad.class);
        startActivity(intent);
    }

    }

