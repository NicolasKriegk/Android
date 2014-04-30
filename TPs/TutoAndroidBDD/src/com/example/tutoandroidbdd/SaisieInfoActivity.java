package com.example.tutoandroidbdd;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SaisieInfoActivity extends Activity implements OnClickListener {

	private long idInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saisie_info);

//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}

		Button saisieBDD = (Button) findViewById(R.id.btnSaisieInfoBDD);
		saisieBDD.setOnClickListener(this);
		findViewById(R.id.btnAnnulSaisieInfoBDD).setOnClickListener(this);
	
		//init
		idInfo = -1;
		saisieBDD.setText(R.string.btnCreerInfoBDD);
		
		//recuperation de l'intent page accueil
		Intent intent = getIntent();
		//recuperation intent pour traitement page 
		if(intent.hasExtra("@string/extraBDDFld_IdInfo")){
				//changement du texte du bouton
				saisieBDD.setText(R.string.btnModifInfoBDD);
			
				//recuperation cle element clique
				EditText eTxt = (EditText) findViewById(R.id.editSaisieInfoBDD);
				eTxt.setText(intent.getStringExtra("@string/extraBDDFld_Info"));
				idInfo= intent.getLongExtra("@string/extraBDDFld_IdInfo", -1);
				//nettoyage de l'intent
				intent.removeExtra("@string/extraBDDFld_Info");
				intent.removeExtra("@string/extraBDDFld_IdInfo");
		}
		
	}

	@Override
	public void onClick(View v) {
		
		int vId = v.getId();
		switch(vId){
		case R.id.btnSaisieInfoBDD:
			EditText eTxt = (EditText) findViewById(R.id.editSaisieInfoBDD);
			String info = eTxt.getText().toString();
			BDD bdd = new BDD();
			bdd.open(this);
			long result;
			if(idInfo==-1){
				//creation entree base
				result = bdd.createInfo(info);
				if(result>0) finish();
			}
			else{
				//modification entree base
				bdd.modifInfo(idInfo, info);
				finish();
			}
			
			
			break;
		case R.id.btnAnnulSaisieInfoBDD:
			finish();
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saisie_info, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_saisie_info,
					container, false);
			return rootView;
		}
	}

}
