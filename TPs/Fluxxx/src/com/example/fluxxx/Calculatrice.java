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
import android.widget.TextView;

public class Calculatrice extends Activity {

	private TextView tvAffichPrincipal;
	private String termeEnCours;
	private String termePrecedent;
	private boolean premiereSaisie;
	private boolean premiereOperation;
	private String operation;
	private boolean divPar0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculatrice);

		//recuperation de l'intent page accueil
		Intent intent = getIntent();
		//textview affichNom
		TextView tvNom = (TextView) findViewById(R.id.affichNom);
		if(intent != null){
			tvNom.setText(intent.getStringExtra("@string/dataNom"));
		}
		
		//Affichage
        tvAffichPrincipal = (TextView) findViewById(R.id.affichPrincipal);

        initCalculatrice();
		
		
		//Recuperation des boutons
        Button chiffre0 = (Button) findViewById(R.id.buttonNum0);
        Button chiffre1 = (Button) findViewById(R.id.buttonNum1);
        Button chiffre2 = (Button) findViewById(R.id.buttonNum2);
        Button chiffre3 = (Button) findViewById(R.id.buttonNum3);
        Button chiffre4 = (Button) findViewById(R.id.buttonNum4);
        Button chiffre5 = (Button) findViewById(R.id.buttonNum5);
        Button chiffre6 = (Button) findViewById(R.id.buttonNum6);
        Button chiffre7 = (Button) findViewById(R.id.buttonNum7);
        Button chiffre8 = (Button) findViewById(R.id.buttonNum8);
        Button chiffre9 = (Button) findViewById(R.id.buttonNum9);
        Button virgule = (Button) findViewById(R.id.buttonComma);
        Button operPlus = (Button) findViewById(R.id.buttonPlus);
        Button operMoins = (Button) findViewById(R.id.buttonMin);
        Button operDiv = (Button) findViewById(R.id.buttonDiv);
        Button operMult = (Button) findViewById(R.id.buttonBy);
        Button affichResultat = (Button) findViewById(R.id.buttonEqual);
        Button initCalc = (Button) findViewById(R.id.buttonReset);

        //Definition des actions des boutons chiffres
        chiffre0.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("0");
			}
		});
        chiffre1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("1");
			}
		});
        chiffre2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("2");
			}
		});
        chiffre3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("3");
			}
		});
        chiffre4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("4");
			}
		});
        chiffre5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("5");
			}
		});
        chiffre6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("6");
			}
		});
        chiffre7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("7");
			}
		});
        chiffre8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("8");
			}
		});
        chiffre9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre("9");
			}
		});
        //Definition des actions des boutons operations
        initCalc.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		initCalculatrice();
        	}
        });
        operPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieOperation("+");
			}
		});
        operMoins.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieOperation("-");
			}
		});
        operDiv.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieOperation("/");
			}
		});
        operMult.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieOperation("*");
			}
		});
        virgule.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saisieNombre(".");
			}
		});
        affichResultat.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				calculer(operation);
				if(divPar0){
					tvAffichPrincipal.setText(getText(R.string.erreurDiv0));
				}
				else{
					termeEnCours = String.valueOf(termePrecedent);
					termePrecedent = "";
					premiereOperation = true;
					tvAffichPrincipal.setText(termeEnCours);
				}
			}
		});
        

	}

	public void initCalculatrice(){
		//Initialisations des variables
		termeEnCours = "";
		termePrecedent = "";
		operation = "";
		premiereSaisie = true;
		premiereOperation = true;
		divPar0 = false;
		
		//Initialisation affichage
		tvAffichPrincipal.setText(getString(R.string.affich0));

	}
	
	//Saisie des nombres
	public void saisieNombre(String numChar){
		if(premiereSaisie){
			termeEnCours="";
			premiereSaisie =false;
		}
		termeEnCours += numChar;
		tvAffichPrincipal.setText(termeEnCours);
	}
	
	//Saisie des operations
	public void saisieOperation(String operChar){
		//sauvegarde du terme en cours, avec calcul sur le terme precedant
		if(premiereOperation){
			termePrecedent = String.valueOf(termeEnCours);
		}
		else{
			calculer(operation);
		}
		premiereSaisie = true;
		
		//sauvegarde de l'operation pour differer le calcul
		//(fin de la saisie du deuxieme terme)
		operation = operChar;
		premiereOperation = false;
	}

	//Traitement operation
	private void calculer(String operation){
		Double t1 = 0.;
		Double t2 = 0.;
		
		if(!"".equals(termePrecedent)){
			t1 = Double.valueOf(termePrecedent);
			t2 = Double.valueOf(termeEnCours);
		}
		
		if("+".equals(operation)){
			termePrecedent = String.valueOf(t1 + t2);
		}
		if("-".equals(operation)){
			termePrecedent = String.valueOf(t1 - t2);
		}
		if("/".equals(operation)){
			if(t2==0){
				divPar0 = true;
			}
			else{
				termePrecedent = String.valueOf(t1 / t2);
			}
		}
		if("*".equals(operation)){
			termePrecedent = String.valueOf(t1 * t2);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculatrice, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_calculatrice,
					container, false);
			return rootView;
		}
	}

}
