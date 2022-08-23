package akumanomi.api.Action.Esper;

import java.sql.Connection;

import com.google.inject.Inject;

import akumanomi.api.Action.Abstract.ActionAbstract;
import akumanomi.api.Internal.ConnectionFactory;
import akumanomi.api.Repository.EsperRepository;
import akumanomi.api.Repository.FruitRepository;
import akumanomi.api.Repository.VO.EsperVO;

public class CreateEsperAction extends ActionAbstract {
    private FruitRepository fruitRepository;
    private EsperRepository esperRepository;
    private ConnectionFactory connectionFactory;


    @Inject
    public CreateEsperAction(FruitRepository fruitRepository, EsperRepository esperRepository,
            ConnectionFactory connectionFactory) {
        this.fruitRepository = fruitRepository;
        this.esperRepository = esperRepository;
        this.connectionFactory = connectionFactory;
    }

    public void CreateEsper(String playerID, String fruitKey) {
        
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                int fruitID = this.fruitRepository.getFruitIdByKey(connection, fruitKey);
                EsperVO esper = new EsperVO();
                esper.setFruitId(fruitID);
                esper.setPlayer(playerID);
                this.fruitRepository.deactivateFruitByKey(connection, fruitKey);
                this.esperRepository.createEsper(connection, esper);
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
