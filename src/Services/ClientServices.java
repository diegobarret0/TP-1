package Services;

import Data.SQLite;

import java.sql.SQLException;

public class ClientServices {

    public String clientValidation(String clientUsername) throws SQLException {
        SQLite db = new SQLite();
        String sql = String.format("SELECT * FROM Clientes WHERE clientUsername = '%s'", clientUsername);

        if(db.getRecords(sql).isEmpty())
            return "Cliente no existe";

        return null;
    }


}
