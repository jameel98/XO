package il.co.techschool.xo.entites;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import il.co.techschool.xo.common.GameStatuses;
import il.co.techschool.xo.utils.UtilRandomGenerator;

public class Game {
    private static final String FIRST_START = "01234567";
//    private static final String SECOND_START = "89abcdef";



    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("player1")
    @Expose
    private String player1;
    @SerializedName("player2")
    @Expose
    private String player2;
    @SerializedName("turn")
    @Expose
    private String turn;
    @SerializedName("turn_time")
    @Expose
    private Integer turnTime;
    @SerializedName("a1")
    @Expose
    private String a1;
    @SerializedName("a2")
    @Expose
    private String a2;
    @SerializedName("a3")
    @Expose
    private String a3;
    @SerializedName("b1")
    @Expose
    private String b1;
    @SerializedName("b2")
    @Expose
    private String b2;
    @SerializedName("b3")
    @Expose
    private String b3;
    @SerializedName("c1")
    @Expose
    private String c1;
    @SerializedName("c2")
    @Expose
    private String c2;
    @SerializedName("c3")
    @Expose
    private String c3;
    @SerializedName("status")
    @Expose
    private GameStatuses status;


    public Game() {
    }

    public Game(String player1, String player2)
    {
        this.player1 = player1;
        this.player2 = player2;
        status = GameStatuses.GS_NONE;
        id = new UtilRandomGenerator().nextSessionId();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public Integer getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(Integer turnTime) {
        this.turnTime = turnTime;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public GameStatuses getStatus() {
        return status;
    }

    public void setStatus(GameStatuses status) {
        this.status = status;
    }

    public void updateFrom(Game aGame) {
        player1 = aGame.player1;
        player2 = aGame.player2;
        turn = aGame.turn;
        turnTime = aGame.turnTime;
        a1 = aGame.a1;
        a2 = aGame.a2;
        a3 = aGame.a3;
        b1 = aGame.b1;
        b2 = aGame.b2;
        b3 = aGame.b3;
        c1 = aGame.c1;
        c2 = aGame.c2;
        c3 = aGame.c3;
        status = aGame.status;
    }

    public void startGame() {
        String fistChar;
        fistChar = getPlayer1().substring(0, 1);

        if (status == GameStatuses.GS_ACCEPTED){
            status = GameStatuses.GS_INGAME;
            if (FIRST_START.contains( fistChar)){
                turn = player1;
            } else {
                turn = player2;
            }
        }
    }
}