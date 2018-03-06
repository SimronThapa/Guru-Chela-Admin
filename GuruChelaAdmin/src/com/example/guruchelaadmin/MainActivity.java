package com.example.guruchelaadmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;


public class MainActivity extends SherlockActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button auth = (Button) findViewById(R.id.bauthentication);
		Button email = (Button) findViewById(R.id.bemail);
		Button delete = (Button) findViewById(R.id.bdelete);
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, Delete.class);
				startActivity(i);
			}
		});
		auth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, AndroidActivity.class);
				startActivity(i);
			}
		});

		email.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, AppleActivity.class);
				startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.cool_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case R.id.menu_list:
			Intent i = new Intent(MainActivity.this, List1.class);
			startActivity(i);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		new AlertDialog.Builder(this)
				.setIcon(R.drawable.alerts_and_states_warning)
				.setTitle("closing Activity")
				.setMessage("Are You Sure You want To exit?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								finish();
							}
						}).setNegativeButton("No", null).show();
	}
}