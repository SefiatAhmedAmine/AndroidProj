package com.tic_tac_toe.view;


import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tic_tac_toe.R;
import com.tic_tac_toe.control.BluetoothGameService;
import com.tic_tac_toe.control.DeviceListActivity;
import com.tic_tac_toe.control.GameLogic;
import com.tic_tac_toe.model.Player;

//import com.example.minimaxtictactoe.R;

public class TwoPlayerActivityBluetooth extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TwoPlayerActivityBT";
    private static final boolean D = true;

    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int PLAYER_LOCATION_READ = 2;
    public static final int PLAYER_LOCATION_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_DISCONNECTED = 6;

    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    public static final String MARKER = "marker";

    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    private String mConnectedDeviceName = null;
    private String mMarker = null;
    private StringBuffer mOutStringBuffer;
    private String oMarker = null;

    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothGameService gameService = null;

    private EditText hostNameLabel;
    private EditText remoteNameLabel;
    private ImageView myMarker;
    private ImageView opponentMarker;
    private TextView playerOneName;
    private TextView playerTwoName;

    private String hostName;
    private String remoteName;

    private Player hostPlayer;
    private Player remotePlayer;

    private Toolbar toolbar;

    private ImageView one_one;
    private ImageView one_two;
    private ImageView one_three;
    //Second row buttons
    private ImageView two_one;
    private ImageView two_two;
    private ImageView two_three;
    //Third row buttons
    private ImageView three_one;
    private ImageView three_two;
    private ImageView three_three;

    private GameLogic gameLogic;
    private boolean isTurn = false;
    private BluetoothDevice device;

    private AlertDialog dialog;
    private MediaPlayer sound;
    private MediaPlayer winSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_bluetooth);

        if (D) Log.e(TAG, "ONCREATE");

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        hostNameLabel = (EditText) findViewById(R.id.player_name);
        remoteNameLabel = (EditText) findViewById(R.id.remote_name);

        myMarker = (ImageView) findViewById(R.id.m_marker);
        opponentMarker = (ImageView) findViewById(R.id.o_marker);

        hostNameLabel.setEnabled(false);
        remoteNameLabel.setEnabled(false);

        toolbar = (Toolbar) findViewById(R.id.options_bar);
        setTitle("Bluetooth Lobby");
        //setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            hostName = extras.getString("playerOne");
        }

        hostNameLabel.setText(hostName);

        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        sound = MediaPlayer.create(this, R.raw.pencil);


    }

    @Override
    public void onStart() {
        super.onStart();
        if (D) {Log.e(TAG, "ONSTART");
                System.out.println("ONSTART");
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else {
            if (gameService == null) setupGame();
        }

    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        if (D) {Log.e(TAG, "ONRESUME");
        System.out.println("ONRESUME");
    }

        if (gameService != null) {
            if (gameService.getState() == BluetoothGameService.STATE_NONE) {
                gameService.start();
            }
        }
    }

    private void setupGame() {
        Log.d(TAG, "setupGame()");
        System.out.println("setupGame()");

        gameService = new BluetoothGameService(this, mHandler);
        mOutStringBuffer = new StringBuffer("");
    }


    @Override
    public synchronized void onPause() {
        super.onPause();
        if (D) {Log.e(TAG, "- ON PAUSE -");
            System.out.println("ON PAUSE");}

    }

    @Override
    public void onStop() {
        super.onStop();
        if (D) {Log.e(TAG, "-- ON STOP --");
            System.out.println("ON STOP");}
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the Bluetooth chat services
        if (gameService != null) gameService.stop();
        if (D) {Log.e(TAG, "--- ON DESTROY ---");
            System.out.println("ON DESTROY");}
    }

    private void ensureDiscoverable() {
        if (D) {Log.d(TAG, "ensure discoverable");
            System.out.println("ensure discoverable");}
        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    public void setupBoardView() {
        setContentView(R.layout.activity_two_player);
        gameLogic = new GameLogic();

        //initialize buttons
        one_one = (ImageView) findViewById(R.id.one_one);
        one_two = (ImageView) findViewById(R.id.one_two);
        one_three = (ImageView) findViewById(R.id.one_three);
        two_one = (ImageView) findViewById(R.id.two_one);
        two_two = (ImageView) findViewById(R.id.two_two);
        two_three = (ImageView) findViewById(R.id.two_three);
        three_one = (ImageView) findViewById(R.id.three_one);
        three_two = (ImageView) findViewById(R.id.three_two);
        three_three = (ImageView) findViewById(R.id.three_three);

        playerOneName = (TextView) findViewById(R.id.player_one_score);
        playerTwoName = (TextView) findViewById(R.id.player_two_score);

        playerOneName.setText(hostPlayer.getName() + ": " + hostPlayer.getScore());
        playerTwoName.setText(remotePlayer.getName() + ": " + remotePlayer.getScore());

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Cheveuxdange.ttf");

        playerOneName.setTypeface(type);
        playerTwoName.setTypeface(type);

        one_one.setBackgroundColor(Color.rgb(255,235,59));
        one_two.setBackgroundColor(Color.rgb(255,235,59));
        one_three.setBackgroundColor(Color.rgb(255,235,59));
        two_one.setBackgroundColor(Color.rgb(255,235,59));
        two_two.setBackgroundColor(Color.rgb(255,235,59));
        two_three.setBackgroundColor(Color.rgb(255,235,59));
        three_one.setBackgroundColor(Color.rgb(255,235,59));
        three_two.setBackgroundColor(Color.rgb(255,235,59));
        three_three.setBackgroundColor(Color.rgb(255,235,59));

        one_one.setOnClickListener(this);
        one_two.setOnClickListener(this);
        one_three.setOnClickListener(this);
        two_one.setOnClickListener(this);
        two_two.setOnClickListener(this);
        two_three.setOnClickListener(this);
        three_one.setOnClickListener(this);
        three_two.setOnClickListener(this);
        three_three.setOnClickListener(this);

        if(isTurn){
            Toast.makeText(TwoPlayerActivityBluetooth.this, "It is your turn!", Toast.LENGTH_SHORT).show();
        }

    }

    private void sendLocation(String location) {

        if (gameService.getState() != BluetoothGameService.STATE_CONNECTED) {
            //Toast.makeText(this, R.string.none_found, Toast.LENGTH_SHORT).show();
            System.out.println("R.string.none_found");
            return;
        }

        byte[] sendLocation = location.getBytes();

        gameService.writeLocation(sendLocation);
        mOutStringBuffer.setLength(0);
    }

    public void updateUI(char marker, int row, int col) {
        if (row == 0 && col == 0) {
            if (marker == 'x') {
                one_one.setBackgroundResource(R.drawable.cross);
            } else {
                one_one.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            one_one.setEnabled(false);
        } else if (row == 0 && col == 1) {
            if (marker == 'x') {
                one_two.setBackgroundResource(R.drawable.cross);
            } else {
                one_two.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            one_two.setEnabled(false);
        } else if (row == 0 && col == 2) {
            if (marker == 'x') {
                one_three.setBackgroundResource(R.drawable.cross);
            } else {
                one_three.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            one_three.setEnabled(false);
        } else if (row == 1 && col == 0) {
            if (marker == 'x') {
                two_one.setBackgroundResource(R.drawable.cross);
            } else {
                two_one.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            two_one.setEnabled(false);
        } else if (row == 1 && col == 1) {
            if (marker == 'x') {
                two_two.setBackgroundResource(R.drawable.cross);
            } else {
                two_two.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            two_two.setEnabled(false);
        } else if (row == 1 && col == 2) {
            if (marker == 'x') {
                two_three.setBackgroundResource(R.drawable.cross);
            } else {
                two_three.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            two_three.setEnabled(false);
        } else if (row == 2 && col == 0) {
            if (marker == 'x') {
                three_one.setBackgroundResource(R.drawable.cross);
            } else {
                three_one.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            three_one.setEnabled(false);
        } else if (row == 2 && col == 1) {
            if (marker == 'x') {
                three_two.setBackgroundResource(R.drawable.cross);
            } else {
                three_two.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            three_two.setEnabled(false);
        } else if (row == 2 && col == 2) {
            if (marker == 'x') {
                three_three.setBackgroundResource(R.drawable.cross);
            } else {
                three_three.setBackgroundResource(R.drawable.circle);
            }
            sound.start();
            three_three.setEnabled(false);
        }

        checkGameStatus();

    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if (D) {Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                        System.out.println("MESSAGE_STATE_CHANGE: " + msg.arg1);
                    }
                    switch (msg.arg1) {
                        case BluetoothGameService.STATE_CONNECTED:
                            break;
                        case BluetoothGameService.STATE_CONNECTING:
                            break;
                        case BluetoothGameService.STATE_LISTEN:

                        case BluetoothGameService.STATE_NONE:
                            break;
                        case BluetoothGameService.STATE_DISCONNECTED:
                            Intent bluetoothIntent = new Intent(TwoPlayerActivityBluetooth.this, TwoPlayerActivityBluetooth.class);
                            bluetoothIntent.putExtra("playerOne", hostName);
                            startActivity(bluetoothIntent);
                            break;
                        case BluetoothGameService.STATE_NEW_GAME:
                            if(dialog != null && dialog.isShowing()){
                                dialog.dismiss();
                            }
                            Log.d("New Game", "New Game");
                            System.out.println("New Game");
                            setupBoardView();
                            break;
                    }
                    break;
                case PLAYER_LOCATION_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;

                    String writeMessage = new String(writeBuf);

                    break;
                case PLAYER_LOCATION_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    String readMessage = new String(readBuf, 0, msg.arg1);

                    int row = Character.getNumericValue(readMessage.charAt(0));
                    int col = Character.getNumericValue(readMessage.charAt(2));

                    isTurn = !isTurn;

                    gameLogic.addMarker(oMarker.charAt(0), row, col);
                    updateUI(oMarker.charAt(0), row, col);
                    break;
                case MESSAGE_DEVICE_NAME:
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    mMarker = msg.getData().getString(MARKER);

                    if (mMarker.equals("")) {
                        mMarker = "o";
                        oMarker = "x";
                        myMarker.setBackgroundResource(R.drawable.circle);
                        opponentMarker.setBackgroundResource(R.drawable.cross);
                    } else if (mMarker.equals("x")) {
                        oMarker = "o";
                        isTurn = true;
                        myMarker.setBackgroundResource(R.drawable.cross);
                        opponentMarker.setBackgroundResource(R.drawable.circle);
                    }
                    Toast.makeText(TwoPlayerActivityBluetooth.this, "Connected to " + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    remoteNameLabel.setText(mConnectedDeviceName);
                    hostPlayer = new Player(hostName, mMarker.charAt(0), 0);
                    remotePlayer = new Player(mConnectedDeviceName, oMarker.charAt(0), 0);

                    setupBoardView();


                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(TwoPlayerActivityBluetooth.this, msg.getData().getString(TOAST), Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (D) {
            Log.d(TAG, "onActivityResult " + resultCode);
            System.out.println("onActivityResult " + resultCode);
        }
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    String address = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

                    device = mBluetoothAdapter.getRemoteDevice(address);
                    gameService.connect(device, "x");
                }
                break;
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    setupGame();
                } else {
                    Log.d(TAG, "BT NOT ENABLED");
                    System.out.println("BT NOT ENABLED");
                    //Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    System.out.println("R.string.bt_not_enabled_leaving");
                    finish();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scan:
                // Launch the DeviceListActivity to see devices and do scan
                Intent serverIntent = new Intent(this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                return true;
            case R.id.discoverable:
                // Ensure this device is discoverable by others
                ensureDiscoverable();
                return true;
        }
        return false;
    }

    public void checkGameStatus() {
        char playerMarker = hostPlayer.getPlayerMarker();
        if (gameLogic.hasWon()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (gameLogic.getWinningMarker() == playerMarker) {
                winSound = MediaPlayer.create(this, R.raw.woohoo);
                builder.setTitle("Congratulations, You Won!");
                hostPlayer.setScore(hostPlayer.getScore() + 1);
            } else {
                builder.setTitle("You Lost, Better Luck Next Time!");
                winSound = MediaPlayer.create(this, R.raw.crowd_boo);
                remotePlayer.setScore(remotePlayer.getScore() + 1);

            }

            builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    gameService.setNewGame();
                }
            }).setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent loginActivity = new Intent(TwoPlayerActivityBluetooth.this, LoginActivity.class);
                    gameService.stop();
                    startActivity(loginActivity);
                }
            });
            dialog = builder.create();
            winSound.start();
            dialog.show();
        } else if (gameLogic.checkFull()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Tie Game, Better Luck Next Time")
                    .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            gameService.setNewGame();
                        }
                    })
                    .setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent loginActivity = new Intent(TwoPlayerActivityBluetooth.this, LoginActivity.class);
                            gameService.stop();
                            startActivity(loginActivity);
                        }
                    });
            dialog = builder.create();
            winSound = MediaPlayer.create(this, R.raw.crowd_boo);
            winSound.start();
            dialog.show();

        }
    }

    @Override
    public void onClick(View v) {
        char playerMarker = mMarker.charAt(0);
        if (isTurn) {
            switch (v.getId()) {

                case R.id.one_one:
                    gameLogic.addMarker(playerMarker, 0, 0);
                    updateUI(playerMarker, 0, 0);
                    sendLocation("0,0");

                    break;
                case R.id.one_two:
                    gameLogic.addMarker(playerMarker, 0, 1);
                    updateUI(playerMarker, 0, 1);
                    sendLocation("0,1");

                    break;
                case R.id.one_three:
                    gameLogic.addMarker(playerMarker, 0, 2);
                    updateUI(playerMarker, 0, 2);
                    sendLocation("0,2");

                    break;
                case R.id.two_one:
                    gameLogic.addMarker(playerMarker, 1, 0);
                    updateUI(playerMarker, 1, 0);
                    sendLocation("1,0");

                    break;
                case R.id.two_two:
                    gameLogic.addMarker(playerMarker, 1, 1);
                    updateUI(playerMarker, 1, 1);
                    sendLocation("1,1");

                    break;
                case R.id.two_three:
                    gameLogic.addMarker(playerMarker, 1, 2);
                    updateUI(playerMarker, 1, 2);
                    sendLocation("1,2");

                    break;
                case R.id.three_one:
                    gameLogic.addMarker(playerMarker, 2, 0);
                    updateUI(playerMarker, 2, 0);
                    sendLocation("2,0");
                    break;
                case R.id.three_two:
                    gameLogic.addMarker(playerMarker, 2, 1);
                    updateUI(playerMarker, 2, 1);
                    sendLocation("2,1");

                    break;
                case R.id.three_three:
                    gameLogic.addMarker(playerMarker, 2, 2);
                    updateUI(playerMarker, 2, 2);
                    sendLocation("2,2");

                    break;

            }
            isTurn = !isTurn;
        }else{
            Toast.makeText(this, "Wait your turn!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent loginActivity = new Intent(TwoPlayerActivityBluetooth.this, LoginActivity.class);
                        gameService.stop();
                        startActivity(loginActivity);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}
