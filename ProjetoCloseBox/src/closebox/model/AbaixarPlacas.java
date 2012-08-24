package closebox.model;

import closebox.activity.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class AbaixarPlacas {
	
	private int[] ordemDasPlacas;
	private boolean placa7abaixada = false;
	private boolean placa8abaixada = false;
	private boolean placa9abaixada = false;
	
	public AbaixarPlacas(){
		ordemDasPlacas = new int[9];
	}
	
	public int identificarPlacaDown(View view){
		int idPlacaDown;
		
		switch (view.getId()) {
		case R.id.imageViewPlaca_1:
			idPlacaDown = R.id.imageViewPD1;
			break;
			
		case R.id.imageViewPlaca_2:
			idPlacaDown = R.id.imageViewPD2;
			break;
			
		case R.id.imageViewPlaca_3:
			idPlacaDown = R.id.imageViewPD3;
			break;
			
		case R.id.imageViewPlaca_4:
			idPlacaDown = R.id.imageViewPD4;
			break;
			
		case R.id.imageViewPlaca_5:
			idPlacaDown = R.id.imageViewPD5;
			break;
			
		case R.id.imageViewPlaca_6:
			idPlacaDown = R.id.imageViewPD6;
			break;
			
		case R.id.imageViewPlaca_7:
			idPlacaDown = R.id.imageViewPD7;
			break;
			
		case R.id.imageViewPlaca_8:
			idPlacaDown = R.id.imageViewPD8;
			break;
			
		case R.id.imageViewPlaca_9:
			idPlacaDown = R.id.imageViewPD9;
			break;

		default:
			idPlacaDown =  1;
			break;
		}
		
		return idPlacaDown;
	}
	
	public int[] embaralharPlacas(){
		int[] arrayImagens  = {R.drawable.placa_1, R.drawable.placa_2, R.drawable.placa_3, R.drawable.placa_4
				,R.drawable.placa_5, R.drawable.placa_6, R.drawable.placa_7, R.drawable.placa_8, R.drawable.placa_9};

		int[] novoArrayImagens = new int[9];
		int i = 0;

		while(i < 9){
			if(i == 0){
				int sorteio = (int)Math.ceil((Math.random()*9) - 1);
				novoArrayImagens[i] = arrayImagens[sorteio];
				ordemDasPlacas[i] = sorteio;
				i++;
			}else{
				int j;
				int sorteio = (int)Math.ceil((Math.random()*9) - 1);
				
				for(j = 0; j < i; j++){
					if(novoArrayImagens[j] == arrayImagens[sorteio])
						break;
				}
				if(j == i){
					novoArrayImagens[i] = arrayImagens[sorteio];
					ordemDasPlacas[i] = sorteio;
					i++;
				}
			}
		}
		return novoArrayImagens;
	}
	
	public int[] getOrdemDasPlacas(){
		return ordemDasPlacas;
	}
	
	public int getPosicaoDaPlaca(View view){
		int posicao;
		
		switch (view.getId()) {
		case R.id.imageViewPlaca_1:
			posicao = 1;
			break;
			
		case R.id.imageViewPlaca_2:
			posicao = 2;
			break;
			
		case R.id.imageViewPlaca_3:
			posicao = 3;
			break;
			
		case R.id.imageViewPlaca_4:
			posicao = 4;
			break;
			
		case R.id.imageViewPlaca_5:
			posicao = 5;
			break;
			
		case R.id.imageViewPlaca_6:
			posicao = 6;
			break;
			
		case R.id.imageViewPlaca_7:
			posicao = 7;
			placa7abaixada = true;
			break;
			
		case R.id.imageViewPlaca_8:
			posicao = 8;
			placa8abaixada = true;
			break;
			
		case R.id.imageViewPlaca_9:
			posicao = 9;
			placa9abaixada = true;
			break;

		default:
			posicao =  1;
			break;
		}
		
		return posicao;
	}
	
	public boolean placasAltasAbaixadas(){
		if(placa7abaixada && placa8abaixada && placa9abaixada) return true;
		else												   return false;
	}
	
	public void setFlagPlacasAltasFalse(int placa){
		if(placa == 7) 	   placa7abaixada = false;
		else if(placa == 8)placa8abaixada = false;
		else			   placa9abaixada = false;
	}
	
	
	
	
	
	//private int getImagem(){}
	
	
	
	
	
	
	
	
	
	
	
	
}
