package ija.homework1.tape;

/*
 * Reprezentuje polícko na pásce, každé polícko je identifikováno pozicí
 * (celé kladné cislo), tj. umistení polícka na pásce.
 */
public class TapeField{
private int position;
private TapeObject field = null;
private TapeHead head = null;

public TapeField (int p){
		position = p;
	}
	public TapeField (int p, TapeObject obj){
		position = p;
		field = obj;
	}
	public int position(){
		return position;
	}
	public boolean seize(TapeHead head){
		if (canSeize()){
			this.head = head;
			return true;
		}
		else return false;
	}
	public TapeHead leave(){
		TapeHead tmphead = head;
		head = null;
		return tmphead; 
	}
	public boolean canSeize(){
			return (head == null && (field!=null ? field.canSeize() :true));
			 //if field==null, there is no object at this field ->implicit true
	}
	public boolean open(){
			return field == null ? false: field.open();
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TapeField))
			return false;
		TapeField other = (TapeField) o;
		return (other.position == position &&
				(field != null ? field.equals(other.field) : other.field == null) &&
				(head  != null ? head.equals(other.head)   : other.head  == null));
	}
	@Override
	public int hashCode (){
		return position + 
			   (head != null ? head.hashCode() :TapeField.class.hashCode())+
			   (field != null ? field.hashCode() :TapeField.class.hashCode());
	}
}
