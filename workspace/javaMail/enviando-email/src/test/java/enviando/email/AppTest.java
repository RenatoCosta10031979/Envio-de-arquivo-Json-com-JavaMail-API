package enviando.email;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AppTest {

	private static final int List = 0;

	public static void main(String[] args) throws MessagingException, InterruptedException, IOException {

		ObjetoEnviaEmail email = new ObjetoEnviaEmail();

		String nome = "";
		String idade = "";
		String cpf = "";
		String telefone = "";
		String profissao = "";
		String emailRemetente = "";
		String nomeRemetente = "";
		String emailDestinatario = "";
		String tituloEmail = "";

		String nomeRemetente1 = JOptionPane.showInputDialog("Digite o seu nome: ");
		String quantosAnos = JOptionPane.showInputDialog("Digite a sua idade: ");
		String registroPessoaFisica = JOptionPane.showInputDialog("Digite o  CPF: ");
		String telefoneContato = JOptionPane.showInputDialog("Digite o número de telefone: ");
		String emailDeOrigem = JOptionPane.showInputDialog("Digite o seu e-mail: ");
		String emailDoDestinatario = JOptionPane.showInputDialog("Digite o e-mail destinatário: ");
		String assuntoEMail = JOptionPane.showInputDialog("Digite o título do e-mail: ");
		String trabalho = JOptionPane.showInputDialog("Digite a profissão: ");

		email.setNomeRemetente(nomeRemetente1);
		email.setIdade(quantosAnos);
		email.setProfissao(trabalho);
		email.setCpf(registroPessoaFisica);
		email.setTelefone(telefoneContato);
		email.setEmailRemetente(emailDeOrigem);
		email.setEmailDestinatario(emailDoDestinatario);
		email.setTituloEmail(assuntoEMail);

		List<ObjetoEnviaEmail> emails = new ArrayList<ObjetoEnviaEmail>();
		emails.add(email);
		email.testeEmail(emails);

		JOptionPane.showMessageDialog(null, "O arquivo foi enviado por e-mail com sucesso!!");

	}
}


