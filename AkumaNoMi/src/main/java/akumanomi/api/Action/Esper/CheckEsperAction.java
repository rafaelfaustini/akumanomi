package akumanomi.api.Action.Esper;

import java.sql.Connection;

import com.google.inject.Inject;

import akumanomi.api.Action.Abstract.ActionAbstract;
import akumanomi.api.Internal.ConnectionFactory;
import akumanomi.api.Repository.EsperRepository;

public class CheckEsperAction extends ActionAbstract {
    private EsperRepository esperRepository;
    private ConnectionFactory connectionFactory;

    @Inject
    public CheckEsperAction(EsperRepository esperRepository, ConnectionFactory connectionFactory) {
        this.esperRepository = esperRepository;
        this.connectionFactory = connectionFactory;
    }


    public boolean CheckEsper(String playerID) {
        boolean isEsper=false;
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                isEsper = this.esperRepository.checkEsper(connection, playerID);
            } catch (Exception e) {
                connection.rollback();
                this.error(e.toString());
            } finally {
                connection.commit();
            }
        } catch(Exception e) {
            
        }
        return isEsper;

}
}
