package closebox.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ControllerActivity extends Activity{
	private Intent intentIn;
	private Intent intentOut;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		selecionaBotao();
		
	}
	
	private void selecionaBotao(){
		intentIn = getIntent();
		
		String botao = intentIn.getStringExtra("botao");
		
		
		if(botao.equals("inicio")){
			
			intentOut = new Intent(this, NumeroDeJogadoresActivity.class);
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("options")){
			intentOut = new Intent(this, OptionsActivity.class);
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("score")){
			intentOut = new Intent(this, ScoreActivity.class);
			intentOut.putExtra("botao", "lista");
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("sobre")){
			intentOut = new Intent(this, SobreActivity.class);
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("botaoOkInserirNome")){
			intentOut = new Intent(this, TelaJogoActivity.class);
			intentOut.putExtra("numeroDeJogadores", intentIn.getIntExtra("numeroDeJogadores",2));//quantidade de jogadores
			intentOut.putStringArrayListExtra("arrayJogadores", intentIn.getStringArrayListExtra("arrayJogadores"));//lista de jogadores
			intentOut.putIntegerArrayListExtra("pontuacaoJogadores", intentIn.getIntegerArrayListExtra("pontuacaoJogadores"));//lista de pontuacao
			intentOut.putExtra("JogadorAtual", intentIn.getIntExtra("JogadorAtual", 0));// o jogador atual
			intentOut.putIntegerArrayListExtra("listaRodadas", intentIn.getIntegerArrayListExtra("listaRodadas"));
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("botaoCancelarDialogo")){
			intentOut = new Intent(this, MainActivity.class);
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals(null)){
			intentOut = new Intent(this, MainActivity.class);
			super.finish();
			startActivity(intentOut);
		
		}else if(botao.equals("botaoCredito")){
			intentOut = new Intent(this, CreditosActivity.class);
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("botaoHistoria")){
			intentOut = new Intent(this, HistoriaActivity.class);
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("botaoComoJogar")){
			intentOut = new Intent(this, ComoJogarActivity.class);
			intentOut.putExtra("tela", 0);
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("calcularPontosDeVida")){
			int jogAtual = intentIn.getIntExtra("jogadorAtual", 0);
			int pontosVida = intentIn.getIntegerArrayListExtra("pontuacaoJogadores").get(jogAtual);
			int pontosRestantes = intentIn.getIntExtra("pontosRodada", 0);
			int numJogadores = intentIn.getIntExtra("numeroDeJogadores", 0);
			
			if(pontosVida - pontosRestantes <1){
				intentOut = new Intent(this, ScoreActivity.class);
				intentOut.putExtra("botao", "gravarPontuacao");
				intentOut.putExtra("numeroDeJogadores", intentIn.getIntExtra("numeroDeJogadores",2));//quantidade de jogadores(int)
				intentOut.putStringArrayListExtra("arrayJogadores", intentIn.getStringArrayListExtra("arrayJogadores"));//lista de jogadores (String)
				intentOut.putIntegerArrayListExtra("pontuacaoJogadores", intentIn.getIntegerArrayListExtra("pontuacaoJogadores"));//lista de pontuacao (int)
				intentOut.putExtra("jogadorAtual", intentIn.getIntExtra("jogadorAtual", 0));// o jogador atual (int)
				intentOut.putIntegerArrayListExtra("listaRodadas", intentIn.getIntegerArrayListExtra("listaRodadas"));
				super.finish();
				startActivity(intentOut);
				}else{
				ArrayList<Integer> pontuacoes = intentIn.getIntegerArrayListExtra("pontuacaoJogadores");
				pontuacoes.set(jogAtual, pontosVida - pontosRestantes);
				jogAtual++;
				if(jogAtual>=numJogadores){
					jogAtual = 0;
				}
				intentOut = new Intent(this, TelaJogoActivity.class);
				intentOut.putExtra("numeroDeJogadores", intentIn.getIntExtra("numeroDeJogadores",2));//quantidade de jogadores(int)
				intentOut.putStringArrayListExtra("arrayJogadores", intentIn.getStringArrayListExtra("arrayJogadores"));//lista de jogadores (String)
				intentOut.putIntegerArrayListExtra("pontuacaoJogadores", pontuacoes);//lista de pontuacao (int)
				intentOut.putExtra("jogadorAtual", jogAtual);// o jogador atual (int)
				intentOut.putIntegerArrayListExtra("listaRodadas", intentIn.getIntegerArrayListExtra("listaRodadas"));
				super.finish();
				startActivity(intentOut);
			}
			
		}else if(botao.equals("novaRodada")){
			intentOut = new Intent(this, TelaJogoActivity.class);
			intentOut.putExtra("numeroDeJogadores", intentIn.getIntExtra("numeroDeJogadores",2));//quantidade de jogadores
			intentOut.putStringArrayListExtra("arrayJogadores", intentIn.getStringArrayListExtra("arrayJogadores"));//lista de jogadores
			intentOut.putIntegerArrayListExtra("pontuacaoJogadores", intentIn.getIntegerArrayListExtra("pontuacaoJogadores"));//lista de pontuacao
			intentOut.putExtra("jogadorAtual", intentIn.getIntExtra("jogadorAtual", 0));// o jogador atual
			intentOut.putIntegerArrayListExtra("listaRodadas", intentIn.getIntegerArrayListExtra("listaRodadas"));
			super.finish();
			startActivity(intentOut);
			
		}else if(botao.equals("gameOver")){
			intentOut = new Intent(this, GameOverActivity.class);
			super.finish();
			startActivity(intentOut);
		
		}else if(botao.equals("botaoNumeroDeJogadores")){
			intentOut = new Intent(this, InserirNomeActivity.class);
			intentOut.putExtra("numeroDeJogadores", intentIn.getIntExtra("numeroDeJogadores", 1));
			super.finish();
			startActivity(intentOut);
		}
	}

}
