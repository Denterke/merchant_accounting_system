package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Entity
public class Nomenclatures extends Model {

    @Id
    public Integer id;

    @Constraints.Required
    public String name;

    public static Finder<Long, Nomenclatures> find = new Finder<Long, Nomenclatures>(Nomenclatures.class);

    public static List<Nomenclatures> all() {

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