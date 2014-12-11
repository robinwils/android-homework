package fibonacci.com.fibonacciservice;

import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import fibonacci.com.fibonaccicom.FibonacciRequest;
import fibonacci.com.fibonaccicom.FibonacciResponse;
import fibonacci.com.fibonaccicom.IFibonacciService;

/**
 * Created by rwils on 12/11/14.
 */



public class IFibonacciServiceImpl extends IFibonacciService.Stub {
    private static final String TAG = "IFibonacciServiceImpl";

    @Override
    public long fibJR(long n) throws RemoteException {
        Log.d(TAG, String.format("fibJR(%d)", n));
        return FibLib.fibJR(n);
    }

    @Override
    public long fibJI(long n) throws RemoteException {
        Log.d(TAG, String.format("fibJI(%d)", n));
        return FibLib.fibJI(n);
    }

    @Override
    public long fibNR(long n) throws RemoteException {
        Log.d(TAG, String.format("fibNR(%d)", n));
        return FibLib.fibNR(n);
    }

    @Override
    public long fibNI(long n) throws RemoteException {
        Log.d(TAG, String.format("fibNI(%d)", n));
        return FibLib.fibNI(n);
    }

    @Override
    public FibonacciResponse fib(FibonacciRequest request) throws RemoteException {
        Log.d(TAG, String.format("fib(%d, %d)", request.getN(), request.getType()));
        long timeInMs = SystemClock.uptimeMillis();
        long result = 0;
        switch (request.getType()) {
            case ITERATIVE_JAVA:
                result = this.fibJI(request.getN());
                break;
            case ITERATIVE_NATIVE:
                result = this.fibNI(request.getN());
                break;
            case RECURSIVE_JAVA:
                result = this.fibJR(request.getN());
                break;
            case RECURSIVE_NATIVE:
                result = this.fibNR(request.getN());
                break;
            default:
                return null;
        }
        timeInMs = SystemClock.uptimeMillis() - timeInMs;
        return new FibonacciResponse(result, timeInMs);
    }
}
