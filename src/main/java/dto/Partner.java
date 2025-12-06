package dto;

public class Partner {
    private int id;
    private String name;
    private String nativeLang;
    private String learnLang;
    private String intro;
    private String imageUrl;

    // user_id 필드
    private Integer userId;

    // 이메일 필드 추가
    private String email;

    // --- 기본 생성자 ---
    public Partner() {}

    // --- Getter / Setter ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNativeLang() { return nativeLang; }
    public void setNativeLang(String nativeLang) { this.nativeLang = nativeLang; }

    public String getLearnLang() { return learnLang; }
    public void setLearnLang(String learnLang) { this.learnLang = learnLang; }

    public String getIntro() { return intro; }
    public void setIntro(String intro) { this.intro = intro; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
