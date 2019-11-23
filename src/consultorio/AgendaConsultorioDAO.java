
package consultorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgendaConsultorioDAO {

    static void adiciona(AgendaConsultorioDAO agenDAO) {
    }
    private Connection conexao;
    
    public AgendaConsultorioDAO() throws SQLException{
	this.conexao = Conexao.getConexaoMySQL();  
}
public void adiciona(AgendaConsultorio agenda) throws SQLException{
	String sql = "insert into agendamento (paciente, medico, data) values (?,?,?)";
	PreparedStatement stmt = conexao.prepareStatement(sql);
	
	stmt.setString(1, agenda.getPaciente());
        stmt.setString(2, agenda.getMedico());
        stmt.setString(3, agenda.getData());

	stmt.execute();
	stmt.close();
}
    
    public ArrayList<AgendaConsultorio> getLista() throws SQLException{
	String sql = "select * from agendamento";
	PreparedStatement stmt = this.conexao.prepareStatement(sql);
	ResultSet rs = stmt.executeQuery();

	ArrayList<AgendaConsultorio> lista = new ArrayList<AgendaConsultorio>();
	
	while(rs.next()){
		AgendaConsultorio agenda = new AgendaConsultorio();
		agenda.setPaciente(rs.getString("paciente"));
		agenda.setMedico(rs.getString("medico"));
                agenda.setData(rs.getString("data"));

		lista.add(agenda);
	}
	rs.close();
	stmt.close();
	
	return lista;
}
}
