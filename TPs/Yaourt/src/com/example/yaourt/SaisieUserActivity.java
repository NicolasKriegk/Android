package com.example.yaourt;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.yaourt.bdd.BDD_Users;

public class SaisieUserActivity extends Activity implements OnClickListener {

	//infos session stockees dans un fichier
	private SharedPreferences cookie;

	private Intent intent;

	//Champs
	private EditText eTxtName;
	private EditText eTxtLogin;
	private EditText eTxtPassw;
	private CheckBox chkBxSuperUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saisie_user);

//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}

		Button saisieBDD = (Button) findViewById(R.id.btnWriteUserBDD);
		saisieBDD.setOnClickListener(this);
		findViewById(R.id.btnAnnulWriteUserBDD).setOnClickListener(this);
	
		//init
		saisieBDD.setText(R.string.btnWriteUserBDD);
		
		//recuperation intent source pour traitement page 
		Intent intent = getIntent();
		if(intent.hasExtra("@string/extra_action")
				& "modify".equals(intent.getStringExtra("@string/extra_action"))
			){
				//changement du texte du bouton
				saisieBDD.setText(R.string.btnModifUserBDD);
			
				//recuperation des infos utilisateur en cours pour affichage
				//source sharedPreferences
				cookie = getSharedPreferences("cookieSession", MODE_PRIVATE);
				
				//affichage du login de l'utilisateur en cours
				eTxtName = (EditText) findViewById(R.id.editSaisieUserLogin);
				eTxtName.setText(cookie.getString("@string/userLogin", "erreur de lecture"));

				//affichage du nom de l'utilisateur en cours
				eTxtLogin = (EditText) findViewById(R.id.editSaisieUserName);
				eTxtLogin.setText(cookie.getString("@string/userName", "erreur de lecture"));

				//affichage du role de l'utilisateur en cours (super ou pas)
				chkBxSuperUser = (CheckBox) findViewById(R.id.checkBoxSuperUser);
				chkBxSuperUser.setChecked(
						cookie.getInt("@string/superUser", 0)==1 ? true : false);
		}
		
	}

	@Override
	public void onClick(View v) {
		
		//elements pour controles de saisie
		boolean cancel = false;
		View focusView = null;

		int vId = v.getId();
		switch(vId){
		case R.id.btnWriteUserBDD:
			//ouverture BDD
			BDD_Users bdd = new BDD_Users();
			bdd.open(this);

			//recuperation des nouvelles infos utilisateur pour ecriture dans BDD
			//et preparation des infos de l'utilisateur a rentrer dans la base
			String[] user = new String[]{};
			eTxtName = (EditText) findViewById(R.id.editSaisieUserName);
			eTxtLogin = (EditText) findViewById(R.id.editSaisieUserLogin);
			eTxtPassw = (EditText) findViewById(R.id.editSaisieUserPassw);
			chkBxSuperUser = (CheckBox) findViewById(R.id.checkBoxSuperUser);

			user[0] = eTxtName.getText().toString();
			user[1] = eTxtLogin.getText().toString();
			user[2] = eTxtPassw.getText().toString();
			user[3] = chkBxSuperUser.isChecked() ? "1" : "0" ;
			
			//verification des infos saisies ------------------------------------------------
			// Check for a valid password.
			if (TextUtils.isEmpty(user[2])) {
				eTxtPassw.setError(getString(R.string.error_field_required));
				focusView = eTxtPassw;
				cancel = true;
			} else if (eTxtPassw.getText().toString().length() < 4) {
				eTxtPassw.setError(getString(R.string.error_invalid_password));
				focusView = eTxtPassw;
				cancel = true;
			}

			// Check for a valid login.
			//TODO verifier si login pas utilise si creation nouvel utilisateur
			//				R.string.error_used_login
			if (TextUtils.isEmpty(eTxtLogin.getText().toString())) {
				eTxtLogin.setError(getString(R.string.error_field_required));
				focusView = eTxtLogin;
				cancel = true;
			}

			if (cancel) {
				// There was an error; don't attempt login and focus the first
				// form field with an error.
				focusView.requestFocus();
				return;
			}

			
			//traitement dans la base --------------------------------------------------------------
			long result = 0;

			//creation dans la base
			if(intent.hasExtra("@string/extra_action")
					& "create".equals(intent.getStringExtra("@string/extra_action"))
			){
				result = bdd.createUser(user);
			}

			//modification entree base
			else if(intent.hasExtra("@string/extra_action")
					& "modify".equals(intent.getStringExtra("@string/extra_action"))
			){
				bdd.modifUser(cookie.getString("@string/userLogin", "erreur de lecture"), user);
				//actualisation de l'utilisateur courant dans la "session" suite a la modif dans base
				cookie.edit().putString("@string/userLogin", eTxtLogin.getText().toString()).commit();

				result = 1;
			}
			
			//nettoyage de l'intent
			intent.removeExtra("@string/extra_action");
			
			//sortie de l'activite
			if(result>0) {
				finish();
			}
			
			break;
		case R.id.btnAnnulWriteUserBDD:
			finish();
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu., menu);
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

//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_saisie_info,
//					container, false);
//			return rootView;
//		}
	}

}
