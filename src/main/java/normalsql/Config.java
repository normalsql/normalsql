package normalsql;

public class Config
{
    public String description;
    public String driver;
    public String url;
    public String username;
    public String password;
    public String source;
    public String target;
    public String pkg;
    public String extension = "sql";

    @Override
    public String toString()
    {
        String sb =
            "Config {" +
                ", \ndescription='" + description + '\'' +
                ", \ndriver='" + driver + '\'' +
                ", \nurl='" + url + '\'' +
                ", \nusername='" + username + '\'' +
                ", \npassword='" + password + '\'' +
                ", \nsource='" + source + '\'' +
                ", \ntarget='" + target + '\'' +
                ", \npackage='" + pkg + '\'' +
                ", \nextension='" + extension + '\'' +
                "\n}";
        return sb;
    }
}
