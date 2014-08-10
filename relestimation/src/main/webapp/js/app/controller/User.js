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
                click: this.updateProcess
            },
            'useredit button[action=saveUserGroupsButton]': {
                click: this.updateUserGroupWithProject
            }
        });
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
    onItemDblClick: function(grid, record) {

        var view = Ext.widget('useredit');
        var form = view.down('form');

        form.loadRecord(record);
        form.store = grid.store;
        view.show();
    },
    updateProcess: function(button) {
        var win = button.up('window'),
                form = win.down('form'),
                record = form.getRecord(),
                values = form.getValues();

        record.set(values);
        form.store.sync();
        win.close();
    }
});