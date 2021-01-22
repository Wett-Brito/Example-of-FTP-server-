package br.com.alelo.core.sftp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import br.com.alelo.core.utils.Cryptography;

/*
 * Para essa classe funcionar precisa da dependencia "com.jcraft.jsch"
 * 
 */

public class sftpConnection {

	String host = "ftp.example.com";
	int port = 22;
	Session session = null;

	
	/*
	 * Metodo que gera a conexão
	 */
	public void connection() throws JSchException {
		JSch jsch = new JSch();
		session = jsch.getSession("usretl", "192.168.130.11", 22);
		session.setConfig("StrictHostKeyChecking", "no");
		session.setPassword(Cryptography.decrypt("QWxlbG9EZXY="));
		session.connect();
	}


	/*
	 * Metodo que realiza download dos campos
	 */
	public void download(String caminhoOrigem, String caminhoDestino) throws JSchException, SftpException {
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		sftpChannel.get(caminhoOrigem, caminhoDestino);
		sftpChannel.exit();
	}

	public void disconnect() {
		if (session != null) {
			session.disconnect();
		}
	}

	/*
	 * 
	 * Metodos de exemplo
	 * 
	 */
	public String DownloadArquivoMaisAtualExtrato_EDI() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" ff_EDI_VENDAS_COM_CV")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}

	
	public String DownloadArquivoMaisAtualAgendaFinancCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" AGENDA_FINANC_2") && !unico.toString().contains("ALELO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualAgendaFinancAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" AGENDA_FINANC_ALELO_2")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}

	public String DownloadArquivoMaisAtualAgendaFinancSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" AGENDA_FINANC_E")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualBIRACMOVTORCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" BIRAC_MOVTOR_2") && !unico.toString().contains("ALELO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualBIRACMOVTORAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" BIRAC_MOVTOR_ALELO_2")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}

	public String DownloadArquivoMaisAtualBiracMovtorSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" BIRAC_MOVTOR_2")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualEvertec() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" AGENDA_FINANC_EVETEC_")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualAntecvvCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" ANTECVV_") && !unico.toString().contains("ALELO")
					&& !unico.toString().contains("REJEITADO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualAntecvvAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ANTECVV_") && !unico.toString().contains("REJEITADO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}

	public String DownloadArquivoMaisAtualAntecvvSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ANTECVV_") && !unico.toString().contains("REJEITADO")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualECVVCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" ECVV_") && !unico.toString().contains("ALELO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualECVVAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ECVV_ALELO_2") && !unico.toString().contains("_bkp")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}

	public String DownloadArquivoMaisAtualECVVSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ECVV_2") && !unico.toString().contains("TESTE")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");
			System.out.println(arquivo);

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			
			
			if(arquivos.size() > 1) {
				SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());
	
				Date data_do_arquivo = df.parse(data);
	
				if (data_do_arquivo.after(maior_data)) {
					maior_data = data_do_arquivo;
					arquivo_final = arquivo;
				}
			} else {
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualANTECDISPCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" ANTECDISP_") && !unico.toString().contains("ALELO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualANTECDISPAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ANTECDISP_ALELO_") && !unico.toString().contains("_bkp")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}

	public String DownloadArquivoMaisAtualANTECDISPSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ANTECDISP_")) {
				arquivos.add(unico.toString());
			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualECVVUPCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" ECVVUP_") && !unico.toString().contains("ALELO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}

	public String DownloadArquivoMaisAtualECVVUPAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ECVVUP_ALELO_2")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {
			
			List<String> lista_tempo2 = new ArrayList<String>();
			lista_tempo2.addAll(Arrays.asList(arquivo.substring(43, 55).split(" ")));
			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			
			if(lista_tempo2.get(0).equals("")) {
				lista_tempo = arquivo.substring(44, 56).split(" ");
			}

			
			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());
			
			try {
				Date data_do_arquivo = df.parse(data);
				
				if (data_do_arquivo.after(maior_data)) {
					maior_data = data_do_arquivo;
					arquivo_final = arquivo;
				}
				
			}catch (ParseException e) {
			
				
				
			}
			
		}

		return arquivo_final.substring(56).trim();

	}

	public String DownloadArquivoMaisAtualECVVUPSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ECVVUP_")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualBIRAC_ANTECCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" BIRAC_ANTEC_2")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualBIRAC_ANTECAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" BIRAC_ANTEC_ALELO_2")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualBIRAC_ANTECSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" BIRAC_ANTEC_2")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualECSEC() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ECSEC")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}
	
	public String DownloadArquivoMaisAtualECFULL() throws JSchException, ParseException, SftpException {

		
		
		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" ARQ_EC_FULL")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}
	
	public String DownloadArquivoMaisAtualMSGVVCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" MSGVV_") && !unico.toString().contains("ALELO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualMSGVVAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" MSGVV_ALELO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}
	
	public String DownloadArquivoMaisAtualMSGVVSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" MSGVV_")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualAUTVVCielo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());
		sftpChannel.exit();

		for (Object unico : teste) {
			if (unico.toString().contains(" AUTVV_") && !unico.toString().contains("ALELO")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualAUTVVAlelo() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" AUTVV_ALELO_2")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}
	
	public String DownloadArquivoMaisAtualAUTVVSaida() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_SAIDA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" AUTVV_")) {
				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}
		return arquivo_final.substring(56);
	}
	
	public String DownloadArquivoMaisAtualMovimientos() throws JSchException, ParseException, SftpException {

		ArrayList<String> arquivos = new ArrayList<>();
		Channel channel = session.openChannel(Sftp.SFTP.getDescricao());
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		List<?> teste = sftpChannel.ls(Sftp.CAMINHO_ENTRADA.getDescricao());

		for (Object unico : teste) {
			if (unico.toString().contains(" MOVIMIENTOS_2")) {

				arquivos.add(unico.toString());

			}
		}
		SimpleDateFormat df1 = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

		Date maior_data = df1.parse("01010000");
		String arquivo_final = null;

		for (String arquivo : arquivos) {

			String[] lista_tempo = arquivo.substring(43, 55).split(" ");

			switch (lista_tempo[0]) {
			case "Jan":
				lista_tempo[0] = "01";
				break;
			case "Feb":
				lista_tempo[0] = "02";
				break;
			case "Mar":
				lista_tempo[0] = "03";
				break;
			case "Apr":
				lista_tempo[0] = "04";
				break;
			case "May":
				lista_tempo[0] = "05";
				break;
			case "Jun":
				lista_tempo[0] = "06";
				break;
			case "Jul":
				lista_tempo[0] = "07";
				break;
			case "Aug":
				lista_tempo[0] = "08";
				break;
			case "Sep":
				lista_tempo[0] = "09";
				break;
			case "Oct":
				lista_tempo[0] = "10";
				break;
			case "Nov":
				lista_tempo[0] = "11";
				break;
			case "Dec":
				lista_tempo[0] = "12";
				break;
			default:
				break;
			}

			String data;

			if (lista_tempo[1].trim().length() > 0) {
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2].replace(":", "");

			} else {
				lista_tempo[1] = "0" + lista_tempo[1];
				data = lista_tempo[0] + lista_tempo[1] + lista_tempo[2] + lista_tempo[3].replace(":", "");

			}

			SimpleDateFormat df = new SimpleDateFormat(Sftp.DATE_DEFAULT.getDescricao());

			Date data_do_arquivo = df.parse(data);

			if (data_do_arquivo.after(maior_data)) {
				maior_data = data_do_arquivo;
				arquivo_final = arquivo;
			}
		}

		return arquivo_final.substring(56);

	}
	
}
