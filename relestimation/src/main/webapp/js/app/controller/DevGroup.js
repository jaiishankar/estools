Ext.define('estools.controller.DevGroup', {
    extend: 'Ext.app.Controller',
    views: [
        'devgroup.MasterPanel',
        'devgroup.Edit',
        'devgroup.Grid'
    ],
    stores: [
        'DevGroup'
    ],
    models: [
        'DevGroup'
    ],
    init: function() {
        this.control({
            'devgroupsgrid': {
                itemdblclick: this.onItemDblClick
            },
            'devgroupedit button[action=save]': {
                click: this.updateDevGroup
            },
            'devgroupedit button[action=saveusertogroupsButton]': {
                click: this.updateUserGroup
            },
            'devgroupmasterpanel button[action=newgroup]': {
                click: this.addNewGroup
            },
            'devgroupmasterpanel button[action=deletegroup]': {
                click: this.deleteGroupPrompt
            },
            'devgroupmasterpanel button[action=editgroup]': {
                click: this.editGroup
            }
        });
    },
    deleteGroupPrompt: function() {
        var grid = Ext.getCmp('devgroupgridid');
        var devgrpsGridSM = grid.getSelectionModel();
        if (devgrpsGridSM.hasSelection()) {
            Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete ?', this.deleteGroup, this);
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a ROW to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    editGroup: function() {
        var grid = Ext.getCmp('devgroupgridid');
        var devgrpsGridSM = grid.getSelectionModel();
        var view = Ext.widget('devgroupedit');
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
    addNewGroup: function() {
        var view = Ext.widget('devgroupedit');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Add";
        view.show();
    },
    deleteGroup: function(btn) {
        if (btn === 'yes') {
            var grid = Ext.getCmp('devgroupgridid');
            var devgrpsGridSM = grid.getSelectionModel();
            if (devgrpsGridSM.hasSelection()) {
                var selectedRow = devgrpsGridSM.getSelection();
                var record = {};
                if (selectedRow.length === 1) {
                    record = (selectedRow[0].data);
                }
                Ext.Ajax.request({
                    method: 'POST',
                    url: './v1/devgroups/delete/' + record.id,
                    headers: {
                        'Accept': 'application/json'
                    },
                    scope: this,
                    success: function(response, options) {
                        var responseData = Ext.decode(response.responseText);
                        if (responseData.success) {
                            var grid = Ext.getCmp('devgroupgridid');
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
    onItemDblClick: function(grid, record) {
        var view = Ext.widget('devgroupedit');
        var form = view.down('form');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Update";
        form.loadRecord(record);
        form.store = grid.store;
        view.selectedGroupdId = record.data.id;
        view.show();
    },
    updateDevGroup: function(button) {
        var win = button.up('window'),
                form = win.down('form'),
                values = form.getValues();
        if (button.text !== 'Update') {
            delete values.id;
        }
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/devgroups/',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: values,
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    var grid = Ext.getCmp('devgroupgridid');
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
    },
    updateUserGroup: function(button){
        
        var win = button.up('window'),
                form = win.down('#userstogroupsForm'),
                values = form.getValues();
        values.groupId = win.selectedGroupdId;
        values.userIds = values.userIds.split(",").map(Number);
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/usergroups/group/users/',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: values,
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
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
//}
});