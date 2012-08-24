package closebox.model;

public class JogaDado {
	
	public int sorteio(){
		int sorteio = (int)Math.ceil((Math.random()*6));	
		return sorteio;
	}
}
