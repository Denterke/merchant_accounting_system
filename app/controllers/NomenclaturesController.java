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

        Form<Nomenclatures> filledForm = Form.form(Nomenclatures.class).bindFromRequest();

        return ok(adding_nomenclatures.render(filledForm));
    }

    public Result store() throws UnsupportedEncodingException, FileNotFoundException {

        Form<Nomenclatures> filledForm = Form.form(Nomenclatures.class).bindFromRequest();

        if (filledForm.hasErrors()) {
            return badRequest(adding_nomenclatures.render(filledForm));
        }

        Nomenclatures name = filledForm.get();

        filledForm.get();
        name.save();
        return redirect(routes.NomenclaturesController.index());
    }

}
