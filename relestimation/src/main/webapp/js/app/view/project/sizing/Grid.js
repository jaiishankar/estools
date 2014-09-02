Ext.define('estools.view.project.sizing.Grid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.selection.CellModel',
        'estools.store.ProjectFeatureSizing'

    ],
    title: 'Update Sizing',
    alias: 'widget.featuressizinggrid',
    border: 0,
    editable: false,
    frame: true,
    id: 'featuresizegridid',
    viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
    projectGroupStore: {},
    groupUserStore: {},
    configs: {
        selectedFeatureId: 0,
        selectedProjectId: 0
    },
    // override
    initComponent: function() {
        // Pass in a column model definition
        this.cellEditing = new Ext.grid.plugin.CellEditing({
            clicksToEdit: 1
        });

        Ext.apply(this, {
            plugins: [this.cellEditing],
            selModel: {
                selType: 'cellmodel'
            },
            tbar: [{
                    text: 'Add Sizing',
                    scope: this,
                    handler: this.onAddClick
                }]
        });
        this.projectGroupStore = Ext.create('Ext.data.Store', {
            fields: ['id', 'name']
        });
        this.loadProjectGroups();

        this.columns = [
            {text: "ID", dataIndex: 'id', width: 5, sortable: true, hidden: true},
            {text: "Group", dataIndex: 'groupId', flex: 1, sortable: true, hidden: false,
                editor: new Ext.form.field.ComboBox({
                    typeAhead: true,
                    triggerAction: 'all',
                    store: this.projectGroupStore,
                    valueField: 'name',
                    displayField: 'name',
                    queryMode: 'local'
                })
                ,
                renderer: function(value, metaData, record, row, col, store, gridView) {
                    var val = parseInt(value);
                    if (val !== NaN) {
                        return this.projectGroupStore.findRecord('id', val).data.name;
                    } else {
                        return value;
                    }
                }
            },
            {text: "Size", dataIndex: 'sizingId', flex: 1, sortable: true, hidden: false,
                editor: new Ext.form.field.ComboBox({
                    typeAhead: true,
                    triggerAction: 'all',
                    store: Ext.StoreMgr.get("Sizing"),
                    valueField: 'sizeName',
                    displayField: 'sizeName'
                }),
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
                editor: new Ext.form.field.ComboBox({
                    typeAhead: true,
                    triggerAction: 'all',
                    store: Ext.StoreMgr.get("User"),
                    valueField: 'lname',
                    displayField: 'lname'
                }),
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
            {
                xtype: 'actioncolumn',
                width: 30,
                sortable: false,
                menuDisabled: true,
                items: [{
                        icon: './images/save.png',
                        tooltip: 'Save',
                        scope: this,
                        handler: this.onSaveClick
                    }]
            },
            {
                xtype: 'actioncolumn',
                width: 30,
                sortable: false,
                menuDisabled: true,
                items: [{
                        icon: './images/delete.png',
                        tooltip: 'Delete',
                        scope: this,
                        handler: this.onRemoveClick
                    }]
            }
        ];

        // Note the use of a storeId, this will register thisStore
        // with the StoreManager and allow us to retrieve it very easily.
        this.store = Ext.StoreMgr.get("ProjectFeatureSizing");
        this.on('render', this.loadStore, this);
        this.on('edit', this.onSaveClick, this);
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
    onAddClick: function() {

        if (this.selectedFeatureId > 0) {
            // Create a model instance
            var rec = new estools.model.ProjectFeatureSizing({
                'sizingId': '1',
                'userId': '1',
                'groupId': '1',
                'featureId': this.selectedFeatureId,
                'projectId': this.selectedProjectId
            });

            this.getStore().insert(0, rec);
            this.cellEditing.startEditByPosition({
                row: 0,
                column: 0
            });
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Features selected, Please select a Feature.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    onRemoveClick: function(grid, rowIndex) {
        //this.getStore().removeAt(rowIndex);
        var rec = grid.getStore().getAt(rowIndex);
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
    },
    onSaveClick: function(grid, rowIndex) {
        var rec = grid.getStore().getAt(rowIndex);
        var values = rec.data;
        if (values.id <= 0) {
            delete values.id;
        }
        console.log(values);
        //convert all the ids to number
        values.groupId = this.getIdForGroupName(values.groupId);
        values.sizingId = this.getIdForSizeName(values.sizingId);
        values.userId = globalvar.currentUserId;

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
                    this.getStore().reload({
                        callback: function() {
                            alert("done");
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
        console.log(values);

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
        if(parseInt(name) !== NaN){
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