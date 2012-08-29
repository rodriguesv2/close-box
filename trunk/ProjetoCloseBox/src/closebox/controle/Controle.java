package closebox.controle;

import java.util.ArrayList;

import android.view.View;
import closebox.model.*;

public class Controle{
	
	private AbaixarPlacas abaixarPlacas;
	private JogaDado jogaDado;
	private Pontos pontos;
	
	public Controle(){
		abaixarPlacas = new AbaixarPlacas();
		jogaDado = new JogaDado();
		pontos = new Pontos();
		incluirJogaDadoEPontosAoAbaixarPlacas();
	}
	
	private void incluirJogaDadoEPontosAoAbaixarPlacas(){
		abaixarPlacas.setJogaDado(jogaDado);
		abaixarPlacas.setPontos(pontos);
	}
	
	public int qualEhAPosicaoDaPlaca(int valor){
		return abaixarPlacas.qualEhAPosicaoDaPlaca(valor);
	}
	
	public boolean isPerguntarSobreDado() {
		return abaixarPlacas.isPerguntarSobreDado();
	}

	public void setPerguntarSobreDado(boolean perguntarSobreDado) {
		abaixarPlacas.setPerguntarSobreDado(perguntarSobreDado);
	}
	
	public boolean isMostraRanking() {
		return abaixarPlacas.isMostraRanking();
	}

	public void setMostraRanking(boolean mostraRanking) {
		abaixarPlacas.setMostraRanking(mostraRanking);
	}

	
	public int getPlacaAnterior() {
		return abaixarPlacas.getPlacaAnterior();
	}
	
	public void setPlacaAnterior(int placa){
		abaixarPlacas.setPlacaAnterior(placa);
	}
	
	public boolean isLevantarPlacas() {
		return abaixarPlacas.isLevantarPlacas();
	}

	public void setLevantarPlacas(boolean levantarPlacas) {
		abaixarPlacas.setLevantarPlacas(levantarPlacas);
	}
	
	public boolean isCalcularPontosRestantes() {
		return abaixarPlacas.isCalcularPontosRestantes();
	}
	
	public void gerenciaJogada(int placa){
		abaixarPlacas.gerenciaJogada(placa);
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
	
	public void setPrimeiraPlaca(boolean primeiraPlaca){
		abaixarPlacas.setPrimeiraPlaca(primeiraPlaca);
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
	
	public int resultadoDaSoma(){
		return jogaDado.resultadoDaSoma();
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
		return jogaDado.getGirarDado2();
	}
	
	public boolean isGirarDados() {
		return abaixarPlacas.isGirarDados();
	}
	
	public void setGirarDados(boolean girarDados){
		abaixarPlacas.setGirarDados(girarDados);
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
	
	public void setEhUmDado(boolean ehUmDado){
		jogaDado.setEhUmDado(ehUmDado);
	}
	
	public boolean getEhUmDado(){
		return jogaDado.getEhUmDado();
	}
	
	public void calculaJogada(int aSerSubtraido, boolean ehUmaPlaca){
		pontos.calculaJogada(aSerSubtraido, ehUmaPlaca);
	}
	
	public int getPontosRestantes(){
		return pontos.getPontosRestantes();
	}
	
	public int getPontosRanking(){
		return pontos.getPontosRanking();
	}
	
	public void setPontosRanking(int ranking){
		pontos.setPontosRanking(ranking);
	}
	
	public void setQuantidadeJodador(int qtdeJogadores){
		abaixarPlacas.setQuantidadeJodador(qtdeJogadores);
	}
	
	public int getQuantidadejogador(){
		return abaixarPlacas.getQuantidadejogador();
	}
	
	public void setListaDeJogadores(ArrayList<String> listaJogadores){
		abaixarPlacas.setListaDeJogadores(listaJogadores);
	}
	
	public ArrayList<String> getListaDeJogadores(){
		return abaixarPlacas.getListaDeJogadores();
	}
	
	public ArrayList<Integer> getListaPontuacao() {
		return pontos.getListaPontuacao();
	}

	public void setListaPontuacao(ArrayList<Integer> listaPontuacao) {
		pontos.setListaPontuacao(listaPontuacao);
	}
}
