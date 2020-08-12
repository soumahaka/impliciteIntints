package com.example.impliciteintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String ADDRESS= "1600 Amphitheatre Parkway, CA";
    static final String TEXT_TO_SHARE_TITLE="Learn how to share";
    static final String TEXT_TO_SHARE="Please share my app if you care about others...";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickOpenWebpageButton(View v) {
        // COMPLETED (5) Create a String that contains a URL ( make sure it starts with http:// or https:// )
        String urlAsString = "http://www.udacity.com";

        // COMPLETED (6) Replace the Toast with a call to openWebPage, passing in the URL String from the previous step
        openWebPage(urlAsString);
    }

    public void onClickOpenAddressButton(View v) {

        showMap(ADDRESS);

    }

    public void onClickShareTextButton(View v) {
        shareText(TEXT_TO_SHARE,TEXT_TO_SHARE_TITLE);
    }


    public void createYourOwn(View v) {
        Toast.makeText(this,
                "TODO: Create Your Own Implicit Intent",
                Toast.LENGTH_SHORT)
                .show();
    }


    private void openWebPage(String url) {

        Uri webpageAdress = Uri.parse(url);
        Log.d(MainActivity.class.getSimpleName(), webpageAdress.toString());

        Intent intent = new Intent(Intent.ACTION_VIEW, webpageAdress);


        Log.d(MainActivity.class.getSimpleName(), intent.resolveActivity(getPackageManager()).toString());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    private void showMap(String address) {
        Uri.Builder addressUri=new Uri.Builder();
        addressUri.scheme("geo")
                .path("0,0")
                .appendQueryParameter("q", address);
        Intent intent =new Intent(Intent.ACTION_VIEW, addressUri.build());
        Log.d(MainActivity.class.getSimpleName(), addressUri.build().toString());

        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    private void shareText(String textToShare, String title){
        String mimeType="text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();
    }
}
