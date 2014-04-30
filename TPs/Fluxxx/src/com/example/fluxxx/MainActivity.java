package com.example.fluxxx;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {


	public TextView tv;
	public View layoutMain;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }

        tv = (TextView) findViewById(R.id.textView2);
        tv.setBackgroundColor(Color.rgb(192, 0, 0));
        tv.setText("Pas assez rapide, petit scarabée!");

        Button bttn1 = (Button) findViewById(R.id.button1);
        bttn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				affiche();
			}
		});
        
        Button bttn2 = (Button) findViewById(R.id.button2);
        bttn2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				bckGrdColor(Color.rgb(192, 0, 0));
			}
		});
        
   }

    public void affiche(){
    	tv.setText(getString(R.string.tpacpc));
    }

    public void bckGrdColor(int colorParam){
        layoutMain = (View) findViewById(R.id.container);
        layoutMain.setBackgroundColor(colorParam);
    	tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,26);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
