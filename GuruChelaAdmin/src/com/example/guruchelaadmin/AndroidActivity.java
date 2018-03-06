package com.example.guruchelaadmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;



public class AndroidActivity extends SherlockActivity {
	EditText college;
	EditText chelacode;
	EditText gurucode;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.authentication);
		college = (EditText) findViewById(R.id.etCollegeName);
		chelacode = (EditText) findViewById(R.id.etCodeChela);
		gurucode = (EditText) findViewById(R.id.etCodeGuru);
		Button insert = (Button) findViewById(R.id.bInsert);
		Button generate = (Button) findViewById(R.id.bGenerate);
		generate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Random rand = new Random(); 
				Integer pickedNumber = rand.nextInt(1000000000);
				Integer pickedNumber1 = rand.nextInt(1000000000);
				gurucode.setText(""+pickedNumber);
				chelacode.setText(""+pickedNumber1);
				
				
			}
		});
		insert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String col = college.getText().toString();
				String chela = chelacode.getText().toString();
				String guru = gurucode.getText().toString();
				if (col.length() < 1 || chela.length() < 1 || guru.length() < 1) {
					Toast.makeText(AndroidActivity.this,
							"Fill up all the fields!", Toast.LENGTH_LONG)
							.show();
				} else {
					new UpdateDB().execute(col, guru, "0");
					new UpdateDB().execute(col, chela, "1");
				}

			}
		});

	}
	// Creating AsynTask for Updating the Database
		private class UpdateDB extends AsyncTask<String, String, String> {

			private String resp = "";

			@Override
			protected String doInBackground(String... params) {
				// publishProgress("Reading Data..."); // Calls onProgressUpdate()
				String url = "http://www.toolittletoobig.com/guruchela_php/updateAdmin_gc.php";
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);

				try {
					// Add your data

					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs
							.add(new BasicNameValuePair("institution", params[0]));
					nameValuePairs
							.add(new BasicNameValuePair("authentication", params[1]));
					nameValuePairs
					.add(new BasicNameValuePair("level", params[2]));

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
				// tvDisplay.setText(result);
				Toast.makeText(AndroidActivity.this,
						"Authentication codes successfully inserted!", Toast.LENGTH_LONG)
						.show();
				college.setText("");
				chelacode.setText("");
				gurucode.setText("");
			}

			@Override
			protected void onPreExecute() {
				// Things to be done before execution of long running operation. For
				// example showing ProgessDialog
			}

			@Override
			protected void onProgressUpdate(String... text) {
				// tvAll.setText(text[0]);
				Toast.makeText(AndroidActivity.this,
						"Authentication code update in progress!", Toast.LENGTH_LONG)
						.show();
				// Things to be done while execution of long running operation is in
				// progress. For example updating ProgessDialog
			}

		}
}