package com.example.yaourt;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class AccueilActivity extends Activity {

	//infos session stockees dans un fichier
	SharedPreferences cookie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);

		//recuperation de l'intent du login
		Intent intent = getIntent();
		
		//affichage du nom de l'utilisateur en cours
		cookie = getSharedPreferences("cookieSession", MODE_PRIVATE);
		this.setTitle("Hello, " + cookie.getString("userName", "valeur manquante"));
		//affichage du bouton add user si super utilisateur
		int superUser = cookie.getInt(getResources().getString(R.string.shPrefsSuperUser), 0);
		View v = findViewById(R.id.menu_homeAddUser); 
		//TODO nullpointerexception a pister
		v.setVisibility(superUser == 1 ? 0 : 4); 
		
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
		Intent pageSuivante;

		switch(id){
		case android.R.id.home:
			onBackPressed();
			return true;
		case R.id.menu_homeAddUser:
			//creer un utilisateur
			Log.i("Tuto", "on va creer une nouvelle entree dans BDD");
			pageSuivante = new Intent(this, SaisieUserActivity.class);
			pageSuivante.putExtra("action", "create");
			startActivityForResult(pageSuivante, 1);
			break;
		case R.id.menu_homeModifUser:
			//modifier utilisateur en cours
			Log.i("Tuto", "on va modifier l'utilisateur en cours");
			pageSuivante = new Intent(this, SaisieUserActivity.class);
			pageSuivante.putExtra("action", "modify");
			startActivityForResult(pageSuivante, 1);
			break;
//		case R.id.action_settings:
//			return true;
//			break;
		default:
			return super.onOptionsItemSelected(item);
		}

		return true;
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
