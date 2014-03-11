package ija.homework1.objects;

import ija.homework1.tape.TapeObject;

public class Wall extends TapeObject {

	public Wall(String name) { 
		super(name);//call to TapeObject's name
	}

	@Override
	public boolean canSeize() {
		return false;
	}

	@Override
	public boolean open() {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Wall))
			return false;
		Wall other = (Wall) o;
		return name != null ? name.equals(other.name):other.name==null;
	}
	
	@Override
	public int hashCode (){		//*2 secures difference with other classes hcodes
		return name != null ? name.hashCode() : TapeObject.class.hashCode()*2;
	}
}
