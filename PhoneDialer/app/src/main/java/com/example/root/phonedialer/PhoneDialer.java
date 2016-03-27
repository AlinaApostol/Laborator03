package com.example.root.phonedialer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialer extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private NumberButtonClickListener numberButtonClickListener = new NumberButtonClickListener();
    private BackspaceButtonClickListener backspaceButtonClickListener = new BackspaceButtonClickListener();
    private CallButtonClickListener callButtonClickListener  = new CallButtonClickListener();
    private HangupButtonClickListener hangupButtonClickListener = new HangupButtonClickListener();

    private class NumberButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + ((Button)view).getText().toString());
        }
    }

    private class BackspaceButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneNumber = phoneNumberEditText.getText().toString();
            if (phoneNumber.length() > 0) {
                phoneNumber = phoneNumber.substring(0,phoneNumber.length()-1);
                phoneNumberEditText.setText(phoneNumber);
            }
        }
    }

    private class CallButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }
    }

    private class HangupButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick (View view) {
            phoneNumberEditText.setText("");
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edit_text);

        Button button0 = (Button)findViewById(R.id.number_0_button);
        button0.setOnClickListener(numberButtonClickListener);
        Button button1 = (Button)findViewById(R.id.number_1_button);
        button1.setOnClickListener(numberButtonClickListener);
        Button button2 = (Button)findViewById(R.id.number_2_button);
        button2.setOnClickListener(numberButtonClickListener);
        Button button3 = (Button)findViewById(R.id.number_3_button);
        button3.setOnClickListener(numberButtonClickListener);
        Button button4 = (Button)findViewById(R.id.number_4_button);
        button4.setOnClickListener(numberButtonClickListener);
        Button button5 = (Button)findViewById(R.id.number_5_button);
        button5.setOnClickListener(numberButtonClickListener);
        Button button6 = (Button)findViewById(R.id.number_6_button);
        button6.setOnClickListener(numberButtonClickListener);
        Button button7 = (Button)findViewById(R.id.number_7_button);
        button7.setOnClickListener(numberButtonClickListener);
        Button button8 = (Button)findViewById(R.id.number_8_button);
        button8.setOnClickListener(numberButtonClickListener);
        Button button9 = (Button)findViewById(R.id.number_9_button);
        button9.setOnClickListener(numberButtonClickListener);
        Button star = (Button)findViewById(R.id.number_star_button);
        star.setOnClickListener(numberButtonClickListener);
        Button diez = (Button)findViewById(R.id.number_diez_button);
        diez.setOnClickListener(numberButtonClickListener);
        ImageButton backspace = (ImageButton)findViewById(R.id.backspace_button);
        backspace.setOnClickListener(backspaceButtonClickListener);
        ImageButton call = (ImageButton)findViewById(R.id.call_button);
        call.setOnClickListener(callButtonClickListener);
        ImageButton hangup = (ImageButton)findViewById(R.id.hangup_button);
        hangup.setOnClickListener(hangupButtonClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phone_dialer, menu);
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
