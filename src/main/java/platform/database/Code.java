package platform.database;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class Code {

    @Id
    private String id;

    private String content;

    private Long time;

    private Integer views;

    @CreationTimestamp
    private LocalDateTime date;

    public Code() {
    }

    public Code(String content) {
        this.content = content;
    }

    public Code(String content, LocalDateTime date) {
        this.content = content;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", views=" + views +
                ", date=" + date +
                '}';
    }
}
