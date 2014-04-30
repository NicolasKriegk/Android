package com.example.fluxxx;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Accueil extends Activity {

//	private Button startCalc;
	private String nom;
//	private Intent pageCalculatrice = new Intent(this, Calculatrice.class);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);

		nom = "";
		final Intent pageCalculatrice = new Intent(this, Calculatrice.class);
		
		//Recuperation objets
		Button startCalc = (Button) findViewById(R.id.buttonStartCalc);
        //Definition des actions des boutons
		startCalc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText edTxt = (EditText) findViewById(R.id.saisieNom);
				nom = edTxt.getText().toString(); 
				//Intent pageCalculatrice = new Intent(this, Calculatrice.class);
				pageCalculatrice.putExtra("@string/dataNom", nom);
				startActivity(pageCalculatrice);
			}
		});

	
	
	
	
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueil, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_accueil,
					container, false);
			return rootView;
		}
	}

}
