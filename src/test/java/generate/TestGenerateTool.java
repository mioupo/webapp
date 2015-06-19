package generate;

import java.sql.Connection;

import junit.framework.TestCase;

import org.junit.Test;

import com.web.generate.DBFactoryForConnection;
import com.web.generate.EntityGenerateTool;
import com.web.generate.InitConnectionI;

public class TestGenerateTool extends TestCase{
	@Test
	public void testGenerateEntity(){
		EntityGenerateTool tool = new EntityGenerateTool();
		InitConnectionI connectionI = DBFactoryForConnection.getConnectionI();
		Connection connection = connectionI.getConnection();
		tool.generatEntity(connection);
	}
}
