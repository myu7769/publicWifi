package Database;

public class BookMarkWikis {

    int id;
    String bookMarkName;
    String SWIFI_MGR_NO;
    String SWIFI_MAIN_NM;
    String registerTime;


    public int getId() {
        return id;
    }

    public String getBookMarkName() {
        return bookMarkName;
    }

    public String getSWIFI_MGR_NO() {
        return SWIFI_MGR_NO;
    }

    public String getSWIFI_MAIN_NM() {
        return SWIFI_MAIN_NM;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public BookMarkWikis(int id, String bookMarkName, String SWIFI_MGR_NO, String SWIFI_MAIN_NM, String registerTime) {
        this.id = id;
        this.bookMarkName = bookMarkName;
        this.SWIFI_MGR_NO = SWIFI_MGR_NO;
        this.SWIFI_MAIN_NM = SWIFI_MAIN_NM;
        this.registerTime = registerTime;
    }
}
