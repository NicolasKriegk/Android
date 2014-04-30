package com.example.tutoandroidbdd;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class TutoAndroidBDDActivity extends ListActivity {

	//variable globale associee a l'activité
	private BDD maBase;
	private Cursor cListeInfos;
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}

		BDD maBase = new BDD();
		maBase.open(this);
		cListeInfos = maBase.getInfos();
		startManagingCursor(cListeInfos);
		cListeInfos.requery();
		//preparation des infos de l'adapteur entre le curseur et le ListView
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					android.R.layout.simple_list_item_1,
					cListeInfos,
					new String[]{"info"},
					new int[] {android.R.id.text1});
		//mise a jour de la liste
		setListAdapter(adapter);
		
	}

	//methode pour traiter le clic sur les elements de la liste
	protected void onListItemClick(final ListView lv, View v, int position, long idInfo){
		super.onListItemClick(lv, v, position, idInfo);
		Log.i("Tuto", "clic sur " + idInfo);
		Intent pageSaisieInfoBDD = new Intent(this, SaisieInfoActivity.class);
		pageSaisieInfoBDD.putExtra("@string/extraBDDFld_IdInfo", idInfo);
//		pageSaisieInfoBDD.putExtra("@string/extraBDDFld_Info", (String)lv.getItemAtPosition(position));
		pageSaisieInfoBDD.putExtra("@string/extraBDDFld_Info", ((TextView) v).getText().toString());
		startActivityForResult(pageSaisieInfoBDD, 1);
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
		switch(id){
			case R.id.action_settings:
				break;
			case R.id.menu_creerInfoBDD:
				//creer une nouvelle info
				Log.i("Tuto", "on va creer une nouvelle entree dans BDD");
				Intent pageSaisieInfoBDD = new Intent(this, SaisieInfoActivity.class);
				startActivityForResult(pageSaisieInfoBDD, 1);
				break;
			case R.id.menu_supprInfoBDD:
				//creer une nouvelle info
//				Log.i("Tuto", "on va creer une nouvelle entree dans BDD");
//				startActivityForResult(pageSaisieInfoBDD, 1);
				break;
		}
		
//		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
