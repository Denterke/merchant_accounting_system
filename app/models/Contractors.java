package models;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Contractors extends Model {

    @Id
    public Integer id;

    @Constraints.Required
    public String name;

    public static Finder<Long, Contractors> find = new Finder<Long, Contractors>(Contractors.class);

    public static List<Contractors> all() {

        return find.all();
    }

    public void set_utf8_encoding() throws UnsupportedEncodingException {
        this.name = new String(this.name.getBytes("ISO-8859-1"), "UTF-8");
    }

    @Override
    public void save() {
        try {
            this.set_utf8_encoding();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        super.save();
    }


}