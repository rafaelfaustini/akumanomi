package akumanomi.api.Action;

import java.sql.Connection;

import com.google.inject.Inject;

import akumanomi.api.Action.Abstract.ActionAbstract;
import akumanomi.api.Internal.AutoRollback;
import akumanomi.api.Internal.ConnectionFactory;
import akumanomi.api.Repository.FruitRepository;

public class CheckFruitAction extends ActionAbstract{
    private FruitRepository fruitRepository;
    private ConnectionFactory connectionFactory;

    @Inject
    public CheckFruitAction(FruitRepository fruitRepository, ConnectionFactory connectionFactory) {
        this.fruitRepository = fruitRepository;
        this.connectionFactory = connectionFactory;
    }


    public int CheckFruit(String fruitKey) {
        int fruitTypeID = 0;
        try (Connection connection = connectionFactory.getConnection();
             AutoRollback tm = new AutoRollback(connection)) {
                fruitTypeID = this.fruitRepository.getFruitTypeByKey(connection, fruitKey);
                tm.commit();
        } catch(Exception e) {
            this.error(e.toString());
        }
        return fruitTypeID;
}
}
