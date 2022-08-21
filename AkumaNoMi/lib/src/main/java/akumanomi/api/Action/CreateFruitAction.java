package akumanomi.api.Action;

import java.sql.Connection;
import java.util.UUID;

import com.google.inject.Inject;

import akumanomi.api.Action.Abstract.ActionAbstract;
import akumanomi.api.Internal.ConnectionFactory;
import akumanomi.api.Repository.FruitRepository;
import akumanomi.api.Repository.VO.FruitVO;

public class CreateFruitAction extends ActionAbstract {
    private FruitRepository fruitRepository;
    private ConnectionFactory connectionFactory;


    @Inject
    public CreateFruitAction(FruitRepository fruitRepository, ConnectionFactory connectionFactory) {
        this.fruitRepository = fruitRepository;
        this.connectionFactory = connectionFactory;
    }



    public String CreateFruit(int fruitTypeID) {
        String fruitKey = UUID.randomUUID().toString();
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                FruitVO fruit = new FruitVO();
                fruit.setKey(fruitKey);
                fruit.setTypeID(fruitTypeID);
                this.fruitRepository.deactivateFruitByType(connection, fruitTypeID);
                this.fruitRepository.createFruit(connection, fruit);

            } catch (Exception e) {
                connection.rollback();
                this.error(e.toString());
                return null;
            } finally {
                connection.commit();
            }
        } catch(Exception e) {
            
        }
        return fruitKey;
}
}
