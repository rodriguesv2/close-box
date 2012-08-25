package closebox.controle;

import android.view.View;
import closebox.activity.R;
import closebox.model.*;

public class Controle {
	
	private AbaixarPlacas abaixarPlacas;
	private JogaDado jogaDado;
	
	public Controle(){
		abaixarPlacas = new AbaixarPlacas();
		jogaDado = new JogaDado();
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
	
	public void setValorDado1(int valorDado1){
		jogaDado.setValorDado1(valorDado1);
	}
	
	public void setValorDado2(int valorDado2){
		jogaDado.setValorDado2(valorDado2);
	}
	
	public int getValorDado1(){
		return jogaDado.getValorDado1();
	}
	
	public int getValorDado2(){
		return jogaDado.getValorDado2();
	}
	
	public void sorteioDado1(){
		jogaDado.sorteioDado1();
	}
	
	public void sorteioDado2(){
		jogaDado.sorteioDado2();
	}
	
	public void setGirarDado1(boolean girar){
		jogaDado.setGirarDado1(girar);
	}
	
	public void setGirarDado2(boolean girar){
		jogaDado.setGirarDado2(girar);
	}
	
	public boolean getGirarDado1(){
		return jogaDado.getGirarDado1();
	}
	
	public boolean getGirarDado2(){
		return jogaDado.getGirarDado1();
	}
	
	public void acaoDado(View view){
		jogaDado.acaoDado(view);
	}
	
	public void setDado1Parado(boolean dado1Parado){
		jogaDado.setDado1Parado(dado1Parado);
	}
	
	public void setDado2Parado(boolean dado2Parado){
		jogaDado.setDado2Parado(dado2Parado);
	}
	
	public boolean getDado1Parado(){
		return jogaDado.getDado1Parado();
	}
	
	public boolean getDado2Parado(){
		return jogaDado.getDado2Parado();
	}
}
