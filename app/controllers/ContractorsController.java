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
        return ok(adding_contractors.render());
    }

    public Result store() throws UnsupportedEncodingException, FileNotFoundException {

        Contractors contractor = Form.form(Contractors.class).bindFromRequest().get();
        contractor.save();

        return redirect(routes.ContractorsController.index());
    }

}
