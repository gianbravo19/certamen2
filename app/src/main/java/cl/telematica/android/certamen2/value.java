package cl.telematica.android.certamen2;

/**
 * Created by italiano Leo on 18-12-2015.
 */
public class value {
    private String type;
    private Integer id;
    private String joke;
    private String[] categories;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }


}
