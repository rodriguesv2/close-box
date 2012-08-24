package closebox.controle;

import android.view.View;
import closebox.model.*;

public class Controle {
	
	private AbaixarPlacas abaixarPlacas;
	private JogaDado jogaDado;
	
	public Controle(){
		abaixarPlacas = new AbaixarPlacas();
		jogaDado = new JogaDado();
	}
	
	public int sorteio(){
		return jogaDado.sorteio();
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
