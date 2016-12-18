package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.SqlUpdate;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.print.Doc;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by denter on 17.12.16.
 */

@Entity
public class Documents extends Model{

    @Id
    public Integer id;

    @Constraints.Required
    public Date date;

    @ManyToOne()
    public Contractors contractor;

    @Column(columnDefinition = "boolean default false")
    public Boolean is_conducted;

    @Transient
    public String string_date;

    public Documents() {
        is_conducted = false;
    }

    public static Finder<Long, Documents> find = new Finder<Long, Documents>(Documents.class);

    public static List<Documents> all() {
        return  find.all();
    }

    public static  Documents get_by_id(Long id) {
        return find.byId(id);
    }

    public static void down_conduct_by_document_id(String document_id) {
        SqlUpdate down_conducted = Ebean.createSqlUpdate("UPDATE documents SET is_conducted = false WHERE id = " + document_id);
        down_conducted.execute();
    }

    public static void up_conduct_by_document_id(String document_id) {
        SqlUpdate up_conducted = Ebean.createSqlUpdate("UPDATE documents SET is_conducted = true WHERE id = " + document_id);
        up_conducted.execute();
    }
}
