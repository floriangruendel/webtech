package htw.berlin.webtech.web.api;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Levy {

    private long id;
    private String title;
    private String discription;
    private String modul;
    private LocalDate deadline;
    private String importance;

    public Levy(long id, String title, String discription, String modul, LocalDate deadline, String importance) {
        this.id = id;
        this.title = title;
        this.discription = discription;
        this.modul = modul;
        this.deadline = deadline;
        this.importance = importance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
}
