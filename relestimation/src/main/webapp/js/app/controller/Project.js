Ext.define('estools.controller.Project', {
    extend: 'Ext.app.Controller',
    views: [
        'project.MasterPanel',
        'project.Edit',
        'project.Grid',
        'project.metrics.Grid'
    ],
    stores: [
        'DevGroup', 'ProjectMetrics'
    ],
    models: [
        'DevGroup', 'ProjectMetrics'
    ],
    init: function() {
        this.control({
            'projectgrid': {
                itemdblclick: this.onItemDblClick
            },
            'projectfeaturesgrid': {
                select: this.featuresSelect,
                itemdblclick: this.featuresSelect
            },
            'projectmetricsgrid': {
                select: this.metricsSelect,
                itemdblclick: this.metricsSelect
            },
            'projectedit button[action=save]': {
                click: this.updateProject
            },
            'projectedit button[action=saveProjectGroupsButton]': {
                click: this.updateDevGroupWithProject
            },
            'projectmasterpanel button[action=newproject]': {
                click: this.addNewProject
            },
            'projectmasterpanel button[action=deleteproject]': {
                click: this.deleteProjectWithPrompt
            },
            'projectmasterpanel button[action=editproject]': {
                click: this.editProject
            },
            'projectedit button[action=saveMetrics]': {
                click: this.saveProjectMetrics
            },
            'projectedit button[action=newMetrics]': {
                click: this.newProjectMetrics
            },
            'projectedit button[action=deleteMetrics]': {
                click: this.deleteMetrics
            },
            'projectedit button[action=newFeatures]': {
                click: this.newFeatures
            },
            'projectedit button[action=saveFeatures]': {
                click: this.saveFeatures
            },
            'projectedit button[action=deleteFeatures]': {
                click: this.deleteFeatures
            }

        });
    },
    deleteProjectWithPrompt: function() {
        var grid = Ext.getCmp('projectgridid');
        var devgrpsGridSM = grid.getSelectionModel();
        if (devgrpsGridSM.hasSelection()) {
            Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete ?', this.deleteProject, this);
            //we need to delete all the childrens
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a ROW to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    editProject: function() {
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
    addNewProject: function() {
        var view = Ext.widget('projectedit');
        var saveBtn = view.down('#saveButton');
        saveBtn.text = "Add";
        view.show();
    },
    deleteProject: function() {
        var grid = Ext.getCmp('projectgridid');
        var devgrpsGridSM = grid.getSelectionModel();
        if (devgrpsGridSM.hasSelection()) {
            var selectedRow = devgrpsGridSM.getSelection();
            var record = {};
            if (selectedRow.length === 1) {
                record = (selectedRow[0].data);
            }
            if (record.id === globalvar.currentUserId) {
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
                    title: "Delete Fail !!!",
                    msg: "You are not the Authorized to delete this project",
                    icon: Ext.Msg.ERROR,
                    buttons: Ext.Msg.OK
                });
            }

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
        view.selectedProjectId = record.data.id;
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
    updateDevGroupWithProject: function(button) {
        var win = button.up('window'),
                form = win.down('#projectGroupsForm'),
                values = form.getValues();
        values.projectId = win.selectedProjectId;
        values.groupIds = values.groupIds.split(",").map(Number);
        Ext.Ajax.request({
            method: 'POST',
            url: './v1/projectgroups/project/groups/',
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
    saveProjectMetrics: function(button) {
        var form = button.up('#projectmetricsForm'),
                values = form.getValues();
        values.projectId = form.selectedProjectId;
        if (values.id === "") {
            delete values.id;
        } else {
            values.id = parseInt(values.id);
        }

        if (form.isValid()) {
            Ext.Ajax.request({
                method: 'POST',
                url: './v1/metrics',
                headers: {
                    'Accept': 'application/json'
                },
                jsonData: values,
                scope: this,
                success: function(response, options) {
                    var responseData = Ext.decode(response.responseText);
                    if (responseData.success) {
                        metricsgrid = Ext.getCmp('projectmetricsgridid');
                        metricsgrid.getStore().reload({
                            callback: function() {
                                metricsgrid.getView().refresh();
                                var metricsForm = Ext.getCmp('projectmetricsForm');
                                metricsForm.getForm().reset();
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
        }
    },
    metricsSelect: function(grid, record, index, options) {
        var metricsForm = Ext.getCmp('projectmetricsForm');
        metricsForm.loadRecord(record);
        metricsForm.store = grid.store;

    },
    newProjectMetrics: function(button) {
        var form = button.up('#projectmetricsForm');
        form.getForm().reset();
    },
    featuresSelect: function(grid, record, index, options) {
        var featuresForm = Ext.getCmp('projectfeaturesForm');
        featuresForm.loadRecord(record);
        var typeComboItem = featuresForm.down("#featuresComboTypeData");
        typeComboItem.setValue(record.data.type);
        featuresForm.store = grid.store;

    },
    newFeatures: function(button) {
        var form = button.up('#projectfeaturesForm');
        form.getForm().reset();
    },
    saveFeatures: function(button) {
        var form = button.up('#projectfeaturesForm'),
                values = form.getValues();
        values.projectId = form.selectedProjectId;
        if (values.id === "") {
            delete values.id;
        } else {
            values.id = parseInt(values.id);
        }
        console.log(values);
        if (form.isValid()) {
            Ext.Ajax.request({
                method: 'POST',
                url: './v1/features',
                headers: {
                    'Accept': 'application/json'
                },
                jsonData: values,
                scope: this,
                success: function(response, options) {
                    var responseData = Ext.decode(response.responseText);
                    if (responseData.success) {
                        metricsgrid = Ext.getCmp('projectfeaturesgridid');
                        metricsgrid.getStore().reload({
                            callback: function() {
                                metricsgrid.getView().refresh();
                                var featuresForm = Ext.getCmp('projectfeaturesForm');
                                featuresForm.getForm().reset();
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
            console.log("Save sucessful");
        } else {
            console.log("Form is not valid");
        }
    },
    deleteMetrics: function(button) {
        var grid = Ext.getCmp('projectmetricsgridid');
        this.deleteWithPrompt(grid, this.deleteSelectedMetrics);
    },
    deleteFeatures: function(button) {
        var grid = Ext.getCmp('projectfeaturesgridid');
        this.deleteWithPrompt(grid, this.deleteSelectedFeatures);
    },
    deleteWithPrompt: function(grid, followupfunction) {
        var sm = grid.getSelectionModel();
        if (sm.hasSelection()) {
            Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete ?', followupfunction, this);
            //we need to delete all the childrens
        } else {
            Ext.Msg.show({
                title: "Error !!!",
                msg: "No Rows selected, Please select a ROW to delete.",
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        }
    },
    deleteSelectedMetrics: function() {
        var grid = Ext.getCmp('projectmetricsgridid');
        var devgrpsGridSM = grid.getSelectionModel();
        if (devgrpsGridSM.hasSelection()) {
            var selectedRow = devgrpsGridSM.getSelection();
            var record = {};
            if (selectedRow.length === 1) {
                record = (selectedRow[0].data);
            }
            Ext.Ajax.request({
                method: 'POST',
                url: './v1/metrics/delete/' + record.id,
                headers: {
                    'Accept': 'application/json'
                },
                scope: this,
                success: function(response, options) {
                    var responseData = Ext.decode(response.responseText);
                    if (responseData.success) {
                        var grid = Ext.getCmp('projectmetricsgridid');
                        grid.getStore().reload({
                            callback: function() {
                                grid.getView().refresh();
                                var form = Ext.getCmp('projectmetricsForm');
                                form.getForm().reset();
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
    },
    deleteSelectedFeatures: function() {
        var grid = Ext.getCmp('projectfeaturesgridid');
        var devgrpsGridSM = grid.getSelectionModel();
        if (devgrpsGridSM.hasSelection()) {
            var selectedRow = devgrpsGridSM.getSelection();
            var record = {};
            if (selectedRow.length === 1) {
                record = (selectedRow[0].data);
            }
            Ext.Ajax.request({
                method: 'POST',
                url: './v1/features/delete/' + record.id,
                headers: {
                    'Accept': 'application/json'
                },
                scope: this,
                success: function(response, options) {
                    var responseData = Ext.decode(response.responseText);
                    if (responseData.success) {
                        var grid = Ext.getCmp('projectfeaturesgridid');
                        grid.getStore().reload({
                            callback: function() {
                                grid.getView().refresh();
                                var form = Ext.getCmp('projectfeaturesForm');
                                form.getForm().reset();
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
});