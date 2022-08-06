package platform.domain;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeResponse {

    private String code;

    private Long time;

    private Integer views;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");

    //@JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private String date;

    public CodeResponse() {

    }
    public CodeResponse(String content, LocalDateTime date) {
        this.code=content;
        this.date=date.format(formatter);
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date.format(formatter);
    }
}
