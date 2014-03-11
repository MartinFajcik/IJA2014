package ija.homework1.tape;

/*
 * Reprezentuje hlavu, která se pohybuje nad páskou a muže obsadit polícko. 
 * Hlava je identifikována celým kladným císlem.
 */
public class TapeHead{
private int id;
	public TapeHead(int id){
		this.id = id;
	}
	public int id(){
		return id;
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TapeHead))
			return false;
		TapeHead other = (TapeHead) o;
		return id==other.id;
	}
	@Override
	public int hashCode (){
		return id+ TapeHead.class.hashCode(); // prevent from just returning ID
	}
}
