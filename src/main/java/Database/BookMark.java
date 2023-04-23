package Database;

public class BookMark {
    int id;
    String name;

    public BookMark(int id, String name, int sequence, String registerTime, String alterTime) {
        this.id = id;
        this.name = name;
        this.sequence = sequence;
        this.registerTime = registerTime;
        if(alterTime != null){
            this.alterTime = alterTime;
        }
    }

    int sequence;
    String registerTime;
    String alterTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getAlterTime() {
        return alterTime;
    }

    public void setAlterTime(String alterTime) {
        this.alterTime = alterTime;
    }
}
