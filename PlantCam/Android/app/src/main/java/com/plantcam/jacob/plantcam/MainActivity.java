package com.plantcam.jacob.plantcam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button masterButton;
    private Button nodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masterButton = (Button) findViewById(R.id.buttonMaster);
        nodeButton = (Button) findViewById(R.id.buttonNode);

        masterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MasterActivity.class));
            }
        });
    }
}
