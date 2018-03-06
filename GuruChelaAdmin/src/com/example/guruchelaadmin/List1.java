package com.example.guruchelaadmin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;

public class List1 extends SherlockActivity {

	ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();
	ListView lv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		lv1 = (ListView) findViewById(R.id.listView1);
		new SelectDB().execute();
		
	}

	private class SelectDB extends AsyncTask<String, String, String> {

		private String resp = "";

		@Override
		protected String doInBackground(String... params) {
			// Calls onProgressUpdate()
			String url = "http://www.toolittletoobig.com/guruchela_php/select_reg1_gca.php";

			HttpClient httpclient = new DefaultHttpClient();

			try {
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				param.add(new BasicNameValuePair("username", "finaluser"));

				String paramString = URLEncodedUtils.format(param, "utf-8");
				// Creating get url
				url += "?" + paramString;

				URI path = new URI(url);
				HttpGet httpget = new HttpGet(path);

				HttpResponse response = httpclient.execute(httpget);

				HttpEntity entity = response.getEntity();
				resp = EntityUtils.toString(entity, "UTF-8");

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				resp = "Error:" + e.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				resp = "Error:" + e.toString();
			} /*
			 * catch (ParserConfigurationException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); } catch (SAXException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resp;

		}

		@Override
		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation
			// display.setText(result);
			String[] hotelList = result.split(";");

			for (String hotel : hotelList) {

				String row[] = hotel.split("!");
				System.out.println("hotel List+"+hotel);
				ItemDetails item_details = new ItemDetails();
				item_details.setCollege(row[0]);
				item_details.setAddress(row[1]);
				item_details.setPhone(row[2]);
				item_details.setWebsite(row[3]);
				item_details.setEmail(row[4]);
				item_details.setBox(row[5]);
				
				
				// item_details.setImageNumber(1);
				results.add(item_details);

			}
		lv1.setAdapter(new ItemListBaseAdapter(List1.this, results));

		}

		@Override
		protected void onPreExecute() {
			// Things to be done before execution of long running operation. For
			// example showing ProgessDialog
		}

		@Override
		protected void onProgressUpdate(String... text) {

			// Things to be done while execution of long running operation is in
			// progress. For example updating ProgessDialog
			Toast.makeText(List1.this, text[0], Toast.LENGTH_LONG).show();
		}

	}

}
