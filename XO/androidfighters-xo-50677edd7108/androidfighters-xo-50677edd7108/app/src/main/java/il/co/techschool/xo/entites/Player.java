package il.co.techschool.xo.entites;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date_of_birth")
    @Expose
    private Long dateOfBirth;
    @SerializedName("number_of_games_win")
    @Expose
    private Integer numberOfGamesWin;
    @SerializedName("number_of_games_loss")
    @Expose
    private Integer numberOfGamesLoss;
    @SerializedName("number_of_games_draw")
    @Expose
    private Integer numberOfGamesDraw;
    @SerializedName("level")
    @Expose
    private Double level;
    @SerializedName("status")
    @Expose
    private Integer status;


    public Player() {

    }

    public Player(String aId, String aName, Long aDateOfBirth, Integer aDbId, Integer aNumberOfGamesWin, Integer aNumberOfGamesLoss, Integer aNumberOfGamesDraw, Double aLevel, Integer aStatus) {
        id = aId;
        name = aName;
        dateOfBirth = aDateOfBirth;
        numberOfGamesWin = aNumberOfGamesWin;
        numberOfGamesLoss = aNumberOfGamesLoss;
        numberOfGamesDraw = aNumberOfGamesDraw;
        level = aLevel;
        status = aStatus;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public Integer getNumberOfGamesWin() {
        return numberOfGamesWin;
    }

    public void setNumberOfGamesWin(Integer numberOfGamesWin) {
        this.numberOfGamesWin = numberOfGamesWin;
    }

    public Integer getNumberOfGamesLoss() {
        return numberOfGamesLoss;
    }

    public void setNumberOfGamesLoss(Integer numberOfGamesLoss) {
        this.numberOfGamesLoss = numberOfGamesLoss;
    }

    public Integer getNumberOfGamesDraw() {
        return numberOfGamesDraw;
    }

    public void setNumberOfGamesDraw(Integer numberOfGamesDraw) {
        this.numberOfGamesDraw = numberOfGamesDraw;
    }

    public Integer getNumberOfGamesCount() {
        Integer result;
        if ((numberOfGamesDraw == null) || (numberOfGamesWin == null) || (numberOfGamesLoss == null)){
            result = 0;
        } else {
            result = numberOfGamesDraw + numberOfGamesLoss + numberOfGamesWin;
        }
        return result;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public void updateFrom(Player aPlayer) {
        name = aPlayer.name;
        dateOfBirth = aPlayer.dateOfBirth;
        numberOfGamesWin = aPlayer.numberOfGamesWin;
        numberOfGamesLoss = aPlayer.numberOfGamesLoss;
        numberOfGamesDraw = aPlayer.numberOfGamesDraw;
        level = aPlayer.level;
        status = aPlayer.status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer aStatus) {
        status = aStatus;
    }
}
