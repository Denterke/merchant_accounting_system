package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.Update;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * Created by denter on 17.12.16.
 */

@Entity
public class Goods_receipt extends Model{

    public Integer document_id;

    public Integer nomenclature_id;

    public Integer count;

    public Integer price;

    @ManyToOne()
    @JoinColumn(name = "nomenclature_id", insertable = false, updatable = false)
    public Nomenclatures nomenclature;

    public static Finder<Long, Goods_receipt> find = new Finder<Long, Goods_receipt>(Goods_receipt.class);

    public static List<Goods_receipt> all() {
        return  find.all();
    }

    public static List<Goods_receipt> get_by_document_id(Long document_id) {

        return  find
                .where()
                .eq("document_id", toIntExact(document_id))
                .findList();
    }
}
