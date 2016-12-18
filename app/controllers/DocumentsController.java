package controllers;
import models.Contractors;
import models.Documents;
import models.Goods_receipt;
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

        for (int i = 0; i < Integer.parseInt(form_data.get("nomenclature_count")); i++) {
            Goods_receipt good_receipt = new Goods_receipt();
            good_receipt.document_id = document.id;

            String nomenclature_field_name = "nomenclature_" + Integer.toString(i);
            good_receipt.nomenclature_id = Integer.parseInt(form_data.get(nomenclature_field_name));

            String count_field_name = "count_" + Integer.toString(i);
            good_receipt.count = Integer.parseInt(form_data.get(count_field_name));

            String price_field_name = "price_" + Integer.toString(i);
            good_receipt.price = Integer.parseInt(form_data.get(price_field_name));

            good_receipt.save();
        }

        return redirect(routes.DocumentsController.index());
    }

    public Result show(Integer id) {
        return ok(documents_information.render(
                Documents.get_by_id(new Long(id)),
                Goods_receipt.get_by_document_id(new Long(id)))
        );
    }
}
