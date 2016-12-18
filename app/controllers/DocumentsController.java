package controllers;
import models.Contractors;
import models.Documents;
import play.data.DynamicForm;
import play.data.Form;

import models.Nomenclatures;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import views.html.documents.*;

/**
 * Created by denter on 17.12.16.
 */
public class DocumentsController extends Controller{

    public Result index() {

        List<Documents> all_documents = Documents.all();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        all_documents.get(0).string_date = dateFormat.format(all_documents.get(0).date);
        return ok(documents.render(all_documents));
    }

    public Result create() {
        return ok(adding_documents.render(Contractors.all(), Nomenclatures.all()));
    }

    public Result store() throws UnsupportedEncodingException, FileNotFoundException {

        DynamicForm form_data = Form.form().bindFromRequest();

        Documents document = Form.form(Documents.class).bindFromRequest().get();
        document.save();

        System.out.println(document.id);

        for (int i = 0; i <= Integer.parseInt(form_data.get("nomenclature_count")); i++) {
        }

        Nomenclatures nomenclature = new Nomenclatures();
        nomenclature.name = Form.form().bindFromRequest().get("test_2");
        nomenclature.save();

        return redirect(routes.DocumentsController.index());
    }
}
