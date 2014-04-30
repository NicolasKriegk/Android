package fr.imie.tutodessin;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Dessin extends View implements OnTouchListener{

	LinkedList<Cercle> cercles;
	
	
	public Dessin(Context context, AttributeSet attrs){
		super(context,attrs);
		//initialisation liste chainee vide
		cercles = new LinkedList<Cercle>();
		//surveillance des touchers sur l'ecran
		setOnTouchListener(this);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		//premier cercle bleu
//		Paint paint = new Paint();
//		paint.setColor(Color.BLUE);
//		canvas.drawCircle(100, 100, 50, paint);
		
		
		//essai cercle rayon emplacement et couleur random
//		Cercle cercle1 = new Cercle((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
//		cercle1.Draw(canvas);
	
		//dessin de tous les cercles de la liste chainee
		for(Cercle cercle : cercles){
			cercle.Draw(canvas);
		}
		
	
	
	}
	
	private class Cercle {
		int xc, yc, rayon;
		private Paint paint;
		public Cercle(int x, int y, int r){
			xc = x;
			yc = y;
			rayon = r;
			paint = new Paint();
			paint.setColor(Color.rgb((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
		}
		
		public void Draw(Canvas canvas){
			canvas.drawCircle(xc, yc, rayon, paint);
			
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int x = (int)event.getX();
		int y = (int)event.getY();
		
		switch(event.getAction()){
		//toucher en x,y, coordonnées du centre, rayon 1
		case MotionEvent.ACTION_DOWN:
			cercles.add(new Cercle(x, y, 1));
			break;
		//mouvement vers x,y: definition du rayon du dernier cercle ajouté
		case MotionEvent.ACTION_MOVE:
			Cercle cercle = cercles.getLast();
			cercle.rayon = (int)Math.sqrt((x-cercle.xc)*(x-cercle.xc) + (y-cercle.yc)*(y-cercle.yc));
			break;
			
		}
		//demander de redessiner la vue (nouvel appel a onDraw)
		this.invalidate();
		return true;
	}
	
}
