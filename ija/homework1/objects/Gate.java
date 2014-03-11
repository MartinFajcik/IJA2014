package ija.homework1.objects;

import ija.homework1.tape.TapeObject;

public class Gate extends TapeObject {
private boolean open=false;

	public Gate(String name) {
		super(name); //call to TapeObject's name
	}

	@Override
	public boolean canSeize() {
		return open;
	}

	@Override
	public boolean open() {
		if (!this.open){
			open=true;
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Gate))
			return false;
		Gate other = (Gate) o;
		return (open == other.open ? 
			      (name != null ? name.equals(other.name):other.name==null):
				false);
	}

	@Override
	public int hashCode (){
		return name != null ? name.hashCode()+ (open ? 2:3):
			   TapeObject.class.hashCode() + (open ? 2:3);
	}
}
