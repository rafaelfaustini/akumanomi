package br.com.rafaelfaustini.akumanomi.model;

public class BountyModel {
    private PlayerModel player;
    private float money;

    public BountyModel(PlayerModel _player, Float _money){
        player = _player;
        money = _money;
    }

    public PlayerModel getPlayer(){
        return this.player;
    }
    public float getMoney(){
        return this.money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BountyModel other = (BountyModel) obj;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (! (money == other.money))
            return false;
        return true;
    }

    @Override
    public String toString(){
        return String.format("☠ %s - β%.2f", this.player, this.money);
    }

}
