package fibonacci.com.fibonaccicom;

        import android.os.Parcel;
        import android.os.Parcelable;

/**
 * Created by rwils on 12/11/14.
 */
public class FibonacciRequest implements Parcelable{
    public static enum Type {
        RECURSIVE_JAVA, ITERATIVE_JAVA, RECURSIVE_NATIVE, ITERATIVE_NATIVE
    }

    public long getN() {
        return n;
    }

    public Type getType() {
        return type;
    }

    private final long n;
    private final Type type;

    public FibonacciRequest(long n, Type type) {
        this.n = n;
        if (type == null)
            throw new NullPointerException("type should not be null");
        this.type = type;
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
        dest.writeLong(this.n);
        dest.writeInt(this.type.ordinal());
    }

    public static final Creator<FibonacciRequest> CREATOR = new Creator<FibonacciRequest>() {
        @Override
        public FibonacciRequest createFromParcel(Parcel source) {
            long n = source.readLong();
            Type type = Type.values()[source.readInt()];
            return new FibonacciRequest(n, type);
        }

        @Override
        public FibonacciRequest[] newArray(int size) {
            return new FibonacciRequest[size];
        }
    };
}
