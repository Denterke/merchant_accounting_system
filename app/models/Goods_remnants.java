package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.SqlUpdate;
import com.avaje.ebean.Update;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * Created by denter on 18.12.16.
 */

@Entity
public class Goods_remnants extends Model{

    public Integer document_id;

    @Constraints.Required
    public Date date;

    public Integer nomenclature_id;

    public Integer count;

    public Integer price;

    public Goods_remnants() {
        date = new Date();
    }

    @ManyToOne()
    @JoinColumn(name = "nomenclature_id", insertable = false, updatable = false)
    public Nomenclatures nomenclature;

    @ManyToOne()
    @JoinColumn(name = "document_id", insertable = false, updatable = false)
    public Documents document;

    public static Finder<Long, Goods_remnants> find = new Finder<Long, Goods_remnants>(Goods_remnants.class);

    public static List<Goods_remnants> all() {
        return  find.all();
    }

    public static List<Goods_remnants> get_by_nomenclature_id(Long nomenclature_id) {

        return  find
                .where()
                .eq("nomenclature_id", toIntExact(nomenclature_id))
                .findList();
    }

    public static void down_conduct_by_document_id(String document_id) {
        SqlUpdate down_conducted = Ebean.createSqlUpdate("DELETE FROM goods_remnants WHERE document_id = " + document_id);
        down_conducted.execute();
    }
}
