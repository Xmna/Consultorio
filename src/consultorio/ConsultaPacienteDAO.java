/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaPacienteDAO {
    private Connection conexao;
    
    public ConsultaPacienteDAO() throws SQLException{
	this.conexao = Conexao.getConexaoMySQL();
    
}
    public void adiciona(ConsultaPaciente consulta) throws SQLException{
	String sql = "insert into prontuario (paciente, medico, observa, prontuario) values (?,?,?,?)";
	PreparedStatement stmt = conexao.prepareStatement(sql);
	
	stmt.setString(1, consulta.getPaciente());
        stmt.setString(2, consulta.getMedico());
        stmt.setString(3, consulta.getObserva());
        stmt.setString(4, consulta.getProntuario());

	stmt.execute();
	stmt.close();
}
    public ArrayList<ConsultaPaciente> getLista(String paciente) throws SQLException{
	String sql = "select * from prontuario where paciente like ?";
	PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setString(1,paciente);
	ResultSet rs = stmt.executeQuery();

	ArrayList<ConsultaPaciente> lista = new ArrayList<ConsultaPaciente>();
	
	while(rs.next()){
		ConsultaPaciente consulta = new ConsultaPaciente();
		consulta.setPaciente(rs.getString("paciente"));
		consulta.setMedico(rs.getString("medico"));
                consulta.setObserva(rs.getString("observa"));
                consulta.setProntuario(rs.getString("prontuario"));

                
		lista.add(consulta);
                
	}
	rs.close();
	stmt.close();
	
	return lista;
}
}
