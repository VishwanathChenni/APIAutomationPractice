package pojoclasses;


public class CreateUser {
    private String name;
    private String job;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public CreateUser(String sName, String sEmail) {
        setName(sName);
        setJob(sEmail);

    }
}

