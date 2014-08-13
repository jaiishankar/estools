/* 
 *contains all the controller actions for sizing tab
 */
Ext.define('estools.controller.Sizing', {
    extend: 'Ext.app.Controller',
    views: [
        'sizing.MasterPanel',
        'sizing.Edit',
        'sizing.Grid'
    ],
    stores: [
        'Sizing'
    ],
    models: [
        'Sizing'
    ],
    init: function() {
        this.control({
            'sizinggrid': {
                itemdblclick: this.onItemDblClick
            },
            'sizingmasterpanel button[action=editsizing]': {
                click: this.editSizing
            },
            'sizingedit button[action=save]': {
                click: this.updateSizing
            },
            'sizingmasterpanel button[action=deletesizing]': {
                click: this.deleteSizingPrompt
            },
            'sizingmasterpanel button[action=newsizing]': {
                click: this.newSizing
            }
        });
    },
    deleteSizingPrompt: function() {
        var grid = Ext.getCmp('sizinggridid');
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
        var view = Ext.widget('sizingedit');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Add";
        view.show();
    },
    onItemDblClick: function(grid, record) {
        var view = Ext.widget('sizingedit');
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
        var view = Ext.widget('sizingedit');
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
            var grid = Ext.getCmp('sizinggridid');
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
                    url: './v1/sizing/delete/' + record.id,
                    headers: {
                        'Accept': 'application/json'
                    },
                    scope: this,
                    success: function(response, options) {
                        var responseData = Ext.decode(response.responseText);
                        if (responseData.success) {
                            var grid = Ext.getCmp('sizinggridid');
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
            url: './v1/sizing/',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: values,
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    var grid = Ext.getCmp('sizinggridid');
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

