/* 
 *contains all the controller actions for sizing tab
 */
Ext.define('estools.controller.Assumptiontype', {
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
            'assumtypegrid': {
                itemdblclick: this.onItemDblClick
            },
            'assumtypemasterpanel button[action=editAssumType]': {
                click: this.editPriorities
            },
            'assumtypeedit button[action=save]': {
                click: this.updatePriorities
            },
            'assumtypemasterpanel button[action=deleteAssumType]': {
                click: this.deletePrioritiyPrompt
            },
            'assumtypemasterpanel button[action=newAssumType]': {
                click: this.newPriority
            }
        });
    },
    deletePrioritiyPrompt: function() {
        var grid = Ext.getCmp('assumtypegridid');
        var devgrpsGridSM = grid.getSelectionModel();
        if (devgrpsGridSM.hasSelection()) {
            Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete ?', this.deletePriority, this);
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a ROW to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    newPriority: function() {
        var view = Ext.widget('assumtypeedit');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Add";
        view.show();
    },
    onItemDblClick: function(grid, record) {
        var view = Ext.widget('assumtypeedit');
        var form = view.down('form');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Update";
        form.loadRecord(record);
        form.store = grid.store;
        view.show();
    },
    editPriorities: function() {
        var grid = Ext.getCmp('assumtypegridid');
        var devgrpsGridSM = grid.getSelectionModel();
        var view = Ext.widget('assumtypeedit');
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
    deletePriority: function(btn) {
        if (btn === 'yes') {
            var grid = Ext.getCmp('assumtypegridid');
            var devgrpsGridSM = grid.getSelectionModel();
            if (devgrpsGridSM.hasSelection()) {
                var selectedRow = devgrpsGridSM.getSelection();
                var record = {};
                if (selectedRow.length === 1) {
                    record = (selectedRow[0].data);
                }
                Ext.Ajax.request({
                    method: 'POST',
                    url: './v1/assumptiontypes/delete/' + record.id,
                    headers: {
                        'Accept': 'application/json'
                    },
                    scope: this,
                    success: function(response, options) {
                        var responseData = Ext.decode(response.responseText);
                        if (responseData.success) {
                            var grid = Ext.getCmp('assumtypegridid');
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
    updatePriorities: function(button) {
        var win = button.up('window'),
                form = win.down('form'),
                values = form.getValues();
        if (button.text !== 'Update') {
            delete values.id;
        }
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/assumptiontypes/',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: values,
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    var grid = Ext.getCmp('assumtypegridid');
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