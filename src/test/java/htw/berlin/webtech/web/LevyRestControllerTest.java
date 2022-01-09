package htw.berlin.webtech.web;

import htw.berlin.webtech.service.LevyService;
import htw.berlin.webtech.web.api.Levy;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LevyRestController.class)
public class LevyRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LevyService levyService;

    @Test
    @DisplayName("should return found levies from levy service")
    void should_return_found_levy_from_levy_service() throws Exception {
        // given
        LocalDate deadline1 = LocalDate.of(2012, 12, 12);
        LocalDate deadline2 = LocalDate.of(2022, 1, 1);
        var levies = List.of(
                new Levy(1, "Aufgabe 1", "Das ist Aufgabe 1", "Webtech", deadline1, "normal"),
                new Levy(2, "Aufgabe 2", "Das ist Aufgabe 2", "Webtech", deadline2, "wichtig")
        );
        doReturn(levies).when(levyService).findAll();

        // when
        mockMvc.perform(get("/api/v1/levies"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Aufgabe 1"))
                .andExpect(jsonPath("$[0].discription").value("Das ist Aufgabe 1"))
                .andExpect(jsonPath("$[0].modul").value("Webtech"))
                .andExpect(jsonPath("$[0].importance").value("normal"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Aufgabe 2"))
                .andExpect(jsonPath("$[1].discription").value("Das ist Aufgabe 2"))
                .andExpect(jsonPath("$[1].modul").value("Webtech"))
                .andExpect(jsonPath("$[1].importance").value("wichtig"));
    }

    @Test
    @DisplayName("should return 404 if levy is not found")
    void should_return_404_if_levy_is_not_found() throws Exception {
        // given
        doReturn(null).when(levyService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/levies/1234"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a levy")
    void should_return_201_http_status_and_location_header_when_creating_a_levy() throws Exception {
        // given
        String levyToCreateAsJson = "{\"title\": \"Test\", \"discription\":\"Das ist ein Test\", \"modul\":\"Testmodul\", \"deadline\":\"2012-12-12\", \"importance\": \"normal\"}";
        var levy = new Levy(1234, null, null, null, null, null);
        doReturn(levy).when(levyService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/levies")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(levyToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/levies/" + levy.getId()))));

    }
}
