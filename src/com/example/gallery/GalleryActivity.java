package com.example.gallery;

import android.support.v7.app.ActionBarActivity;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryActivity extends ActionBarActivity {
	 private int[] myImageIds = {R.drawable.photo1, 
			 R.drawable.photo2, 
		  R.drawable.photo3, 
		  R.drawable.photo4, 
		  R.drawable.photo5, 
		  R.drawable.photo6,R.drawable.photo7};
	 int lastSelectedPosition= -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		
		Gallery gallery= (Gallery) findViewById(R.id.gallery);
		ImageAdapter imageAdapter = new ImageAdapter(this);
		gallery.setAdapter(imageAdapter);
	    gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  
	        @SuppressLint("NewApi")
			@Override  
	        public void onItemSelected(AdapterView<?> parent, View v,int position, long id) {  
	        	AnimatorSet animatorSet = new AnimatorSet();
	            ObjectAnimator imgScaleUpYAnim = ObjectAnimator.ofFloat(v, "scaleY", 0.7f, 1.05f);
	            imgScaleUpYAnim.setDuration(600);
	            //imgScaleUpYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
	            ObjectAnimator imgScaleUpXAnim = ObjectAnimator.ofFloat(v, "scaleX", 0.7f, 1.05f);
	            imgScaleUpXAnim.setDuration(600);
	            animatorSet.playTogether(imgScaleUpYAnim,imgScaleUpXAnim);
	            animatorSet.start();
    
	            for(int i = 0;i < parent.getChildCount();i++){
	            	if(parent.getChildAt(i) != v){
			            View s = parent.getChildAt(i);
			            ObjectAnimator imgScaleDownYAnim = ObjectAnimator.ofFloat(s, "scaleY", 1.05f, 0.7f);
			            imgScaleDownYAnim.setDuration(100);
			            //imgScaleUpYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
			            ObjectAnimator imgScaleDownXAnim = ObjectAnimator.ofFloat(s, "scaleX", 1.05f, 0.7f);
			            imgScaleDownXAnim.setDuration(100);				            
			            animatorSet.playTogether(imgScaleDownXAnim,imgScaleDownYAnim);
			            animatorSet.start();
	            	}
	            }
	        }  
	      
	        @Override  
	        public void onNothingSelected(AdapterView<?> arg0) { 
  
	        }  
	    }); 
 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gallery, menu);
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
	public class ImageAdapter extends BaseAdapter{
 
	    private Context mContext;
	    public ImageAdapter(Context context){
	    	mContext = context;
		}
	    public int getCount(){
	    	return myImageIds.length;
		}
		public Object getItem(int position){
			return position;
		}
		public long getItemId(int position){
			return position;
		}
		@SuppressLint("NewApi")
		public View getView(int position, View convertView, ViewGroup parent){
			ImageView imageView = new ImageView(mContext); 
			imageView.setImageResource(myImageIds[position]);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT));
			imageView.setScaleX(0.7f);
			imageView.setScaleY(0.7f);
			return imageView;
		}
	}
	
}
