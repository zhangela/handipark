package handipark.nyuad.com.handipark;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class NavigatorActivity extends Activity implements View.OnClickListener{

    Button reportButton;
    Button deleteButton;
    // ImageButton reportImage;
    public static ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    TextView myLocation;
    private String id;
    private ImageButton navigateButton;
    private double lat;
    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

        reportButton = (Button) findViewById(R.id.report);
        deleteButton = (Button) findViewById(R.id.delete);
        mImageView =(ImageView) findViewById(R.id.hidden);
        myLocation =(TextView) findViewById(R.id.textView);
        navigateButton = (ImageButton) findViewById(R.id.nav_btn);

        navigateButton.setOnClickListener(this);
        reportButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);


        Intent markerId = getIntent();
        id =markerId.getStringExtra("id");
        lat = markerId.getDoubleExtra("lat", 0);
        lng = markerId.getDoubleExtra("lng", 0);

        Log.i("MakerId " , id);

    }

    @Override
    public void onClick(View v) {
        // Toast.makeText(getApplicationContext(), "button1 was clicked", Toast.LENGTH_SHORT).show();
        int id = v.getId();
        if (id == R.id.report) {
            dispatchTakePictureIntent();
        }

        if (id == R.id.delete) {
            Toast.makeText(getApplicationContext(), "location deleted", Toast.LENGTH_SHORT).show();
            final String url = "http://45.33.86.33/remove.php?idd="+id;
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
            /*Intent intent;
            intent = new Intent(MainActivity.this, report.class);
            startActivity(intent);*/
        }
        if (id == R.id.nav_btn) {
            //Toast.makeText(getApplicationContext(), "button1 was clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + lng));
            startActivity(intent);
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            ReportActivity.bmp = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();


            Intent intent;
            intent = new Intent(NavigatorActivity.this, ReportActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


}
