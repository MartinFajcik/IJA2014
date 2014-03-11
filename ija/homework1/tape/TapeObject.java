package ija.homework1.tape;

/*
 * Reprezentuje objekt, který lze umístit na polícko (viz TapeField).
 *  Každý objekt má pridelené jméno. Abstraktní trída.
 */
public abstract class TapeObject {
protected String name;
	public TapeObject (String name){ //constructor
		this.name = name;
	}
	public abstract boolean canSeize();
	public abstract boolean open();
	public abstract boolean equals(Object o);
	public abstract int hashCode();	
}