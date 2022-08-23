package akumanomi.api.Action.Esper;

import java.sql.Connection;

import com.google.inject.Inject;

import akumanomi.api.Action.Abstract.ActionAbstract;
import akumanomi.api.Internal.ConnectionFactory;
import akumanomi.api.Repository.EsperRepository;

public class RemoveEsperAction extends ActionAbstract {
    private EsperRepository esperRepository;
    private ConnectionFactory connectionFactory;


    @Inject
    public RemoveEsperAction(EsperRepository esperRepository, ConnectionFactory connectionFactory) {
        this.esperRepository = esperRepository;
        this.connectionFactory = connectionFactory;
    }

    public void RemoveEsper(String playerID) {
        
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                this.esperRepository.removeEsper(connection, playerID);
            } catch (Exception e) {
                connection.rollback();
                this.error(e.toString());
            } finally {
                connection.commit();
            }
        } catch(Exception e) {
            
        }
    }
}
