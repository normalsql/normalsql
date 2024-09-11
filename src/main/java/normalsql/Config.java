package normalsql;

import java.nio.file.Path;

public class Config
{
    public String description;
    public String url;
    public String username;
    public String password;
    public Path source;
    public Path target;
    public String pkg;
    public String extension = "sql";

    @Override
    public String toString()
    {
        String sb =
            "Config {" +
                ", \ndescription='" + description + '\'' +
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
