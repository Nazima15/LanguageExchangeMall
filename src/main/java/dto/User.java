package dto;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String email;
    private String nickname;

    // 언어 이름
    private String nativeLang; // ex: "한국어"
    private String learnLang;  // ex: "영어"

    // DB 참조 ID (foreign key)
    private Integer nativeLangId;
    private Integer learnLangId;

    private String level;
    private String profileImg; // 선택 사항
    private String role;       // USER / ADMIN

    // Getter & Setter
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getNativeLang() { return nativeLang; }
    public void setNativeLang(String nativeLang) { this.nativeLang = nativeLang; }

    public String getLearnLang() { return learnLang; }
    public void setLearnLang(String learnLang) { this.learnLang = learnLang; }

    public Integer getNativeLangId() { return nativeLangId; }
    public void setNativeLangId(Integer nativeLangId) { this.nativeLangId = nativeLangId; }

    public Integer getLearnLangId() { return learnLangId; }
    public void setLearnLangId(Integer learnLangId) { this.learnLangId = learnLangId; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getProfileImg() { return profileImg; }
    public void setProfileImg(String profileImg) { this.profileImg = profileImg; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

