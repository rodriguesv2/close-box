package closebox.model;

public class Jogador {
	private String nome;
	private int pontosDeVida;
	
	public Jogador(String nome, int pontosDeVida){
		this.nome = nome;
		this.pontosDeVida = pontosDeVida;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public int getPontosDeVida(){
		return pontosDeVida;
	}
	
	public void setPontosDeVida(int pontosDeVida){
		this.pontosDeVida = pontosDeVida;
	}
}
