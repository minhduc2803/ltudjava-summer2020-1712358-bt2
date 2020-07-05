package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 5/7/2020 - 12:21 PM
 * @Description
 */
public class GiaoVu implements java.io.Serializable{
    private String Username;
    private String password;
    public GiaoVu(){}
    public GiaoVu(String Username, String password){
        this.Username = Username;
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
