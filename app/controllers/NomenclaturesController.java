package controllers;
import play.data.Form;

import models.Nomenclatures;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.nomenclatures.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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

}
