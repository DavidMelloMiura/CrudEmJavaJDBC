package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		
//		Instancia a classe ContatoDAO
		ContatoDAO contatoDao = new ContatoDAO();
				
//		Instancia um novo contato 		
		Contato contato = new Contato();
		contato.setNome("Novo nome");
		contato.setIdade(39);
		contato.setDataCadastro(new Date());
		
		contatoDao.save(contato);
		//Pega o metodo save da Classe ContatoDAO e salva em contatos
		
		
//========== ATUALIZAR CONTATO ==========
		
		Contato c2 = new Contato();
		c2.setNome("Tarja Turunen");
		c2.setIdade(44);
		c2.setDataCadastro(new Date());
		c2.setId(25);// Coloca uma ID que tem no banco de dados
		
//		contatoDao.update(c2);
		
		
//========== DELETAR O CONTATO PELO NUMERO DE ID ==========
		
//		contatoDao.deleteByID(23);  
		
		
		
//========== VISUALIZAÇÂO DOS REGISTROS  NO BANCO DE DADOS ==========
		
		for (Contato c: contatoDao.getContatos()) {
			System.out.println("Nome "+ c.getNome());
			System.out.println("Idade "+ c.getIdade());
			System.out.println("Data de Cadastro " + c.getDataCadastro());
			System.out.println("==============================");
		}
		
	}
}