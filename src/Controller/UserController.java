package Controller;

import java.util.ArrayList;

import model.UserDTO;

public class UserController {
    private ArrayList<UserDTO> list;
    private int nextId;
    
    private final int RANK_ADMIN = 1;
    private final int RANK_CRITIC = 2;
    private final int RANK_GENERAL = 3;
    
    //생성자
    public UserController() {
        list = new ArrayList<>();
        nextId = 1;
        
    }
    
    //입력메소드
    public void insert(UserDTO u) {
        u.setId(nextId);
        u.setRank(RANK_GENERAL); // 일반가입자는 일반등급유저
        
        list.add(u);
    }
    
    //

}
