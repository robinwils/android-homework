package fibonacci.com.fibonacciclient;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fibonacci.com.fibonaccicom.FibonacciRequest;
import fibonacci.com.fibonaccicom.FibonacciResponse;
import fibonacci.com.fibonaccicom.IFibonacciService;

public class FibonacciActivity extends ActionBarActivity {
    public static final String TAG = "FibonacciActivity";
    public IFibonacciService service;
    private Button button;
    public TextView output;

    private ServiceConnection mConnection = new ServiceConnection() {

        /**
         * Called when a connection to the Service has been established, with
         * the {@link android.os.IBinder} of the communication channel to the
         * Service.
         *
         * @param name    The concrete component name of the service that has
         *                been connected.
         * @param service The IBinder of the Service's communication channel,
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            FibonacciActivity.this.service = IFibonacciService.Stub.asInterface(service);
            button.setEnabled(true);
        }

        /**
         * Called when a connection to the Service has been lost.  This typically
         * happens when the process hosting the service has crashed or been killed.
         * This does <em>not</em> remove the ServiceConnection itself -- this
         * binding to the service will remain active, and you will receive a call
         * to {@link #onServiceConnected} when the Service is next running.
         *
         * @param name The concrete component name of the service whose
         *             connection has been lost.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            FibonacciActivity.this.service = null;
            button.setEnabled(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);

        this.button = (Button)findViewById(R.id.button);
        final EditText et = (EditText)findViewById(R.id.editText);
        this.output = (TextView)findViewById(R.id.textView2);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FibonacciRequest request = new FibonacciRequest(new Long(et.getText().toString()), FibonacciRequest.Type.ITERATIVE_JAVA);
                //final ProgressDialog dialog = ProgressDialog.show(FibonacciActivity.this, "", getText(R.string.progress_text), true);
                Log.d(TAG, FibonacciActivity.this.toString());
                new ComputeTask(FibonacciActivity.this, request, null).execute();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fibonacci, menu);
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
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        if (!bindService(new Intent(IFibonacciService.class.getName()), mConnection, Context.BIND_AUTO_CREATE))
            Log.d(TAG, "Failed to bind service");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        super.unbindService(mConnection);
    }

}
