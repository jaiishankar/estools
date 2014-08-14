/* 
 *contains all the controller actions for sizing tab
 */
Ext.define('estools.controller.Priorities', {
    extend: 'Ext.app.Controller',
    views: [
        'priority.MasterPanel',
        'priority.Edit',
        'priority.Grid'
    ],
    stores: [
        'Priority'
    ],
    models: [
        'Priority'
    ],
    init: function() {
        this.control({
            'prioritygrid': {
                itemdblclick: this.onItemDblClick
            },
            'prioritymasterpanel button[action=editpriority]': {
                click: this.editSizing
            },
            'priorityedit button[action=save]': {
                click: this.updateSizing
            },
            'prioritymasterpanel button[action=deletepriority]': {
                click: this.deleteSizingPrompt
            },
            'prioritymasterpanel button[action=newpriority]': {
                click: this.newSizing
            }
        });
    },
    deleteSizingPrompt: function() {
        var grid = Ext.getCmp('prioritygridid');
        var devgrpsGridSM = grid.getSelectionModel();
        if (devgrpsGridSM.hasSelection()) {
            Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete ?', this.deleteSizing, this);
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a ROW to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    newSizing: function() {
        var view = Ext.widget('priorityedit');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Add";
        view.show();
    },
    onItemDblClick: function(grid, record) {
        var view = Ext.widget('priorityedit');
        var form = view.down('form');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Update";
        form.loadRecord(record);
        form.store = grid.store;
        view.show();
    },
    editSizing: function() {
        var grid = Ext.getCmp('sizinggridid');
        var devgrpsGridSM = grid.getSelectionModel();
        var view = Ext.widget('priorityedit');
        var form = view.down('form');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Update";
        if (devgrpsGridSM.hasSelection()) {
            var selectedRow = devgrpsGridSM.getSelection();
            var record = {};
            if (selectedRow.length === 1) {
                record = (selectedRow[0].data);
            }
            var editRecord = grid.getStore().findRecord("id", record.id);
            form.loadRecord(editRecord);
            form.store = grid.getStore();
            view.show();
        }
    },
    deleteSizing: function(btn) {
        if (btn === 'yes') {
            var grid = Ext.getCmp('prioritygridid');
            var devgrpsGridSM = grid.getSelectionModel();
            if (devgrpsGridSM.hasSelection()) {
                var selectedRow = devgrpsGridSM.getSelection();
                var record = {};
                if (selectedRow.length === 1) {
                    record = (selectedRow[0].data);
                }
                console.log(record);
                Ext.Ajax.request({
                    method: 'POST',
                    url: './v1/priorities/delete/' + record.id,
                    headers: {
                        'Accept': 'application/json'
                    },
                    scope: this,
                    success: function(response, options) {
                        var responseData = Ext.decode(response.responseText);
                        if (responseData.success) {
                            var grid = Ext.getCmp('prioritygridid');
                            grid.getStore().reload({
                                callback: function() {
                                    grid.getView().refresh();
                                }
                            });
                            //win.close();
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
                    msg: "No Rows selected, Please select a ROW to delete.",
                    icon: Ext.Msg.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    },
    updateSizing: function(button) {
        var win = button.up('window'),
                form = win.down('form'),
                values = form.getValues();
        if (button.text !== 'Update') {
            delete values.id;
        }
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/priorities/',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: values,
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    var grid = Ext.getCmp('prioritygridid');
                    grid.getStore().reload({
                        callback: function() {
                            grid.getView().refresh();
                        }
                    });
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

