package htw.berlin.webtech.persistence;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "levies")
public class LevyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "discription")
    private String discription;

    @Column(name = "modul")
    private String modul;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "importance", nullable = false)
    private String importance;

    public LevyEntity(long id, String title, String discription, String modul, LocalDate deadline, String importance) {
        this.id = id;
        this.title = title;
        this.discription = discription;
        this.modul = modul;
        this.deadline = deadline;
        this.importance = importance;
    }

    protected LevyEntity() {}

    public long getId() {
        return id;
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
