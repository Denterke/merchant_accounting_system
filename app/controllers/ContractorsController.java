package controllers;
import play.data.Form;

import models.Contractors;
import play.*;
import play.mvc.*;

import views.html.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
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

        System.out.println(new String(name.name.getBytes("ISO-8859-1"), "UTF-8"));
        filledForm.get();
        name.save();
        return redirect(routes.ContractorsController.index());
    }

}
