package tests;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import tests.model.Items;

import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonTest {

   private ClassLoader cl = JsonTest.class.getClassLoader();

    @Test
    void jsonTest() throws Exception {
       try (Reader reader = new InputStreamReader(
               cl.getResourceAsStream("test.json")
       )) {
           ObjectMapper mapper = new ObjectMapper();
           Items items = mapper.readValue(reader, Items.class);

           assertThat(items.getItems().get(0).getType()).isEqualTo("cafe");
           assertThat(items.getItems().get(0).getTitle()).isEqualTo("Ням-Ням");
           assertThat(items.getItems().get(0).getTimeWalk()).isEqualTo(4);
           assertThat(items.getItems().get(0).getId()).isEqualTo(1561219);

           assertThat(items.getItems().get(1).getType()).isEqualTo("cafe");
           assertThat(items.getItems().get(1).getTitle()).isEqualTo("Русские традиции");
           assertThat(items.getItems().get(1).getTimeWalk()).isEqualTo(5);
           assertThat(items.getItems().get(1).getId()).isEqualTo(1576243);

           assertThat(items.getItems().get(2).getType()).isEqualTo("grocery");
           assertThat(items.getItems().get(2).getTitle()).isEqualTo("Минимаркет");
           assertThat(items.getItems().get(2).getTimeWalk()).isEqualTo(5);
           assertThat(items.getItems().get(2).getId()).isEqualTo(8227900);
       }
    }
}
