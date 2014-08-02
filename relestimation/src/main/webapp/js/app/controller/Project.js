Ext.define('estools.controller.Project', {
    extend: 'Ext.app.Controller',
    views: [
        'project.MasterPanel',
        'project.Edit',
        'project.Grid'
    ],
    stores: [
        'DevGroup'
    ],
    models: [
        'DevGroup'
    ],
    init: function() {
        this.control({
            'projectgrid': {
                itemdblclick: this.onItemDblClick
            },
            'projectedit button[action=save]': {
                click: this.updateProject
            },
            'projectedit button[action=saveProjectGroupsButton]': {
                click: this.updateDevGroupWithProject
            },
            'projectmasterpanel button[action=newproject]': {
                click: this.addNewGroup
            },
            'projectmasterpanel button[action=deleteproject]': {
                click: this.deleteGroup
            },
            'projectmasterpanel button[action=editproject]': {
                click: this.editGroup
            }
        });
    },
    editGroup: function() {
        var grid = Ext.getCmp('projectgridid');
        var projectsGridSM = grid.getSelectionModel();
        var view = Ext.widget('projectedit');
        var form = view.down('form');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Update";
        if (projectsGridSM.hasSelection()) {
            var selectedRow = projectsGridSM.getSelection();
            var record = {};
            if (selectedRow.length === 1) {
                record = (selectedRow[0].data);
            }
            view.selectedProjectId = record.id;
            var editRecord = grid.getStore().findRecord("id", record.id);
            form.loadRecord(editRecord);
            form.store = grid.getStore();
            view.show();
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a ROW to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    addNewGroup: function() {
        var view = Ext.widget('projectedit');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Add";
        view.show();
    },
    deleteGroup: function() {
        var grid = Ext.getCmp('projectgridid');
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
                url: './v1/projects/delete/' + record.id,
                headers: {
                    'Accept': 'application/json'
                },
                scope: this,
                success: function(response, options) {
                    var responseData = Ext.decode(response.responseText);
                    if (responseData.success) {
                        var grid = Ext.getCmp('projectgridid');
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

        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a ROW to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    onItemDblClick: function(grid, record) {
        var view = Ext.widget('projectedit');
        view.selectedProjectId = record.id;
        var form = view.down('form');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Update";
        form.loadRecord(record);
        form.store = grid.store;
        view.show();
    },
    updateProject: function(button) {
        var win = button.up('window'),
                form = win.down('form'),
                values = form.getValues();
        if (button.text !== 'Update') {
            delete values.id;
            values.ownerId = globalvar.currentUserId;
        } 
        
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/projects/',
            headers: {
                'Accept': 'application/json'
            },
            jsonData: values,
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    var grid = Ext.getCmp('projectgridid');
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
    updateDevGroupWithProject : function(button){
         var win = button.up('window'),
                form = win.down('#projectGroupsForm'),
                values = form.getValues();
        console.log(values);
    }
});