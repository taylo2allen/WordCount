/* Project:      WordCountProject
// Module:       Correlator.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         11/6/18
// Purpose:      The command line form for Correlator will be as follows:java Correlator -h [-frequency | -unique] <filename2>
//				*/
public class HashTable implements DataCounter<String> {
	protected int size;
	/** {@inheritDoc} */
	@Override
	public DataCount<String>[] getCounts() {

		return null;
	}

	/** {@inheritDoc} */
	@Override
	public int getSize() {

		return size;
	}

	/** {@inheritDoc} */
	@Override
	public void incCount(String data) {

	}
	public void getArray() {

	}
	private static boolean isPrime(int n)
	{
		if( n == 2 || n == 3 )
			return true;

		if( n == 1 || n % 2 == 0 )
			return false;

		for( int i = 3; i * i <= n; i += 2 )
			if( n % i == 0 )
				return false;

		return true;
	}

}
