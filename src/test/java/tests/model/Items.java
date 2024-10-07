package tests.model;

import java.util.List;

public class Items {

    private List<Items.items> items;

    public List<Items.items> getItems() {
        return items;
    }

    public static class items {
        private String type;
        private String title;
        private Integer timeWalk;
        private Integer id;

        public String getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public Integer getTimeWalk() {
            return timeWalk;
        }

        public Integer getId() {
            return id;
        }
    }
}
