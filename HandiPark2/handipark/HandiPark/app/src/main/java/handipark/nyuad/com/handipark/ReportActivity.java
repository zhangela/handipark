package handipark.nyuad.com.handipark;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by ubuntu on 15/04/15.
 */
public class ReportActivity extends ActionBarActivity implements View.OnClickListener {

    public static ImageView img;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static Bitmap bmp;
    TextView locationText;
    //final ImageButton submit;
    double longitude;
    double latitude;
    Button submit;
    EditText a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        img =(ImageView) findViewById(R.id.myView);

        submit = (Button) findViewById(R.id.submit);
        a= (EditText) findViewById(R.id.editText);
        submit.setOnClickListener(this);



        locationText =(TextView) findViewById(R.id.locationText);
        img.setImageBitmap(bmp);

        GPSTracker gps = new GPSTracker(ReportActivity.this);

        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    getApplicationContext(), new GeocoderHandler());
        }

        else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


    }

    @Override
    public void onClick(View v) {


        final String url = "http://45.33.86.33/report.php?lng="+longitude+"&lat="+latitude+"&comment="+a.getText().toString();
        final HttpClient client = new DefaultHttpClient();
        new AsyncTask<Void, Void, Void >(){
            protected Void doInBackground(Void... params){
                try {
                    client.execute(new HttpGet(url));
                } catch(IOException e) {
                    System.out.println("baaaaaaad" + e.getMessage());
                }
                return null;
            }
        }.execute();

        this.finish();
        return;

    }



    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            TextView tvAddress = (TextView) findViewById(R.id.locationText);
            tvAddress.setText(locationAddress);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        this.finish();
        return;
    }
}
