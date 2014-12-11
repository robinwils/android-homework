package fibonacci.com.fibonacciclient;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import fibonacci.com.fibonacciclient.FibonacciActivity;
import fibonacci.com.fibonacciclient.R;
import fibonacci.com.fibonaccicom.FibonacciRequest;
import fibonacci.com.fibonaccicom.FibonacciResponse;
import fibonacci.com.fibonaccicom.IFibonacciService;

/**
 * Created by rwils on 12/12/14.
 */
public class ComputeTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "FibonacciClient";
    private final FibonacciActivity activity;
    private final FibonacciRequest request;
    private final ProgressDialog dialog;

    public ComputeTask(FibonacciActivity activity, FibonacciRequest request, ProgressDialog dialog) {
        this.activity = activity;
        this.request = request;
        this.dialog = dialog;
        Log.d(TAG, Boolean.toString(this.activity.service == null));
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected String doInBackground(Void... params) {
        try {
            Log.d(TAG, "ICI: " + Boolean.toString(this.activity.service == null));

            FibonacciResponse response = this.activity.service.fib(this.request);

            return String.format(
                    "fibonacci(%d) = %d\nin %d ms", response.getResult(),
                    response.getTimeInMs());
        } catch (RemoteException e) {
            Log.wtf(TAG, "Failed to communicate with the service");
            return null;
        }
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param s The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(String s) {
        this.dialog.dismiss();
        if(s == null)
            Toast.makeText(this.activity, R.string.fib_error, Toast.LENGTH_SHORT).show();
        else
            this.activity.output.setText(s);
    }
}
