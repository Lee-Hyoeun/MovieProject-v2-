package Controller;

import java.util.ArrayList;

import model.UserDTO;

public class UserController {
    private ArrayList<UserDTO> list;
    private int nextId;

    private final int RANK_ADMIN = 1;
    private final int RANK_CRITIC = 2;
    private final int RANK_GENERAL = 3;

    // 생성자
    public UserController() {
        list = new ArrayList<>();
        nextId = 1;

    }

    // 입력메소드
    public void insert(UserDTO u) {
        u.setId(nextId);
        u.setRank(RANK_GENERAL); // 일반가입자는 일반등급유저

        list.add(u);
    }

    // 개별 회원을 불러오는 메소드
    public UserDTO selectOne(int id) {
        for (UserDTO u : list) {
            if (u.getId() == id) {
                return new UserDTO(u);
            }
        }

        return null;
    }

    // 사용자로부터 입력받은 username이 중복이거나 'x'와 일치하면
    // true,
    // 그외는 false가 리턴되는 메소드
    public boolean validate(String username) {
        if (username.equalsIgnoreCase("X")) {
            return true;
        }

        for (UserDTO u : list) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }

    // 사용자로부터 입력받은 username과 password가 일치하는 회원을
    // 리턴하는 메소드

    // 회원정보 수정 메소드

    // 회원 정보 삭제 메소드

}
