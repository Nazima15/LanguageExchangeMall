package dto;

public class Wishlist {

    // 기존 필드
    private int wishlistId;   // JSP에서 w.wishlistId 사용하므로 수정
    private int userId;
    private int partnerId;
    private String createdAt;

    // JOIN된 partner 정보 (JSP에서 사용)
    private String imageUrl;
    private String name;
    private String nativeLang;
    private String learnLang;
    private String intro;

    // -------- Getter & Setter --------

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeLang() {
        return nativeLang;
    }

    public void setNativeLang(String nativeLang) {
        this.nativeLang = nativeLang;
    }

    public String getLearnLang() {
        return learnLang;
    }

    public void setLearnLang(String learnLang) {
        this.learnLang = learnLang;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
