package models;

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


}