package closebox.model;

import java.util.ArrayList;

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
	private boolean placa7abaixada;
	private boolean placa8abaixada;
	private boolean placa9abaixada;
	private boolean primeiraPlaca;
	private int placaAnterior;
	private int qtdePlacas;
	private int qtdeJogadores;
	private int jogadorAtual;
	private int diferenca;
	private boolean girarDados;
	private boolean levantarPlacas;
	private boolean calcularPontosRestantes;
	private boolean mostraRanking;
	private boolean perguntarSobreDado;
	private JogaDado jogaDado;
	private Pontos pontos;
	private ArrayList<String> listaJogadores;
	
	public AbaixarPlacas(){
		ordemDasPlacas = new int[9];
		placa7abaixada = false;
		placa8abaixada = false;
		placa9abaixada = false;
		qtdePlacas = 9;
		primeiraPlaca = true;
		girarDados = false;
		levantarPlacas = false;
		calcularPontosRestantes = false;
		mostraRanking = false;
		perguntarSobreDado = false;
	}
		
	public boolean isPerguntarSobreDado() {
		return perguntarSobreDado;
	}

	public void setPerguntarSobreDado(boolean perguntarSobreDado) {
		this.perguntarSobreDado = perguntarSobreDado;
	}

	public boolean isMostraRanking() {
		return mostraRanking;
	}

	public void setMostraRanking(boolean mostraRanking) {
		this.mostraRanking = mostraRanking;
	}

	public int getPlacaAnterior() {
		return placaAnterior;
	}

	public boolean isCalcularPontosRestantes() {
		return calcularPontosRestantes;
	}

	public void setJogaDado(JogaDado jogaDado){
		this.jogaDado = jogaDado;
	}
	
	public void setPontos(Pontos pontos){
		this.pontos = pontos;
	}
	
	public boolean isGirarDados() {
		return girarDados;
	}
	
	public void setGirarDados(boolean girarDados){
		this.girarDados = girarDados;
	}
	
	public boolean isLevantarPlacas() {
		return levantarPlacas;
	}

	public void setLevantarPlacas(boolean levantarPlacas) {
		this.levantarPlacas = levantarPlacas;
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
	
	public void setQuantidadeJodador(int qtdeJogadores){
		this.qtdeJogadores = qtdeJogadores;
	}
	
	public int getQuantidadejogador(){
		return qtdeJogadores;
	}
	
	public void setListaDeJogadores(ArrayList<String> listaJogadores){
		this.listaJogadores = listaJogadores;
	}
	
	public ArrayList<String> getListaDeJogadores(){
		return listaJogadores;
	}
	
	public void setPlacaAnterior(int placa){
		placaAnterior = placa;
	}
	
	public void setPrimeiraPlaca(boolean primeiraPlaca){
		this.primeiraPlaca = primeiraPlaca;
	}
	
	public void gerenciaJogada(int placa){
		int somaDados;
		if(!jogaDado.getEhUmDado())somaDados = jogaDado.resultadoDaSoma();
		else		               somaDados = jogaDado.getValorDado1();

		if(primeiraPlaca){
			if(placa == somaDados){
				qtdePlacas --;
				pontos.calculaJogada(placa, primeiraPlaca);
				girarDados = true;
				levantarPlacas = false;
				mostraRanking = true;
				perguntarSobreDado = true;
			}else{
				primeiraPlaca = false;
				diferenca = somaDados - placa;
				placaAnterior = placa;
			}
		}else{
			if(placa==diferenca){
				qtdePlacas -= 2;
				pontos.calculaJogada((placa+placaAnterior), primeiraPlaca);
				primeiraPlaca = true;
				girarDados = true;
				mostraRanking = true;
				perguntarSobreDado = true;
			}else{
				levantarPlacas = true;
			}
		}
		if(qtdePlacas == 0){
			pontos.adicionarPontosRanking(30);
			jogaDado.setGirarDado1(false);
			jogaDado.setGirarDado2(false);
			calcularPontosRestantes = true;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
