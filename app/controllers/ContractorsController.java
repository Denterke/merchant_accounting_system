package controllers;
import play.data.Form;

import models.Contractors;
import play.*;
import play.mvc.*;

import views.html.contractors.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class ContractorsController extends Controller{

    public Result index() {

        return ok(contractors.render(Contractors.all()));
    }

    public Result create() {

        Form<Contractors> filledForm = Form.form(Contractors.class).bindFromRequest();

        return ok(adding_contractors.render(filledForm));
    }

    public Result store() throws UnsupportedEncodingException, FileNotFoundException {

        Form<Contractors> filledForm = Form.form(Contractors.class).bindFromRequest();

        if (filledForm.hasErrors()) {
            return badRequest(adding_contractors.render(filledForm));
        }

        Contractors name = filledForm.get();

        filledForm.get();
        name.save();
        return redirect(routes.ContractorsController.index());
    }

}
