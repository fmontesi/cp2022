package cp;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaExample {
	/* Java provides a Function interface that is generic on input and output type.
	 * Furthermore, there is a BiFunction interface for functions that take two parameters.
	 * You can check them out at:
	 * 	- https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Function.html
	 *  - https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/BiFunction.html
	 */

	/* Unfortunately, there is no interface for functions that take more
	 * than two parameters. But this is no problem, since we can define them
	 * ourselves. Below is an example for functions that take 3 parameters.
	 */
	/// The interface of a function that takes three parameters
	interface ThreeFunction< I1, I2, I3, O > {
		public O apply( I1 i1, I2 i2, I3 i3 );
	}

	public static void main() {
		// You can instantiate a Function by creating an object as usual.
		Function<Integer, Integer> f = new Function<Integer, Integer>() {
			public Integer apply(Integer i) {
				return i + i;
			}
		};

		// Or you can do the same by using a lambda expression.
		// The following commented line is essentially equivalent to the definition of f above.
		// Function<Integer, Integer> f = i -> i + i;

		// The following commented statement is a mistake because f expects an Integer.
		// Try changing "Hello" into 5.
		// System.out.println( f.apply("Hello") );

		// Here is a simple example with a ThreeFunction
		ThreeFunction<Integer, Integer, Integer, Integer> f3 =
			(x, y, z) -> x + y + z;
		f3.apply( 1, 2, 3 );

		// An example about iteration. You can iterate over a list using a for-each loop as usual.
		List<Integer> list = List.of(1,2,3,4);
		for( Integer x : list ) {
			System.out.println( x );
		}

		// Or you can use a functional programming style as follows.
		list.forEach( x -> System.out.println( x ) );

		// Streams allow for more complex transformations.
		list.stream()
			.map( i -> Math.sqrt( i ) )
			.map( d -> (int)(double)d )
			.forEach( d -> System.out.println( d ) );
		
		// The code above based on a stream and map essentially behaves as the following loop.
		for( Integer i : list ) {
			Function<Integer, Double> h = x -> Math.sqrt(x);
			double d = h.apply(i);
			Function<Double, Integer> g = x -> (int)(double)x;
			int result = g.apply(d);
			Consumer<Integer> consumer = x -> System.out.println( x ); // A Consumer does not return anything.
			consumer.accept(result);
		}
	}
}
