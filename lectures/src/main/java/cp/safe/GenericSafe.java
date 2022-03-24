package cp.safe;

public class GenericSafe<T> {
	private String password;
	private T content;
	
	public GenericSafe( String password, T content ) {
		this.password = password;
		this.content = content;
	}

	/**
	 * Opens the safe, reads the content inside and returns it. The safe is considered locked
	 * afterwards.
	 * 
	 * @throws InvalidPassword if the password is incorrect.
	 * @return the content of the safe
	 */
	public T readContent( String password )
		throws InvalidPassword {
		if( this.password.equals( password ) ) {
			return content;
		} else {
			throw new InvalidPassword();
		}
	}

	public void setContent( String password, T newContent )
		throws InvalidPassword {
		if( this.password.equals( password ) ) {
			content = newContent;
		} else {
			throw new InvalidPassword();
		}
	}
}
