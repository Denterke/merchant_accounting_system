# goods_receipt schema

# --- !Ups
CREATE TABLE goods_receipt
(
    document_id INTEGER NOT NULL,
    nomenclature_id INTEGER NOT NULL,
    count INTEGER NOT NULL,
    price INTEGER NOT NULL,
    CONSTRAINT goods_receipt_pkey PRIMARY KEY (document_id, nomenclature_id),
    CONSTRAINT goods_receipt_document_id_on_delete_foreign FOREIGN KEY (document_id) REFERENCES documents (id) ON DELETE CASCADE,
    CONSTRAINT goods_receipt_document_id_on_update_foreign FOREIGN KEY (document_id) REFERENCES documents (id) ON UPDATE CASCADE,
    CONSTRAINT goods_receipt_nomenclature_id_on_delete_foreign FOREIGN KEY (nomenclature_id) REFERENCES nomenclatures (id) ON DELETE CASCADE,
    CONSTRAINT goods_receipt_nomenclature_id_on_update_foreign FOREIGN KEY (nomenclature_id) REFERENCES nomenclatures (id) ON UPDATE CASCADE
);
