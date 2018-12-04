package com.developer.mohamedraslan.hossamexams.JsonModel;

public class Block_Model {
    private String email;
    private String are_blocked;

    public Block_Model(String email, String are_blocked) {
        this.email = email;
        this.are_blocked = are_blocked;
    }

    public String getEmail() {
        return email;
    }

    public String getAre_blocked() {
        return are_blocked;
    }

    public Block_Model(){

    }
}
