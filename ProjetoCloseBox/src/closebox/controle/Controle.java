package closebox.controle;

import android.view.View;
import closebox.model.*;

public class Controle {
	
	private AbaixarPlacas abaixarPlacas;
	
	public Controle(){
		abaixarPlacas = new AbaixarPlacas();
	}
	
	public int sorteio(){
		return abaixarPlacas.sorteio();
	}
	
	public int identificarPlacaDown(View view){
		return abaixarPlacas.identificarPlacaDown(view);
	}
	
	public int[] embaralharPlacas(){
		return abaixarPlacas.embaralharPlacas();
	}
	
	public int[] getOrdemDasPlacas(){
		return abaixarPlacas.getOrdemDasPlacas();
	}
	
	public int getPosicaoDaPlaca(View view){
		return abaixarPlacas.getPosicaoDaPlaca(view);
	}
	
	public boolean placasAltasAbaixadas(){
		return abaixarPlacas.placasAltasAbaixadas();
	}
	
	public void setFlagPlacasAltasFalse(int placa){
		abaixarPlacas.setFlagPlacasAltasFalse(placa);
	}
}
