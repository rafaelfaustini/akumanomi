package br.com.rafaelfaustini.akumanomi.model;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerModel {
    private UUID uuid;
    private String nickname;
    private AkumaNoMiModel fruit;

    public PlayerModel(String _uuid, String _nickname, AkumaNoMiModel _fruit){
        uuid = UUID.fromString(_uuid);
        nickname = _nickname;
        fruit = _fruit;
    }
    public PlayerModel(String _uuid, String _nickname){
        uuid = UUID.fromString(_uuid);
        nickname = _nickname;
        fruit = null;
    }

    public boolean isEsper(){
        if(fruit == null) return false;
        return true;
    }

    public UUID getUUID(){
        return this.uuid;
    }
    public String getNickname(){
        return this.nickname;
    }

    public AkumaNoMiModel getFruit() {
        return fruit;
    }

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerModel other = (PlayerModel) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!nickname.equals(other.nickname))
            return false;
        return true;
    }

    public boolean equals(Player p){
        if(p.getUniqueId().equals(this.uuid)){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return String.format(this.nickname);
    }


}
