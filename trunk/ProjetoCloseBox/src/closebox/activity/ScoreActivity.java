package closebox.activity;

import java.util.ArrayList;
import java.util.List;
import closebox.activity.ControllerActivity;
import closebox.activity.R;
import closebox.model.Jogador;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




public class ScoreActivity extends Activity {

	private Intent intentIn; //intent responsável por receber as informaçoes necessárias 
	private Intent intentOut; //intent responsável por enviar as informaçoes necessárias 
	private String botao = ""; // Srting usada para comparar o botão que chamou essa Activity
	int numJogadores; // quantidade de jogadores
	int jogAtual, pontoJog; // o indice do jogador atual e a sua pontuação
	private String nomeJog = ""; //o nome do jogador
	private static final String NOME_BANCO = "closebox"; 
	private static final String TABELA = "jogador";
	private static final String ID_TABELA = "_id";
	private static final String CAMPO_NOME = "nome";
	private static final String CAMPO_RODADAS = "rodadas";
	private SQLiteDatabase bancoDados = null; // instancia do banco de dados
	private Cursor cursor;// cursor usado para manipular os dados provenientes do banco de dados
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		abreouCriaBanco();
		intentIn = getIntent();
		defineAcaoOrigem();
	}
	
	/**
	 * Metodo que define a Activity que chamou essa classe, definindo assim o comportamento esperado. (gravar ou exibir a lista)
	 * De acordo com a origem grava, solicita nova rodada ou mostra a view "GAME OVER"
	 */
	private void defineAcaoOrigem(){
		botao = intentIn.getStringExtra("botao");
		
		if (botao.equals("gravarPontuacao")) {
			numJogadores = intentIn.getIntExtra("numeroDeJogadores", 0);//a quantidade dos jogadores
			jogAtual = intentIn.getIntExtra("jogadorAtual", 0);//indice do jogador atual na lista
			pontoJog = intentIn.getIntegerArrayListExtra("listaRodadas").get(jogAtual); //a pontuacao do jogador atual
			nomeJog = intentIn.getStringArrayListExtra("arrayJogadores").get(jogAtual); // o nome do jogador atual
			gerenciarTabela(nomeJog, pontoJog);//grava os dados se obedecerem os criterios para tal
			voltaParaJogo();//inicia uma nova rodada
		}else if(botao.equals("lista")){
			populaTabela();//mostra a lista Score
		
		}else {
			intentOut = new Intent(this, ControllerActivity.class);
			intentOut.putExtra("botao", "gameOver");
			super.finish();
			startActivity(intentOut);
		}
	}
	
	/**
	 * Metodo responsavel por atualizar os dados e envia-los para o Controller que iniciará uma nova rodada.
	 * Esse metodo é chamado após armazenar a pontuação de um Jogador no Banco de Dados
	 */
	private void voltaParaJogo(){
		numJogadores --; //Atualiza a quantidade de jogadores
		if(numJogadores<1){//Caso não haja mais Jogadores: GAME OVER
	    	intentOut = new Intent(this, ControllerActivity.class);
	    	intentOut.putExtra("botao", "gameOver");
	    	fechaBanco();
	    	super.finish();
			startActivity(intentOut);
		}else{
			ArrayList<String> jogadores = intentIn.getStringArrayListExtra("arrayJogadores");
			jogadores.remove(jogAtual);
			ArrayList<Integer> listaPontuacao = intentIn.getIntegerArrayListExtra("pontuacaoJogadores");
			listaPontuacao.remove(jogAtual);
			ArrayList<Integer> rodadas = intentIn.getIntegerArrayListExtra("listaRodadas");
			rodadas.remove(jogAtual);
			if(jogAtual>=numJogadores){
				jogAtual = 0;
			}
			intentOut = new Intent(this, ControllerActivity.class);
			intentOut.putExtra("botao", "novaRodada");
			intentOut.putExtra("numeroDeJogadores", numJogadores);//quantidade de jogadores
			intentOut.putStringArrayListExtra("arrayJogadores", jogadores);//lista de jogadores
			intentOut.putIntegerArrayListExtra("pontuacaoJogadores", listaPontuacao);//lista de pontuacao
			intentOut.putExtra("jogadorAtual", jogAtual);// o jogador atual
			intentOut.putIntegerArrayListExtra("listaRodadas", rodadas);
			fechaBanco();
			super.finish();
			startActivity(intentOut);
		}
	}

	/**
	 * Metodo responsavel por abrir a conexao com o Banco de dados
	 */
	private void abreouCriaBanco() {
		try {
			bancoDados = openOrCreateDatabase(NOME_BANCO, MODE_WORLD_READABLE, null);
			String sql = "CREATE TABLE IF NOT EXISTS "+TABELA+
				"("+ID_TABELA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOME+
				" TEXT, "+CAMPO_RODADAS+" INTEGER);";
			bancoDados.execSQL(sql);
		} catch (Exception erro) {
			mensagemExibir("Erro Banco","Erro ao criar ou abrir Banco de Dados: " + erro.getMessage());
		}
	}

	/**
	 * Metodo responsavel por fechar a conexao com o Banco de Dados
	 */
	private void fechaBanco() {
		try {
			bancoDados.close(); // fecha banco de dados
		} catch (Exception erro) {
			mensagemExibir("Erro Banco", "Erro ao fechar o banco: " + erro.getMessage());
		}
	}

	/**
	 * Metodo responsavel por preencher a ListView que apresenta a lista dos jogadores gravados no Banco de Dados
	 */
	private void populaTabela() {
		setContentView(R.layout.score_listview);
		ListView listView = (ListView) findViewById(R.id.list_view);
		ArrayList<Jogador> listaJogadores; //lista para receber os dados, se existirem no Banco de Dados
		Jogador jogador;
		try {
			cursor = bancoDados.query(TABELA, new String[] {"nome", "rodadas"}, 
					null,// selection,
					null,// selectionArgs,
					null,// groupBy,
					null,// having,
					"rodadas desc",// "order by (nome coluna modo)
					"10"); // Limite de registros retornados(String)
			int numeroRegistros = cursor.getCount();//faz a contagem dos registros
			if (numeroRegistros > 0){
				 listaJogadores = new ArrayList<Jogador>();
				   while(cursor.moveToNext()){//enquanto apontar para a proxima (ou primeira) linha do resultado da pesquisa
					   jogador = new Jogador();
					   jogador.setNome(cursor.getString(cursor.getColumnIndex("nome")));//grava o nome na instancia do joogador
					   jogador.setPontosDeVida(Integer.parseInt(cursor.getString(cursor.getColumnIndex("rodadas"))));//grava a pontuacao na instancia do joogador
					   listaJogadores.add(jogador);//adiciona a instancia de Jogador na lista
				   }
				   //Criação do Adapter e passamos a nossa lista de Jogadores para ele
			       JogadorAdapter adapter2 = new JogadorAdapter(this, listaJogadores);
			       listView.setAdapter(adapter2);//passamos o Adapter como parametro para a ListVIEW
			} else {
				Toast toast = Toast.makeText(getBaseContext(),"Não há jogadores no Score", Toast.LENGTH_SHORT);
				toast.show();
			}
		} catch (Exception erro) {
			mensagemExibir("Erro Banco","Erro buscar dados no banco: " + erro.getMessage());
		}
	}
	
	/**
	 * Metodo que define se a pontuação é suficiente para ser gravada no Banco de Dados de acordo com a quantidade de dados armazenados
	 * e o valor da pontuação
	 * @param nomE o nome do Jogador
	 * @param rodadaS os pontos do Jogador
	 */
	private void gerenciarTabela(String nomE, int rodadaS){
		if(numRegistros()<10){//se houver até 9 jogadores gravados no BD grava direto
			gravarJogador(nomE, rodadaS);
		}else if(rodadaS>getMenorPonto()){//caso haja 10, compara a pontuação com o que há gravado no BD
			gravarJogador(nomE, rodadaS);//grava
			apagarUltimo();//apaga o pior resultado gravado anteriormente
		}else{
			Toast toast = Toast.makeText(getBaseContext(),"Não atingiu score!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	/**
	 * Metodo que busca no Banco de Dados a quantidade de registros
	 * @return a quantidade de registros no BD, caso ocorra erros retorna 10.(para que seja utilizado o critério de maior pontuação)
	 */
	private int numRegistros(){
		int registros = 10;
		try {
			Cursor c = bancoDados.rawQuery("select * from "+TABELA+";", null);
			registros = c.getCount();
			return registros;
		} catch (Exception e) {
			mensagemExibir("Erro Banco", "Erro buscar dados no banco: " + e.getMessage());
			return registros;
		}
	}
	
	/**
	 * Metodo que busca a menor pontuação gravada no BD
	 * @return a menor pontuação
	 */
	private int getMenorPonto(){
		int menor = 0;
		try { 
			cursor = bancoDados.rawQuery("select min("+CAMPO_RODADAS+") from jogador;", null); 
			cursor.moveToFirst();
			menor = cursor.getInt(cursor.getColumnIndex("min(+CAMPO_RODADAS+)"));
			return menor;
		} catch (Exception e) {
			mensagemExibir("Erro Banco", "Erro buscar dados no banco: " + e.getMessage());
			return menor;
		}
	}
	/**
	 * Metodo utilizado para guardar os valores passados por parametro no banco de dados
	 * @param valorNome o nome do Jogador
	 * @param valorPontos os pontos do Jogador
	 */
	private void gravarJogador(String valorNome, int valorPontos){
		try {
			   String sql="INSERT INTO "+TABELA+" ("+CAMPO_NOME+", "+CAMPO_RODADAS+") " 
			   		+ "values ('"+valorNome+"', "+valorPontos+")";		   
			   bancoDados.execSQL(sql);			   		   
		   }
		   catch(Exception erro) {
			   mensagemExibir("Erro Banco", "Erro ao gravar dados no banco: "+erro.getMessage());
			  
		   }
	}
	
	/**
	 * Metodo que busca o valor mais baixo do campo rodadas(integer) e o apaga. 
	 */
	private void apagarUltimo(){
		try {
			int menorValor; //variavel que será usada para armazenar o retorno da busca
			/**
			 * Essa query busca o id da linha que tem o menor valor no campo rodada,
			 *  que foi adicionado mais recente, ou seja se dois jogadores com a mesma pontuacao forem encontrados
			 *   a busca retornara o que foi armazenado por ultimo. (MAIOR ID)
			 */
			String sql = ID_TABELA+" = (select max("+ID_TABELA+") from "+TABELA+
			"where "+CAMPO_RODADAS+" = (select min("+CAMPO_RODADAS+") from "+TABELA+"));";
			
			cursor = bancoDados.query(TABELA, new String[] { ID_TABELA, CAMPO_NOME }, 
					sql, null, null, null, null);
			cursor.moveToFirst();
			menorValor = cursor.getInt(cursor.getColumnIndex(ID_TABELA));
			bancoDados.execSQL("delete from "+TABELA+" where "+ID_TABELA+" = "+menorValor+";");
		} catch (Exception erro) {
			mensagemExibir("Erro Banco", "Erro buscar dados no banco: " +erro.getMessage());
		}
	}
	
	/**
	 * Metodo usado para mostrar uma mensagem de alerta
	 * @param titulo O titulo da mensagem
	 * @param texto o texto objetivo da mensagem
	 */
	private void mensagemExibir(String titulo, String texto) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				ScoreActivity.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();

	}
	
	/**
	 * CLASSE INTERNA USADA PARA ADAPTAR O ARRAYLIST DE JOGADORES À LISTA EXIBIDA NO SCORE
	 */
	public static class JogadorAdapter extends BaseAdapter{
	    private List<Jogador> listJogadores;
	 
	    //Classe utilizada para instanciar os objetos do XML
	    private LayoutInflater inflater;
	     
	    public JogadorAdapter(Context context, List<Jogador> plistJogadores) {
	        this.listJogadores = plistJogadores;
	        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
	 /*
	    public void addItem(final Jogador item) {
	        this.listJogadores.add(item);
	        //Atualizar a lista caso seja adicionado algum item
	        notifyDataSetChanged();
	    }   */ 
	     
	    @Override
	    public int getCount() {
	        return listJogadores.size();
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return listJogadores.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup viewGroup) {
	        //Pega o registro da lista e trasnfere para o objeto JogadorLV
	        Jogador jogadorLV = listJogadores.get(position);
	         
	        //Utiliza o XML score_campos para apresentar na tela
	        convertView = inflater.inflate(R.layout.score_campos, null);
	         
	        //Instância os objetos do XML
	        TextView tvNome = (TextView)convertView.findViewById(R.id.nome2);
	        TextView tvPontos = (TextView)convertView.findViewById(R.id.nome4);
	             
	        //pega os dados que estão no objeto jogadorLV e transfere para os objetos do XML
	        tvNome.setText(jogadorLV.getNome());
	        tvPontos.setText(jogadorLV.getPontosDeVida()+"");
	         
	        return convertView;
	    }
	}
	
	
}
