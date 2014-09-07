Ext.define('estools.view.project.sizing.Grid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'estools.store.ProjectFeatureSizing'
    ],
    title: 'Update Sizing',
    alias: 'widget.featuressizinggrid',
    border: 0,
    editable: false,
    frame: true,
    id: 'featuresizegridid',
    autoSync: false,
    viewConfig: {
        stripeRows: true,
        enableTextSelection: true,
        markDirty: false

    },
    configs: {
        selectedFeatureId: 0,
        selectedProjectId: 0
    },
    // override
    initComponent: function() {
        Ext.apply(this, {
            tbar: [{
                    text: 'Add Sizing',
                    scope: this,
                    handler: this.onAddClick
                }, {
                    text: 'Delete Sizing',
                    scope: this,
                    handler: this.onRemoveClick
                }]
        });

        this.columns = [
            {text: "ID", dataIndex: 'id', width: 5, sortable: true, hidden: true},
            {text: "Group", dataIndex: 'groupId', flex: 1, sortable: true, hidden: false,
                renderer: function(value, metaData, record, row, col, store, gridView) {
                    var val = parseInt(value);
                    if (val !== NaN) {
                        var rec = Ext.StoreMgr.get("DevGroup").findRecord('id', val);
                        return rec.data.name;
                    } else {
                        return value;
                    }
                }},
            {text: "Size", dataIndex: 'sizingId', flex: 1, sortable: true, hidden: false,
                renderer: function(value, metaData, record, row, col, store, gridView) {
                    var val = parseInt(value);
                    if (val !== NaN) {
                        var rec = Ext.StoreMgr.get("Sizing").findRecord('id', val);
                        return rec.data.sizeName;
                    } else {
                        return value;
                    }
                }
            },
            {text: "User", dataIndex: 'userId', flex: 1, sortable: true, hidden: false,
                renderer: function(value, metaData, record, row, col, store, gridView) {
                    var val = parseInt(value);
                    if (val !== NaN) {
                        var rec = Ext.StoreMgr.get("User").findRecord('id', val);
                        return rec.data.lname;
                    } else {
                        return value;
                    }
                }
            },
            {text: "Project", dataIndex: 'projectId', width: 1, sortable: true, hidden: true},
            {text: "Feature", dataIndex: 'featureId', width: 1, sortable: true, hidden: true},
            {text: "id", dataIndex: 'id', width: 1, sortable: true, hidden: true}
        ];
        this.win = new Ext.window.Window({
            items: [this.form],
            scope: this,
            closable: true,
            title: "Add/Update Sizing",
            buttons: [{
                    text: 'Save',
                    handler: this.onSaveClick
                }]
        });
        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("ProjectFeatureSizing");
        this.on('render', this.loadStore, this);
        this.on('itemdblclick', this.loadEdit, this);
        // finally call the superclasses implementation
        this.callParent();
    },
    loadStore: function() {
        if (this.selectedFeatureId > 0) {
            this.getStore().getProxy().url = ('./v1/mappings/feature/' + this.selectedFeatureId);
            this.getStore().load();
            if (Ext.StoreMgr.get("DevGroup").getCount() <= 0) {
                Ext.StoreMgr.get("DevGroup").load();
            }
            if (Ext.StoreMgr.get("Sizing").getCount() <= 0) {
                Ext.StoreMgr.get("Sizing").load();
            }
            if (Ext.StoreMgr.get("User").getCount() <= 0) {
                Ext.StoreMgr.get("User").load();
            }
        }
    },
    loadEdit: function(grid, record) {
        this.onAddClick(record);
    },
    onAddClick: function(rec) {
        if (this.selectedFeatureId > 0) {
            this.form = new Ext.form.Panel({
                id: 'sizingsavedetailsform',
                width: 400,
                height: 200,
                bodyPadding: 15,
                projectId: this.selectedProjectId,
                featureId: this.selectedFeatureId,
                recordId: 0,
                store: this.store,
                items: [{
                        type: 'hidden',
                        name: 'id',
                        value: 0
                    },
                    {
                        type: 'hidden',
                        name: 'projectId',
                        value: this.selectedProjectId
                    },
                    {
                        type: 'hidden',
                        name: 'featureId',
                        value: this.selectedFeatureId
                    },
                    {
                        xtype: 'combo',
                        fieldLabel: 'Development Group',
                        displayField: 'name',
                        valueField: 'id',
                        triggerAction: 'all',
                        editable: false,
                        store: Ext.StoreMgr.get("DevGroup"),
                        name: 'groupId'

                    }, {xtype: 'combo',
                        fieldLabel: 'Size',
                        displayField: 'sizeName',
                        valueField: 'id',
                        triggerAction: 'all',
                        editable: false,
                        store: Ext.StoreMgr.get("Sizing"),
                        name: 'sizingId'},
                    {
                        xtype: 'combo',
                        fieldLabel: 'User',
                        displayField: 'lname',
                        valueField: 'id',
                        triggerAction: 'all',
                        editable: false,
                        store: Ext.StoreMgr.get("User"),
                        name: 'userId'
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
                url: './v1/mappings/delete/' + rec.data.id,
                headers: {
                    'Accept': 'application/json'
                },
                scope: this,
                success: function(response, options) {
                    var responseData = Ext.decode(response.responseText);
                    if (responseData.success) {
                        var grid = Ext.getCmp('featuresizegridid');
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
        var formPanel = Ext.getCmp('sizingsavedetailsform');
        var mainGrid = Ext.getCmp('featuresizegridid');
        var formValues = formPanel.getForm().getValues();
        formValues.projectId = mainGrid.selectedProjectId;
        formValues.featureId = mainGrid.selectedFeatureId;
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
            url: './v1/mappings/',
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

    },
    getIdForLastName: function(name) {
        var rec = Ext.StoreMgr.get("Users").findRecord('lname', name);
        return rec.data.id;
    },
    getIdForGroupName: function(name) {
        var val = parseInt(name);
        var rec = {};
        if (val !== NaN) {
            return val;
        } else {
            rec = this.projectGroupStore.findRecord('name', name);
        }
        return rec.data.id;
    },
    getIdForSizeName: function(name) {
        if (parseInt(name) !== NaN) {
            return parseInt(name);
        }
        var rec = Ext.StoreMgr.get("Sizing").findRecord('sizeName', name);
        return rec.data.id;
    },
    runData: function(data) {
        var source = Ext.StoreMgr.get("DevGroup");
        if (source.getCount() <= 0) {
            source.load();
        } else {
            source.sync();
        }
        for (var i = 0; i < data.length; i++) {
            var rec = source.findRecord('id', data[i]);
            if (rec) {
                this.projectGroupStore.add({id: rec.data.id, name: rec.data.name});
            }
        }
    },
    loadProjectGroups: function() {
        //calls the server and gets the selected groups for the selected project
        var url = './v1/projectgroups/project/' + globalvar.selectedProjectId;
        Ext.Ajax.request({
            method: 'GET',
            url: url,
            scope: this,
            headers: {
                'Accept': 'application/json'
            },
            success: function(response) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    this.runData(responseData.results.groupIds);
                }
            }});
    }
});