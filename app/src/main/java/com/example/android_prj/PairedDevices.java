package com.example.android_prj;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Set;

public class PairedDevices extends AppCompatActivity {

    ListView listView;
    Button scanBtn;
    BluetoothAdapter MyBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paired_devices);
        scanBtn = (Button) findViewById(R.id.scanBtn);
        listView = (ListView) findViewById(R.id.listView);
        MyBluetoothAdapter= BluetoothAdapter.getDefaultAdapter();

        exeButton();



    }

    private void exeButton() {
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<BluetoothDevice> bt=MyBluetoothAdapter.getBondedDevices();
                String[] strings=new String[bt.size()];
                int counter = 0;

                if(bt.size()>0){
                    for (BluetoothDevice device: bt){
                        strings[counter]=device.getName();
                        counter++;
                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,strings);
                    listView.setAdapter(arrayAdapter);

                }
            }
        });
    }
}