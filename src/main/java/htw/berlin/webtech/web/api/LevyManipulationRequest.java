package htw.berlin.webtech.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class LevyManipulationRequest {

    @NotBlank(message = "The title must not be empty.")
    private String title;

    @NotBlank(message = "The discription must not be empty.")
    private String discription;

    @NotBlank(message = "The modul must not be empty.")
    private String modul;

    @NotBlank(message = "The modul must not be empty.")
    private LocalDate deadline;

    @NotBlank(message = "The importance must not be empty.")
    private String importance;

    public LevyManipulationRequest(String title, String discription, String modul, LocalDate deadline, String importance) {
        this.title = title;
        this.discription = discription;
        this.modul = modul;
        this.deadline = deadline;
        this.importance = importance;
    }

    public LevyManipulationRequest() {}

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
