package closebox.activity;

import java.util.ArrayList;

import closebox.model.Jogador;
import closebox.controle.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TelaJogoActivity extends Activity{
	private TextView jogador1;
	private TextView jogador2;
	private TextView jogador3;
	private TextView pontos1;
	private TextView pontos2;
	private TextView pontos3;
	private Intent dadosIntent;
	private Handler handler;
	private boolean girar;
	private boolean girar2;
	private ImageView dado1;
	private ImageView dado2;
	private boolean dado1Parado = false;//garante que o dado foi acionado
	private boolean dado2Parado = false;//garante que o dado foi acionado
	private int valorDado1;
	private int valorDado2;
	private Runnable runnable1;
	private Runnable runnable2;
	private int[]listaDados = {R.drawable.dado_face1,R.drawable.dado_face2,R.drawable.dado_face3,
			R.drawable.dado_face4,R.drawable.dado_face5,R.drawable.dado_face6};
	
	private int pontuacao;
	private int somaDados = 0;
	private boolean primeiraPlaca = true;
	private int diferenca;
	private int placaAnterior = 0;
	private ImageView placa1;
	private ImageView placa2; 
	private ImageView placa3; 
	private ImageView placa4; 
	private ImageView placa5; 
	private ImageView placa6; 
	private ImageView placa7; 
	private ImageView placa8; 
	private ImageView placa9; 
	private ImageView placaDown1;
	private ImageView placaDown2;
	private ImageView placaDown3;
	private ImageView placaDown4;
	private ImageView placaDown5;
	private ImageView placaDown6;
	private ImageView placaDown7;
	private ImageView placaDown8;
	private ImageView placaDown9;
	private ImageView dadoLancado1;
	private ImageView dadoLancado2;
	private Intent intentOut;
	private int qtdePlacas = 9;
	private ArrayList<String> listaJogadores;
	private ArrayList<Integer> listaPontuacao;
	private int qtdeJogadores;
	private int jogadorAtual;
	private int pontosRestantes = 45;
	private TextView apontador;
	private boolean ehUmDado = false;
	private boolean jahFoiPerguntadoSobreDados = false;
	private TextView qualSomaDasPlacas;
	private EditText campoSomaPlacas;
	private Button okSomaPlacas;
	private TextView rodadaAtual;
	private ArrayList<Integer> listaRodadas;
	private int rodada;
	private boolean calcularPontos = false;
	private boolean jahDesistiu = false;
	private Controle controle;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_jogo);
		controle = new Controle();
		
		threadDado1();
		threadDado2();
		handler = new Handler();
		dadosIntent = getIntent();
		instanciarObjetos();
		mostrarJogadores();
		dadoLancado1.setVisibility(View.INVISIBLE);
		dadoLancado2.setVisibility(View.INVISIBLE);
	}
	
	//Mostra apenas as TextViews com conteudo.
	private void mostrarJogadores(){
		//Recebe dados da activity que a chamou.
		qtdeJogadores = dadosIntent.getIntExtra("numeroDeJogadores",2);
		listaJogadores = dadosIntent.getStringArrayListExtra("arrayJogadores");
		listaPontuacao = dadosIntent.getIntegerArrayListExtra("pontuacaoJogadores");
		jogadorAtual = dadosIntent.getIntExtra("jogadorAtual", 0);
		pontuacao = listaPontuacao.get(jogadorAtual);
		listaRodadas = dadosIntent.getIntegerArrayListExtra("listaRodadas");
		rodada = listaRodadas.get(jogadorAtual);
		
		Toast toast = Toast.makeText(getBaseContext(), "Agora é a vez de "+ listaJogadores.get(jogadorAtual), Toast.LENGTH_SHORT);
		toast.show();
		
		if(qtdeJogadores == 1){
			jogador2.setVisibility(View.INVISIBLE);
			jogador3.setVisibility(View.INVISIBLE);
			pontos2.setVisibility(View.INVISIBLE);
			pontos3.setVisibility(View.INVISIBLE);
			
			
			jogador1.setText(listaJogadores.get(0)+"  ");
			pontos1.setText(listaPontuacao.get(0)+"");
			
		
		}else if(qtdeJogadores == 2){
			jogador3.setVisibility(View.INVISIBLE);
			pontos3.setVisibility(View.INVISIBLE);
			
			jogador1.setText(listaJogadores.get(0)+"  ");
			pontos1.setText(listaPontuacao.get(0)+"");
			jogador2.setText(listaJogadores.get(1)+"  ");
			pontos2.setText(listaPontuacao.get(1)+"");
		
		}else{
			jogador1.setText(listaJogadores.get(0)+"  ");
			pontos1.setText(listaPontuacao.get(0)+"");
			jogador2.setText(listaJogadores.get(1)+"  ");
			pontos2.setText(listaPontuacao.get(1)+"");
			jogador3.setText(listaJogadores.get(2)+"  ");
			pontos3.setText(listaPontuacao.get(2)+"");
		}
		rodadaAtual.setText(rodada+"");
		apontaJogador(jogadorAtual);
	}
	
	
	private void instanciarObjetos(){
		jogador1 = (TextView)findViewById(R.id.jogadorText1);
		jogador2 = (TextView)findViewById(R.id.jogadorText2);
		jogador3 = (TextView)findViewById(R.id.jogadorText3);
		pontos1 = (TextView)findViewById(R.id.pontosText1);
		pontos2 = (TextView)findViewById(R.id.pontosText2);
		pontos3 = (TextView)findViewById(R.id.pontosText3);
		dado1 = (ImageView)findViewById(R.id.imageView1);
		dado2 = (ImageView)findViewById(R.id.imageView2);
		placa1 = (ImageView)findViewById(R.id.imageViewPlaca_1);
		placaDown1 = (ImageView)findViewById(R.id.imageViewPD1);
		placa2 = (ImageView)findViewById(R.id.imageViewPlaca_2);
		placaDown2 = (ImageView)findViewById(R.id.imageViewPD2);
		placa3 = (ImageView)findViewById(R.id.imageViewPlaca_3);
		placaDown3 = (ImageView)findViewById(R.id.imageViewPD3);
		placa4 = (ImageView)findViewById(R.id.imageViewPlaca_4);
		placaDown4 = (ImageView)findViewById(R.id.imageViewPD4);
		placa5 = (ImageView)findViewById(R.id.imageViewPlaca_5);
		placaDown5 = (ImageView)findViewById(R.id.imageViewPD5);
		placa6 = (ImageView)findViewById(R.id.imageViewPlaca_6);
		placaDown6 = (ImageView)findViewById(R.id.imageViewPD6);
		placa7 = (ImageView)findViewById(R.id.imageViewPlaca_7);
		placaDown7 = (ImageView)findViewById(R.id.imageViewPD7);
		placa8 = (ImageView)findViewById(R.id.imageViewPlaca_8);
		placaDown8 = (ImageView)findViewById(R.id.imageViewPD8);
		placa9 = (ImageView)findViewById(R.id.imageViewPlaca_9);
		placaDown9 = (ImageView)findViewById(R.id.imageViewPD9);
		dadoLancado1 = (ImageView)findViewById(R.id.imageViewDadoLancado1);
		dadoLancado2 = (ImageView)findViewById(R.id.imageViewDadoLancado2);
		qualSomaDasPlacas = (TextView)findViewById(R.id.textView1);
		campoSomaPlacas = (EditText)findViewById(R.id.campo_pontos_de_vida);
		okSomaPlacas = (Button)findViewById(R.id.okSomaPlacas);
		rodadaAtual = (TextView)findViewById(R.id.rodada);
	}
	
	//Adiciona um # a frente do nome da rodada.
	public void apontaJogador(int jogador){
		if(jogador == 0){
			apontador = (TextView)findViewById(R.id.checkedTextView1);
			apontador.setVisibility(View.VISIBLE);
		}else if(jogador == 1){
			apontador = (TextView)findViewById(R.id.checkedTextView2);
			apontador.setVisibility(View.VISIBLE);
		}else{
			apontador = (TextView)findViewById(R.id.checkedTextView3);
			apontador.setVisibility(View.VISIBLE);
		}
	}
	
	//Faz o dado 1 girar.
	public void threadDado1() {
		runnable1 = new Runnable() {
			int i = 2;
			@Override
			public void run() {
				girar = true;
				while (girar) {
					final int value = i;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						@Override
						public void run() {
							if(value<6){
							dado1.setImageResource(listaDados[value]);
							}else{
								i = 0;
								dado1.setImageResource(listaDados[i]);
							}
						}
					});
					i++;
				}
			}
		};
		new Thread(runnable1).start();
		dado1Parado = false;
	}
	
	//Faz o dado 2 girar.
	public void threadDado2() {
		// Do something long
		runnable2 = new Runnable() {
			int i = 4;
			@Override
			public void run() {
				girar2 = true;
				while (girar2) {
					
					final int value = i;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						@Override
						public void run() {
							if(value<6){
							dado2.setImageResource(listaDados[value]);
							}else{
								i = 0;
								dado2.setImageResource(listaDados[i]);
							}
						}
					});
					i++;
				}
			}
		};
		new Thread(runnable2).start();
		dado2Parado = false;
	}
	
	
	//Faz com que os dados voltem a posição de jogar.
	public void escondeDadoLancado(){
		if(!ehUmDado){
			dadoLancado1.setVisibility(View.INVISIBLE);
			dadoLancado2.setVisibility(View.INVISIBLE);
			dado1.setVisibility(View.VISIBLE);
			dado2.setVisibility(View.VISIBLE);
		}else{
			dadoLancado1.setVisibility(View.INVISIBLE);
			dado1.setVisibility(View.VISIBLE);
		}
	}
	
	//Faz com que o dado 1 seja jogado.
	public void acaoDado1(View view){
		dado1.setVisibility(View.INVISIBLE);
		girar = false;
		dado1Parado = true;
		sortearDado1();
	}
	
	//Faz com que o numero sorteado corresponda a imagem do dado.
	public void sortearDado1(){
		valorDado1 = controle.sorteio();
		dadoLancado1.setImageResource(listaDados[valorDado1-1]);
		dadoLancado1.setVisibility(View.VISIBLE);
	}
	
	//Faz com que o dado 2 seja jogado.
	public void acaoDado2(View view){
		dado2.setVisibility(View.INVISIBLE);
		girar2 = false;
		dado2Parado = true;
		sortearDado2();
	}
	
	//Faz com que o numero sorteado corresponda a imagem do dado.
	public void sortearDado2(){
		valorDado2 = controle.sorteio();
		dadoLancado2.setImageResource(listaDados[valorDado2-1]);
		dadoLancado2.setVisibility(View.VISIBLE);
	}
	
	public void inutilizarDado2(){
		dado2.setVisibility(View.INVISIBLE);
	}
	
	//-------------------------------------------------------------------------------------------------------------//
	//caso de uso abaixar placas
	
	public void abaixarPlaca(View view){
		if(((dado1Parado && dado2Parado) || (dado1Parado && ehUmDado)) && !calcularPontos){
			ImageView placa = (ImageView)findViewById(view.getId());
			ImageView placaDown = (ImageView)findViewById(controle.identificarPlacaDown(view));
			
			placa.setVisibility(View.INVISIBLE);
			placaDown.setVisibility(View.VISIBLE);
				
			calculaJogada(controle.getPosicaoDaPlaca(view));
		}
	}
	
	/*Calcula a soma dos dados e valida a ação levando em conta se uma ou duas
	 * placas foram abaixadas.
	 */
	public void calculaJogada(int placa){
		if(!ehUmDado)somaDados = valorDado1 + valorDado2;
		else					  somaDados = valorDado1;
		
		if(primeiraPlaca){
			if(placa==somaDados){
				qtdePlacas --;
				pontosRestantes -= placa;
				rodada +=10;
				rodadaAtual.setText(rodada+"");
				threadDado1();
				threadDado2();
				escondeDadoLancado();
				subirDialogoSobreDados();
			}else{
				primeiraPlaca = false;
				diferenca = somaDados - placa;
				placaAnterior  = placa;
			}
		}else{
			if(placa==diferenca){
				qtdePlacas = qtdePlacas -2;
				pontosRestantes -= (placa+placaAnterior);
				primeiraPlaca =true;
				rodada +=5;
				rodadaAtual.setText(rodada+"");
				threadDado1();
				threadDado2();
				escondeDadoLancado();
				subirDialogoSobreDados();
			}else{
				levantar2Placas(placa, placaAnterior);
			}
		}
		if(qtdePlacas == 0){
			rodada +=30;
			girar = false;
			girar2 = false;
			calculaPontosRestantes();
		}
	}

	//Levanta as 2 placas.
	private void levantar2Placas(int placa, int placaAnterior) {
		levantarPlaca(placa);
		levantarPlaca(placaAnterior);
		dado1Parado = true;
		dado2Parado = true;
		primeiraPlaca = true;
		this.placaAnterior = 0;
		
		mensagemJogadaErrada(placa, placaAnterior);
	}

	//Levanta uma placa.
	private void levantarPlaca(int placa) {
		switch (placa) {
		case 1:
			placa1.setVisibility(View.VISIBLE);
			placaDown1.setVisibility(View.INVISIBLE);
			break;
		
		case 2:
			placa2.setVisibility(View.VISIBLE);
			placaDown2.setVisibility(View.INVISIBLE);
			break;
			
		case 3:
			placa3.setVisibility(View.VISIBLE);
			placaDown3.setVisibility(View.INVISIBLE);
			break;
			
		case 4:
			placa4.setVisibility(View.VISIBLE);
			placaDown4.setVisibility(View.INVISIBLE);
			break;
			
		case 5:
			placa5.setVisibility(View.VISIBLE);
			placaDown5.setVisibility(View.INVISIBLE);
			break;
			
		case 6:
			placa6.setVisibility(View.VISIBLE);
			placaDown6.setVisibility(View.INVISIBLE);
			break;
			
		case 7:
			placa7.setVisibility(View.VISIBLE);
			placaDown7.setVisibility(View.INVISIBLE);
			controle.setFlagPlacasAltasFalse(placa);
			break;
			
		case 8:
			placa8.setVisibility(View.VISIBLE);
			placaDown8.setVisibility(View.INVISIBLE);
			controle.setFlagPlacasAltasFalse(placa);
			break;
			
		case 9:
			placa9.setVisibility(View.VISIBLE);
			placaDown9.setVisibility(View.INVISIBLE);
			controle.setFlagPlacasAltasFalse(placa);
			break;

		default:
			break;
		}
	}
		
	//Verifica se já foi perguntado se deseja jogar com apenas 1 dado.
	public void subirDialogoSobreDados(){
		if(controle.placasAltasAbaixadas() && !jahFoiPerguntadoSobreDados){
			determinarQuantidadeDeDados();
			jahFoiPerguntadoSobreDados = true;
		}
	}
	
	//Detemina se deve haver dois dados.
	public void determinarQuantidadeDeDados(){
		if(controle.placasAltasAbaixadas()){
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("Sugestão");
			dialogo.setMessage("As placas 7, 8 e 9 foram abaixadas.\n Deseja jogar " +
					"com 1 dado?");
			
			dialogo.setPositiveButton("Sim", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ehUmDado = true;
					inutilizarDado2();
				}
			});
			dialogo.setNegativeButton("Não", null);
			dialogo.show();
			
		}
	}
	
	//Despara uma mensagem dizendo que a jogada é invalida.
	private void mensagemJogadaErrada(int placa1, int placa2){
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("JOGADA ERRADA");
		dialogo.setMessage("A soma de "+ placa2 +" e "+ placa1 + " não corresponde á soma dos dados!");
		
		dialogo.setPositiveButton("OK", new OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialogo, int qualBotao) {
				try {
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
    	dialogo.show();
	}
	
	//Exibe caixa de dialogo para o caso do jogador não conseguir mais jogar.
	public void desistir(View view){
		if(!jahDesistiu){
			jahDesistiu = true;
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("NÃO É POSSÍVEL PROSSEGUIR!");
			dialogo.setMessage("Tem certeza que não há jogadas possíveis?");

			dialogo.setPositiveButton("CONFIRMAR", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					mostrarCalcularPontos();
				}
			});

			dialogo.setNegativeButton("TENTAR NOVAMENTE", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					try {
						this.finalize();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			});
			dialogo.show();
		}
	}
	
	public void calculaPontosRestantes(){//No Sprint apropriado será implementado corretamente.
		// inserir ou enviar para o Activity CALCULAR PONTOS DE VIDA
		
		intentOut = new Intent(this, ControllerActivity.class);
		
		intentOut.putExtra("botao", "calcularPontosDeVida");
		intentOut.putExtra("numeroDeJogadores", qtdeJogadores);//quantidade de jogadores (int)
		intentOut.putStringArrayListExtra("arrayJogadores", listaJogadores);//lista de nomes dos jogadores (String)
		intentOut.putIntegerArrayListExtra("pontuacaoJogadores", listaPontuacao);//lista de pontuacao (int)
		intentOut.putExtra("jogadorAtual", jogadorAtual);// o jogador atual (int)
		intentOut.putExtra("pontosRodada", pontosRestantes);// a soma das placas nao abaixadas (int)
		listaRodadas.set(jogadorAtual, rodada);
		intentOut.putIntegerArrayListExtra("listaRodadas", listaRodadas);//lista das rodadas (int)
		super.finish();
		startActivity(intentOut);
	}
	
	@Override
	public void onBackPressed(){
		
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Sair do Jogo");
		dialogo.setMessage("Deseja realmente sair?");
		
		dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		
		dialogo.setNegativeButton("Não", null);
		
		dialogo.show();
	}
	
	public void mostrarCalcularPontos(){
		campoSomaPlacas.setVisibility(View.VISIBLE);
		qualSomaDasPlacas.setVisibility(View.VISIBLE);
		okSomaPlacas.setVisibility(View.VISIBLE);
		calcularPontos = true;
		dado1.setVisibility(View.INVISIBLE);
		dado2.setVisibility(View.INVISIBLE);
		
	}
	
	public void validarCalculoDoUsuario(View view){
		try {
			int somaDasPlacas = Integer.parseInt(campoSomaPlacas.getText().toString());
			
			if(somaDasPlacas == pontosRestantes){
				calculaPontosRestantes();
			}else{
				dialogoErroDeCalculo(null);
			}
		} catch (NumberFormatException e) {
			dialogoErroDeCalculo(e);
		}		
	}
	
	public void dialogoErroDeCalculo(NumberFormatException e){
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		
		dialogo.setTitle("Erro");
		if(e == null) dialogo.setMessage("Calculo não está correto.");
		else          dialogo.setMessage("O campo está vazio.");
		
		dialogo.setNegativeButton("Ok", null);
		dialogo.show();
	}
	
	public void gameOver(){
    	intentOut = new Intent(this, ControllerActivity.class);
    	intentOut.putExtra("botao", "gameOver");
    	super.finish();
		startActivity(intentOut);
	}
}