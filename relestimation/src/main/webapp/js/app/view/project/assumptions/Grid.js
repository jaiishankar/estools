Ext.define('estools.view.project.assumptions.Grid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'estools.store.Assumption'
    ],
    title: 'Update Sizing',
    alias: 'widget.casesassumptionsgrid',
    border: 0,
    editable: false,
    frame: true,
    id: 'casesassumptionsgridid',
    autoSync: false,
    viewConfig: {
        stripeRows: true,
        enableTextSelection: true,
        markDirty: false
    },
    configs: {
        selectedCaseId: 0
    },
    // override
    initComponent: function() {
        Ext.apply(this, {
            tbar: [{
                    text: 'Add Assumption',
                    scope: this,
                    handler: this.onAddClick
                }, {
                    text: 'Delete Assumption',
                    scope: this,
                    handler: this.onRemoveClick
                }]
        });

        this.columns = [
            {text: "ID", dataIndex: 'id', width: 5, sortable: true, hidden: true},
            {text: "caseId", dataIndex: 'caseId', width: 1, sortable: true, hidden: true},
            {text: "Assumption", dataIndex: 'assumption', flex: 1, sortable: true, hidden: false},
            {text: "Type", dataIndex: 'typeId', flex: 1, sortable: true, hidden: false,
                renderer: function(value, metaData, record, row, col, store, gridView) {
                    var val = parseInt(value);
                    if (val !== NaN) {
                        var rec = Ext.StoreMgr.get("AssumptionType").findRecord('id', val);
                        return rec.data.typeName;
                    } else {
                        return value;
                    }
                }
            }
        ];
        this.win = new Ext.window.Window({
            items: [this.form],
            scope: this,
            closable: true,
            title: "Add/Update Assumption",
            buttons: [{
                    text: 'Save',
                    handler: this.onSaveClick
                }]
        });
        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("Assumption");
        this.on('render', this.loadStore, this);
        this.on('itemdblclick', this.loadEdit, this);
        // finally call the superclasses implementation
        this.callParent();
    },
    loadStore: function() {
        if (this.selectedCaseId > 0) {
            this.getStore().getProxy().url = ('./v1/assumption/case/' + this.selectedCaseId);
            this.getStore().load();
            if (Ext.StoreMgr.get("AssumptionType").getCount() <= 0) {
                Ext.StoreMgr.get("AssumptionType").load();
            } else {
                Ext.StoreMgr.get("AssumptionType").sync();
            }

        }
    },
    loadEdit: function(grid, record) {
        this.onAddClick(record);
    },
    onAddClick: function(rec) {
        if (this.selectedCaseId > 0) {
            this.form = new Ext.form.Panel({
                id: 'assumptionsaveddetailsform',
                width: 400,
                height: 200,
                bodyPadding: 15,
                caseId: this.selectedCaseId,
                recordId: 0,
                store: this.store,
                items: [{
                        type: 'hidden',
                        name: 'id',
                        value: 0
                    },
                    {
                        type: 'hidden',
                        name: 'caseId',
                        value: this.selectedCaseId
                    },{
                        xtype:'textarea',
                        name:'assumption',
                        fieldLabel:'Assumption',
                        allowBlank: false
                    },
                    {
                        xtype: 'combo',
                        fieldLabel: 'Assumption Type',
                        displayField: 'typeName',
                        valueField: 'id',
                        triggerAction: 'all',
                        editable: false,
                        store: Ext.StoreMgr.get("AssumptionType"),
                        name: 'typeId',
                        allowBlank:false

                    }]
            });
            if (!rec.text) {
                this.form.loadRecord(rec);
                if (rec.data.id && rec.data.id > 0) {
                    this.form.recordId = rec.data.id;
                }
            }

            this.win = new Ext.window.Window({
                items: [this.form],
                scope: this,
                closable: true,
                title: "New Sizing",
                buttons: [{
                        text: 'Save',
                        handler: this.onSaveClick
                    }]
            });
            this.win.show();
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Features selected, Please select a Feature.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    onRemoveClick: function(button) {
        var sm = this.getSelectionModel();
        if (sm.hasSelection()) {
            var rec = sm.getSelection()[0];
            Ext.Ajax.request({
                method: 'POST',
                url: './v1/assumption/delete/' + rec.data.id,
                headers: {
                    'Accept': 'application/json'
                },
                scope: this,
                success: function(response, options) {
                    var responseData = Ext.decode(response.responseText);
                    if (responseData.success) {
                        var grid = Ext.getCmp('casesassumptionsgridid');
                        grid.getStore().reload({
                            callback: function() {
                                grid.getView().refresh();
                            }
                        });
                    }
                    else {
                        var errorObject = responseData.results;
                        Ext.Msg.show({
                            title: errorObject.title,
                            msg: errorObject.msg,
                            icon: Ext.Msg.ERROR,
                            buttons: Ext.Msg.OK
                        });
                    }
                }});
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a row to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    onSaveClick: function(button) {
        var win = button.up('window');
        var formPanel = Ext.getCmp('assumptionsaveddetailsform');
        var mainGrid = Ext.getCmp('casesassumptionsgridid');
        var formValues = formPanel.getForm().getValues();
        formValues.caseId = mainGrid.selectedCaseId;
        formValues.id = formPanel.recordId;
        mainGrid.onSaveClicker(formValues, win);
    },
    onSaveClicker: function(values, win) {
        if (values.id <= 0) {
            delete values.id;
        }
        console.log(values);
        //convert all the ids to number
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/assumption/',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: values,
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    this.getStore().reload();
                    win.close();
                }
                else {
                    var errorObject = responseData.results;
                    Ext.Msg.show({
                        title: errorObject.title,
                        msg: errorObject.msg,
                        icon: Ext.Msg.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }});

    }
});