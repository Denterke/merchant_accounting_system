package controllers;
import models.Documents;
import models.Goods_remnants;
import play.data.Form;

import models.Nomenclatures;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.nomenclatures.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

public class NomenclaturesController extends Controller{

    public Result index() {

        return ok(nomenclatures.render(Nomenclatures.all()));
    }

    public Result create() {
        return ok(adding_nomenclatures.render());
    }

    public Result store() throws UnsupportedEncodingException, FileNotFoundException {

        Nomenclatures nomenclature = Form.form(Nomenclatures.class).bindFromRequest().get();
        nomenclature.save();

        return redirect(routes.NomenclaturesController.index());
    }

    public Result show(Integer id) {

        List<Goods_remnants> all_goods_remnants = Goods_remnants.get_by_nomenclature_id(new Long(id));

        if (all_goods_remnants.size() != 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            int i = 0;
            while (i < all_goods_remnants.size()) {
                all_goods_remnants.get(i).document.string_date = dateFormat.format(all_goods_remnants.get(i).date);
                i++;
            }
        }

        return ok((goods_remnants.render(all_goods_remnants)));
    }

}
