package fibonacci.com.fibonaccicom;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rwils on 12/11/14.
 */
public class FibonacciResponse implements Parcelable {
    private final long result;
    private final long timeInMs;

    public FibonacciResponse(long result, long timeInMs) {
        this.result = result;
        this.timeInMs = timeInMs;
    }

    public long getResult() {
        return result;
    }

    public long getTimeInMs() {
        return timeInMs;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.result);
        dest.writeLong(this.timeInMs);
    }

    public static final Creator<FibonacciResponse> CREATOR = new Creator<FibonacciResponse>() {
        @Override
        public FibonacciResponse createFromParcel(Parcel source) {
            return new FibonacciResponse(source.readLong(), source.readLong());
        }

        @Override
        public FibonacciResponse[] newArray(int size) {
            return new FibonacciResponse[size];
        }
    };
}
