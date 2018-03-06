package com.example.guruchelaadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.actionbarsherlock.app.SherlockActivity;



public class AppleActivity extends SherlockActivity implements OnClickListener {
	EditText personsEmail, intro, outro;
	String emailAdd, beginning, out;
	ImageButton sendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		this.setTitle("Email");
		initializeVars();
		sendEmail.setOnClickListener(this);
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		personsEmail = (EditText) findViewById(R.id.etEmails);
		intro = (EditText) findViewById(R.id.etIntro);

		outro = (EditText) findViewById(R.id.etOutro);
		sendEmail = (ImageButton) findViewById(R.id.bSentEmail);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated();
		String emailaddress[] = { emailAdd };

		String message = "" + out;

		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, beginning);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
	}

	private void convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated() {
		// TODO Auto-generated method stub
		emailAdd = personsEmail.getText().toString();
		beginning = intro.getText().toString();
		out = outro.getText().toString();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
