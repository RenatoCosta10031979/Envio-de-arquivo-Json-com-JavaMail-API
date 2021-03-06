package enviando.email;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjetoEnviaEmail {

	// Atributos
	private String idade = "";
	private String cpf = "";
	private String telefone = "";
	private String profissao = "";
	private String emailRemetente = "";
	private String nomeRemetente = "";
	private String emailDestinatario = "";
	private String tituloEmail = "";

	// Constructor
	ObjetoEnviaEmail() {

	}

	// Método
	public void testeEmail(List<ObjetoEnviaEmail> emails) throws MessagingException, IOException {

		// Organizar o código no arquivo Json
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonWrite = new File(
				"/home/cafecomjava/workspace/javaMail/enviando-email/src/main/java/enviando/email/arquivo.json");
		String jsonUser = gson.toJson(emails);

		System.out.println(jsonUser);

		FileWriter gravarArquivoJson = new FileWriter(jsonWrite);

		gravarArquivoJson.write(jsonUser);
		gravarArquivoJson.flush();
		gravarArquivoJson.close();

		// Configurações das propriedades:autorização, autenticação, servidor gmail.
		// porta do servidor, socket

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true"); // autorização
		properties.put("mail.smtp.starttls", "true"); // autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com"); // servidor gmail do google
		properties.put("mail.smtp.port", "465"); // porta do servidor
		properties.put("mail.smtp..socketFactory.port", "465"); // especifica a porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");// Classe socket de
																							// conexão ao SMTP

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("<inserir o e-mail do titular>", "<inserir aqui a senha  do e-mail>");
			}
		});

		// Anexar arquivo.txt
		Address[] toUser = InternetAddress.parse(emailDestinatario);
		FileDataSource anexarArquivoJson = new FileDataSource(jsonWrite);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("rgcfisica@gmail.com", nomeRemetente)); // autor do e-mail ( e-mail de
																					// origem)
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(tituloEmail);
		message.setDataHandler(new DataHandler(anexarArquivoJson));
		message.setFileName(anexarArquivoJson.getName());

		Transport.send(message);
	}


	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEmailRemetente() {
		return emailRemetente;
	}

	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}

	public String getNomeRemetente() {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getTituloEmail() {
		return tituloEmail;
	}

	public void setTituloEmail(String tituloEmail) {
		this.tituloEmail = tituloEmail;
	}

}
