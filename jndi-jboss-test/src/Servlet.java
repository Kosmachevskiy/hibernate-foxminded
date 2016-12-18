import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by toss on 17.12.16.
 */
@WebServlet(urlPatterns = "/servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        InitialContext context = null;
            context = new InitialContext();
            Context envCtx = null;
            envCtx = (Context) context.lookup("java:/datasource/");
            DataSource dataSource = (DataSource) envCtx.lookup("PostgresDS");
            System.out.println(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
