package ru.romster.fs.message;

/**
 * Created by r0m5t3r on 11/30/15.
 */
public class StartPageResponseMessage {
    private Integer usersCount;
    private String redirectUr;

    public StartPageResponseMessage() {
    }

    public StartPageResponseMessage(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public StartPageResponseMessage(String redirectUrl) {
        this.redirectUr = redirectUrl;
    }

    public Integer getUserCount() {
        return usersCount;
    }

    public void setUserCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public String getRedirectUrl() {
        return redirectUr;
    }

    public void setRedirectUrl(String redirectUr) {
        this.redirectUr = redirectUr;
    }

    @Override
    public String toString() {
        return "StartPageResponceMEssage{" +
                "usersCount=" + usersCount +
                ", redirectUr='" + redirectUr + '\'' +
                '}';
    }
}
