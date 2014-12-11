// IFibonacciService.aidl
package fibonacci.com.fibonaccicom;

// Declare any non-default types here with import statements
import fibonacci.com.fibonaccicom.FibonacciRequest;
import fibonacci.com.fibonaccicom.FibonacciResponse;

interface IFibonacciService {
     long fibJR(in long n);
     long fibJI(in long n);
     long fibNR(in long n);
     long fibNI(in long n);
     FibonacciResponse fib(in FibonacciRequest request);
}
