package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;


public class ContatoDAO {

//	CRUD - Create Read Update Delete
	
	
//================ INSERINDO DADOS NO BANCO DE DADOS ================
	
	public void save(Contato contato) {
//		VALUES - Parse dos dados que vão chegar substituindo os valors ? pelos dados
		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";
		
//		Vars que vão ser usadas mais abaixo
		Connection conn = null;
		PreparedStatement pstm = null;
		
//		Tentar conectar com o banco ( Pode ser que esteja inativo, co o try ele tenta )
		try {
//			Criar conexão com o BD
			conn = ConnectionFactory.createConnectionToMySQL();
			
//			Criamos uma PrepareStatement para executar uma query 
			pstm = conn.prepareStatement(sql);
//			Add os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
//			Executar a Query
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso!/n");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			//Fechar as conexões
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	
//================ UPDATE DOS DADOS NO BANCO DE DADOS ================
	
	
	public void update(Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ?" +
	"WHERE id = ?";
		
//		Mesma receita
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
//			Criar conexão com o Banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
//			Criar a classe para executar a Query
			pstm = conn.prepareStatement(sql);
			
//			Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
//			Qual o ID do registro que deseja atualizar?
			pstm.setInt(4, contato.getId());
			
//			Executar a Query
			pstm.execute();
			

		}catch (Exception e ){
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}
	

	
//================ DELETAR OS DADOS NO BANCO DE DADOS ================
	
	
	public void deleteByID(int id) {
		
		String sql = "DELETE FROM contatos  WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	
	

//================ MOSTRAR O QUE ESTA NO BANCO DE DADOS ================
	
	public List<Contato> getContatos() {
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		

		
//		Classe que recupera dados do Banco de Dados SELECT
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				
//				Recuperar id 
				contato.setId(rset.getInt("id"));
//				Recuperar Nome 
				contato.setNome(rset.getString("nome"));
//				Recuperar Idade 
				contato.setIdade(rset.getInt("idade"));
//				Recuperar Data de Cadastro
				contato.setDataCadastro(rset.getDate("datacadastro"));
				
				contatos.add(contato);
			}
		}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset != null) {
						rset.close();
					}
					if(pstm != null) {
						pstm.close();
					}
					
					if (conn != null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			return contatos;
			}
}
