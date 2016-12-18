package controllers;
import models.*;
import play.data.DynamicForm;
import play.data.Form;

import play.mvc.Controller;
import play.mvc.Result;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import views.html.documents.*;

import javax.print.Doc;

/**
 * Created by denter on 17.12.16.
 */
public class DocumentsController extends Controller{

    public Result index() {

        List<Documents> all_documents = Documents.all();
        if (all_documents.size() != 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            int i = 0;
            while (i < all_documents.size()) {
                all_documents.get(i).string_date = dateFormat.format(all_documents.get(i).date);
                i++;
            }
        }

        return ok(documents.render(all_documents));
    }

    public Result create() {
        return ok(adding_documents.render(Contractors.all(), Nomenclatures.all()));
    }

    public Result store() throws UnsupportedEncodingException, FileNotFoundException {

        DynamicForm form_data = Form.form().bindFromRequest();

        Documents document = Form.form(Documents.class).bindFromRequest().get();
        document.save();

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

        if (document.is_conducted) {
            for (int i = 0; i < Integer.parseInt(form_data.get("nomenclature_count")); i++) {
                Goods_remnants goods_remnants = new Goods_remnants();

                goods_remnants.document_id = document.id;

                String nomenclature_field_name = "nomenclature_" + Integer.toString(i);
                goods_remnants.nomenclature_id = Integer.parseInt(form_data.get(nomenclature_field_name));

                String count_field_name = "count_" + Integer.toString(i);
                goods_remnants.count = Integer.parseInt(form_data.get(count_field_name));

                String price_field_name = "price_" + Integer.toString(i);
                goods_remnants.price = Integer.parseInt(form_data.get(price_field_name));

                goods_remnants.save();
            }


        }

        return redirect(routes.DocumentsController.index());
    }

    public Result show(Integer id) {
        return ok(documents_information.render(
                Documents.get_by_id(new Long(id)),
                Goods_receipt.get_by_document_id(new Long(id)))
        );
    }

    public Result down_conduct(Integer id) {
        Documents.down_conduct_by_document_id(String.valueOf(id));
        Goods_remnants.down_conduct_by_document_id(String.valueOf(id));
        return redirect(routes.DocumentsController.index());
    }

    public Result up_conduct(Integer id) {
        Documents.up_conduct_by_document_id(String.valueOf(id));

        List<Goods_receipt> goods_receipts = Goods_receipt.get_by_document_id(new Long(id));

        if (goods_receipts.size() != 0) {
            int i = 0;
            while (i < goods_receipts.size()) {
                Goods_remnants goods_remnants = new Goods_remnants();

                goods_remnants.document_id = id;
                goods_remnants.nomenclature_id = goods_receipts.get(i).nomenclature_id;
                goods_remnants.count = goods_receipts.get(i).count;
                goods_remnants.price = goods_receipts.get(i).price;

                goods_remnants.save();
                i++;
            }
        }

        return redirect(routes.DocumentsController.index());
    }

}
