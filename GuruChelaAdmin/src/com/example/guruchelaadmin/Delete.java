package com.example.guruchelaadmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class Delete extends SherlockActivity {
	EditText guru, chela;
	Button delete;
	String strguru, strchela;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);

		guru = (EditText) findViewById(R.id.et_delGuru);
		chela = (EditText) findViewById(R.id.et_delChela);

		delete = (Button) findViewById(R.id.b_del);
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				strguru = guru.getText().toString();
				strchela = chela.getText().toString();
				if (strguru.length() < 1 && strchela.length() < 1) {
					Toast.makeText(Delete.this, "Fill up the fields!",
							Toast.LENGTH_LONG).show();

				} else if (strguru.length() < 1 && strchela.length() > 1) {
					new Deleletdb().execute(strchela);
				} else if (strguru.length() > 1 && strchela.length() < 1) {
					new Deleletdb().execute(strguru);
				} else {
					Toast.makeText(Delete.this, "Select one at a time!",
							Toast.LENGTH_LONG).show();

				}
			}
		});

	}

	// Creating AsynTask for Insert into Database
	private class Deleletdb extends AsyncTask<String, String, String> {

		private String resp = "";

		@Override
		protected String doInBackground(String... params) {
			publishProgress("Reading Data..."); // Calls onProgressUpdate()
			String url = "http://www.toolittletoobig.com/guruchela_php/delete_user_gc.php";
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);

			try {
				// Add your data

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs
						.add(new BasicNameValuePair("username", params[0]));

				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				HttpResponse response = httpclient.execute(httppost);

				// Reading data from insert.php
				InputStream is = response.getEntity().getContent();

				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));

				resp = br.readLine();
				// Execute HTTP Post Request

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				resp = "Error:" + e.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				resp = "Error:" + e.toString();
			}
			return resp;
		}

		@Override
		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation
			Toast.makeText(Delete.this,result,
					Toast.LENGTH_LONG).show();
			guru.setText("");
			chela.setText("");
		}

		@Override
		protected void onPreExecute() {
			// Things to be done before execution of long running operation. For
			// example showing ProgessDialog
		}

		@Override
		protected void onProgressUpdate(String... text) {
			// tvAll.setText(text[0]);
			Toast.makeText(Delete.this,text[0],
					Toast.LENGTH_LONG).show();
			// Things to be done while execution of long running operation is in
			// progress. For example updating ProgessDialog
		}

	}
}
