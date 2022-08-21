package akumanomi.api.Repository.VO;

import java.sql.Date;

public class EsperVO {
    private int id;
    private String player;
    private int fruitId;
    private Date endDate;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }
    public int getFruitId() {
        return fruitId;
    }
    public void setFruitId(int fruitId) {
        this.fruitId = fruitId;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
