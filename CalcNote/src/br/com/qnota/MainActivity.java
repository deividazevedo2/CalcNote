package br.com.qnota;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	TextView tvQuantidade, tvMediaGeral, tvTituloConfig;
	EditText etMediaOutra;
	RadioGroup rgNotas, rgMediaGeralAdotada;
	int quantidadeDeNotas, mediaGeral;
	RadioButton rbUma, rbDuas, rbTres, rbQuatro, rbMediaSete, rbMediaOutra;
	ImageButton btCalcular, btConfigurar, btSobre;
	Button btConfigVoltar, btConfigSalvar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		chamaTelaInicial();
	}

	/**
	 * M�todo que chama a tela inicial, inicializa os objetos desta tela e as
	 * capturas de bot�es.
	 */
	private void chamaTelaInicial() {
		setContentView(R.layout.activity_main);
		inicializaObjetos();
		listeners();
	}

	/**
	 * M�todo que chama a tela configurar, inicializa os objetos desta tela e as
	 * capturas de bot�es.
	 */
	private void chamaTelaConfigurar() {
		setContentView(R.layout.configurar);
		inicializaObjetos();
		listeners();

	}

	/**
	 * Neste m�todo todos os objetos s�o inicializados de acordo com a tela a
	 * qual pertencem. � importante perceber que os bot�es referentes a tela
	 * inicial est�o dentro de um trycatch. Os objetos das outras telas est�o
	 * dentro de outro trycatch. � assim que deve continuar: cada tela e seus
	 * respectivos bot�es devem estar em trycatch diferentes de outras telas.
	 */
	private void inicializaObjetos() {

		try {
			// INICIALIZA OBJETOS DA TELA INICIAL
			btCalcular = (ImageButton) findViewById(R.id.btCalcular);
			btConfigurar = (ImageButton) findViewById(R.id.btConfigurar);
			btSobre = (ImageButton) findViewById(R.id.btSobre);
		} catch (Exception e) {

		}
		try {
			// INICIALIZA OBJETOS DA TELA CONFIGURA��O
			tvTituloConfig = (TextView) findViewById(R.id.tvTituloConfig);
			tvQuantidade = (TextView) findViewById(R.id.tvQuantidade);
			rgNotas = (RadioGroup) findViewById(R.id.rgQuantidadeNotas);
			rbUma = (RadioButton) findViewById(R.id.rbUma);
			rbDuas = (RadioButton) findViewById(R.id.rbDuas);
			rbTres = (RadioButton) findViewById(R.id.rbTres);
			rbQuatro = (RadioButton) findViewById(R.id.rbQuatro);
			quantidadeDeNotas();

			tvMediaGeral = (TextView) findViewById(R.id.tvMedia);
			rgMediaGeralAdotada = (RadioGroup) findViewById(R.id.rgMediaGeralAdotada);
			rbMediaSete = (RadioButton) findViewById(R.id.rbMediaSete);
			rbMediaOutra = (RadioButton) findViewById(R.id.rbMediaOutra);
			mediaGeralAdotada();

			System.out.println(mediaGeral);
			btConfigVoltar = (Button) findViewById(R.id.btConfigVoltar);
			btConfigSalvar = (Button) findViewById(R.id.btConfigSalvar);
		} catch (Exception e) {

		}
	}

	/**
	 * M�todo que recebe a configura��o do usu�rio para a quantidade de notas a
	 * partir da interface.
	 */
	private void quantidadeDeNotas() {
		rgNotas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup grupo, int opcaoID) {
				if (rbUma.isChecked()) {
					quantidadeDeNotas = 1;
				} else if (rbDuas.isChecked()) {
					quantidadeDeNotas = 2;
				} else if (rbTres.isChecked()) {
					quantidadeDeNotas = 3;
				} else if (rbQuatro.isChecked()) {
					quantidadeDeNotas = 4;
				}

			}
		});
	}

	/**
	 * M�todo que captura a m�dia Geral Adotada que o usu�rio est� configurando
	 * neste momento.
	 */
	private void mediaGeralAdotada() {
		rgMediaGeralAdotada
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup grupo, int opcaoID) {
						if (rbMediaSete.isChecked()) {
							mediaGeral = 7;
						} else if (rbMediaOutra.isChecked()) {
							etMediaOutra = (EditText) findViewById(R.id.etOutra);
							String media = etMediaOutra.getText().toString();
							if (media.isEmpty()) {
								mediaGeral = 7;
							} else {
								mediaGeral = Integer.parseInt(media);
							}
						}
					}
				});

	}

	/**
	 * M�todo utilizado para exibir caixa de di�logo com mensagens para o
	 * usu�rio. � necess�rio passar como par�metro o t�tulo e a mensagem em si.
	 * Esta caixa de di�logo apresentar� as informa��es e um bot�o "OK" apenas
	 * para o usu�rio clicar e ela desaparecer.
	 * 
	 * @param titulo
	 *            da caixa de di�logo
	 * @param msg
	 *            que ir� ser exibida ao usu�rio
	 */
	private void mensagem(String titulo, String msg) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				MainActivity.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();

	}

	/**
	 * M�todo que realiza todas as capturas dos bot�es do aplicativo. �
	 * necess�rio que os bot�es de cada tela fiquem dentro de um try catch, caso
	 * contr�rio n�o � executado este m�todo. Bot�es de uma tela podem ficar
	 * dentro de apenas um trycatch. Bot�es de telas diferentes devem ficar em
	 * trycatch's diferentes.
	 */
	private void listeners() {
		try {
			btConfigurar.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					chamaTelaConfigurar();

				}
			});
		} catch (Exception e) {

		}
		try {
			btConfigVoltar.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					chamaTelaInicial();

				}
			});
			btConfigSalvar.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mensagem("Configura��es Salvas",
							"Suas configura��es foram Salvas com sucesso!");
					chamaTelaInicial();

				}
			});
		} catch (Exception e) {

		}

	}
}
