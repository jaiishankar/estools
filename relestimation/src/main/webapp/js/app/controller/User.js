Ext.define('estools.controller.User', {
    extend: 'Ext.app.Controller',
    views: [
        'users.MasterPanel',
        'users.Edit'
    ],
    stores: [
        'User'
    ],
    models: [
        'User'
    ],
    init: function() {
        this.control({
            'usersgrid': {
                itemdblclick: this.onItemDblClick
            },
            'useredit button[action=save]': {
                click: this.updateUser
            },
            'usersmasterpanel button[action=newuser]': {
                click: this.newUser
            },
            'usersmasterpanel button[action=edituser]': {
                click: this.editUser
            },
            'usersmasterpanel button[action=deleteuser]': {
                click: this.deleteUsersPrompt
            },
            'useredit button[action=saveUserGroupsButton]': {
                click: this.updateUserGroupWithProject
            }
        });
    },
    deleteUsersPrompt: function() {
        var grid = Ext.getCmp('usersgridid');
        var devgrpsGridSM = grid.getSelectionModel();
        if (devgrpsGridSM.hasSelection()) {
            Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete ?', this.deleteUser, this);
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a ROW to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    deleteUser: function(btn) {
        if (btn === 'yes') {
            var grid = Ext.getCmp('usersgridid');
            var devgrpsGridSM = grid.getSelectionModel();
            if (devgrpsGridSM.hasSelection()) {
                var selectedRow = devgrpsGridSM.getSelection();
                var record = {};
                if (selectedRow.length === 1) {
                    record = (selectedRow[0].data);
                }
                Ext.Ajax.request({
                    method: 'POST',
                    url: './v1/users/delete/' + record.id,
                    headers: {
                        'Accept': 'application/json'
                    },
                    scope: this,
                    success: function(response, options) {
                        var responseData = Ext.decode(response.responseText);
                        if (responseData.success) {
                            var grid = Ext.getCmp('usersgridid');
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
                    msg: "No Rows selected, Please select a ROW to delete.",
                    icon: Ext.Msg.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    },
    updateUserGroupWithProject: function(button) {
        var win = button.up('window'),
                form = win.down('#userGroupsForm'),
                values = form.getValues();
        values.userId = globalvar.currentUserId;
        values.groupIds = values.groupIds.split(",").map(Number);
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/usergroups/user/groups/',
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
    },
    editUser: function(button) {
        var grid = Ext.getCmp('usersgridid');
        var devgrpsGridSM = grid.getSelectionModel();
        var view = Ext.widget('useredit');
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
    newUser: function(button) {
        var view = Ext.widget('useredit');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Add";
        view.show();
    },
    onItemDblClick: function(grid, record) {
        var view = Ext.widget('useredit');
        var form = view.down('form');
        form.loadRecord(record);
        form.store = grid.store;
        view.show();
    },
    updateUser: function(button) {
        var win = button.up('window'),
                form = win.down('form'),
                values = form.getValues();
        if (button.text !== 'Update') {
            delete values.id;
        }
        if(values.isAdminUser){
            if(values.isAdminUser === "on"){
                values.isAdminUser = true;
            }
        } else {
            values.isAdminUser = false;
        }

        Ext.Ajax.request({
            method: 'POST',
            url: './v1/users',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: values,
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    var grid = Ext.getCmp('usersgridid');
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
        win.close();
    }
});